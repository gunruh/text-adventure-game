
package com.gunruh.textgame;

import com.gunruh.textgame.enumerations.Action;
import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.Player;
import com.gunruh.textgame.objects.Statement;
import com.gunruh.textgame.objects.containerObjects.Container;
import com.gunruh.textgame.objects.items.Blaster;
import com.gunruh.textgame.objects.rooms.RoomA;
import com.gunruh.textgame.objects.rooms.RoomB;
import com.gunruh.textgame.objects.rooms.starship.level1.JanitorsQuarters;
import com.gunruh.textgame.utils.Constants;
import com.gunruh.textgame.utils.ContainerUtils;
import com.gunruh.textgame.utils.IOUtils;
import com.gunruh.textgame.utils.InputMaps;

import java.util.List;

import static com.gunruh.textgame.utils.IOUtils.*;

public class Game {
    Player player = Player.getInstance();

    public void run() {
        initializeGame();

        String input = null;
        while (!"quit".equalsIgnoreCase(input)) {
            input = IOUtils.getInputText();

            if (isNullOrEmpty(input)) {
                continue;
            }

            if (input.toLowerCase().trim().contains("quit")) {
                continue;
            }

            Statement statement = IOUtils.getStatementFromInputText(input, player.getItems(), player.getCurrentRoom().getItems());

            switch (statement.getAction()) {
                case Take: {
                    handleTake(statement);
                    // todo add support for 'take from inside container' probably in 'handleTake()' method ^^^
                    break;
                }
                case Insert: {
                    // todo handle this.
                    IOUtils.displayWithinAsterisks("The game doesn't support 'insert into' yet.");
                    break;
                }
                case Drop: {
                    handleDrop(statement);
                    break;
                }
                case Look: {
                    handleLook(statement);
                    break;
                }
                case Name: {
                    handleName(statement);
                    break;
                }
                case Shoot: {
                    handleShoot(statement);
                    break;
                }
                case Speak: {
                    handleSpeak(statement);
                    break;
                }
                case Inventory: {
                    handleInventory(statement);
                    break;
                }
                case Open: {
                    handleOpen(statement);
                    break;
                }
                case Close: {
                    handleClose(statement);
                    break;
                }
                case Move: {
                    handleMove(statement);
                    break;
                }
                default: {
                    display("I don't know that action.");
                    break;
                }
            }
        }

        // Exiting loop
        display("That's all folks.");
    }

    private void handleMove(Statement statement) {
        if (statement.getDirection() == null) {
            display("Which direction?");
            String directionInput = IOUtils.getInputText();
            statement.setDirection(InputMaps.directionMap.get(directionInput));
        }

        if (statement.getDirection() == null) {
            display("Sorry - I don't know that direction.");
            return;
        }

        switch (statement.getDirection()) {
            case North: {
                player.enterRoom(player.getCurrentRoom().goNorth());
                break;
            }
            case NorthEast: {
                player.enterRoom(player.getCurrentRoom().goNorthEast());
                break;
            }
            case East: {
                player.enterRoom(player.getCurrentRoom().goEast());
                break;
            }
            case SouthEast: {
                player.enterRoom(player.getCurrentRoom().goSouthEast());
                break;
            }
            case South: {
                player.enterRoom(player.getCurrentRoom().goSouth());
                break;
            }
            case SouthWest: {
                player.enterRoom(player.getCurrentRoom().goSouthWest());
                break;
            }
            case West: {
                player.enterRoom(player.getCurrentRoom().goWest());
                break;
            }
            case NorthWest: {
                player.enterRoom(player.getCurrentRoom().goNorthWest());
                break;
            }
            case Up: {
                player.enterRoom(player.getCurrentRoom().goUp());
                break;
            }
            case Down: {
                player.enterRoom(player.getCurrentRoom().goDown());
                break;
            }
            default: {
                display("I don't know that direction.");
                break;
            }
        }
    }

    private void handleInventory(Statement statement) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Inventory:\n");

        stringBuilder.append(IOUtils.getListStringFromGameObjectsList(Player.getInstance().getItems(), 0));

        IOUtils.display(stringBuilder.toString());
    }

    private void handleSpeak(Statement statement) {
        if (statement.getReceivingObject() == null) {
            display("Talk to who?");
            String whoInput = getInputText();
            statement.setReceivingObject(findMatchingGameObjectFromList(whoInput, getCombinedGameObjectsList(player.getItems(), player.getCurrentRoom().getItems())));
        }

        if (statement.getReceivingObject() == null) {
            display("Sorry - I didn't understand who you wanted to talk to.");
            return;
        }

        player.talkTo(statement.getReceivingObject());
    }

    private void handleShoot(Statement statement) {
        // check for 'shoot blaster' meaning 'shoot with blaster', rather than 'shoot at blaster'.
        if (statement.getActingObject() == null && statement.getReceivingObject() != null && statement.getReceivingObject().getEffectivenessAsBlaster() > 0) {
            statement.setActingObject(statement.getReceivingObject());
            statement.setReceivingObject(null);
        }

        if (statement.getActingObject() == null) {
            GameObject bestMatchBlaster = IOUtils.getGameObjectWithHighestEffectiveness(Player.getInstance().getItems(), Action.Shoot, statement.getReceivingObject());
            if (bestMatchBlaster == null) {
                display("You don't have anything to use as a blaster.");
                return;
            }
            else {
                statement.setActingObject(bestMatchBlaster);
            }
        }

        if (statement.getReceivingObject() == null) {
            display("What do you want to shoot " + IOUtils.getNickNameOrNameWithArticle(statement.getActingObject()) + " at?");
            String searchText = IOUtils.getInputText();
            GameObject receivingObject = IOUtils.findMatchingGameObjectFromList(searchText, IOUtils.getCombinedGameObjectsList(Player.getInstance().getItems(), Player.getInstance().getCurrentRoom().getItems()));
            if (receivingObject == statement.getActingObject()) {
                display("You can't shoot an object with itself.");
                return;
            }
            else {
                statement.setReceivingObject(receivingObject);
            }
        }

        if (statement.getReceivingObject() == null) {
            display("Sorry, I couldn't determine your target.");
            return;
        }

        GameObject targetObject = statement.getReceivingObject();
        statement.getActingObject().shoot(targetObject);
    }

    private void handleName(Statement statement) {
        if (statement.getReceivingObject() == null) {
            display("Name what?");
            String nameInput = IOUtils.getInputText();
            statement.setReceivingObject(findMatchingGameObjectFromList(nameInput, IOUtils.getCombinedGameObjectsList(player.getItems(), player.getCurrentRoom().getItems())));
        }

        if (statement.getReceivingObject() == null) {
            display("Sorry - I can't find that object.");
            return;
        }

        if (isNullOrEmpty(statement.getRemainingString())) {
            display("What did you want to name it?");
            statement.setRemainingString(getInputText());
        }

        if (isNullOrEmpty(statement.getRemainingString())) {
            display("Sorry - I didn't catch that name.");
            return;
        }
        statement.getReceivingObject().setNickName(statement.getRemainingString());
        displayWithinAsterisks("The " + statement.getReceivingObject().getName() + "'s name is " + statement.getRemainingString());
    }

    private void handleLook(Statement statement) {
        if (statement.getReceivingObject() != null) {
            displayGameObject(statement.getReceivingObject());
        }
        else {
            displayGameObject(player.getCurrentRoom());
        }
    }

    private void handleDrop(Statement statement) {
        if (statement.getReceivingObject() == null) {
            display("Drop what?");
            String takeInput = IOUtils.getInputText();
            statement.setReceivingObject(findMatchingGameObjectFromList(takeInput, player.getItems()));
        }

        if (statement.getReceivingObject() == null) {
            display("Sorry - I can't find that object in your inventory.");
            return;
        }

        GameObject droppedObject = ContainerUtils.recursiveRemove(player, statement.getReceivingObject());
        if (droppedObject != GameObject.EMPTY_GAME_OBJECT) {
            player.getCurrentRoom().addItem(droppedObject);
            displayWithinAsterisks("Drops " + (!isNullOrEmpty(droppedObject.getNickName()) ? droppedObject.getNickName() : droppedObject.getName()));
        }
        else {
            display("You don't have that.");
        }
    }

    private void handleTake(Statement statement) {
        GameObject itemToTake = statement.getReceivingObject(); // default to receiving object.
        Container containerItem = player.getCurrentRoom(); // default to current room.

        // Check to see if you're taking an item from within another item
        if (statement.getReceivingObject() != null) {
            if (statement.getActingObject() != null && statement.getReceivingObject() instanceof Container) {
                itemToTake = statement.getActingObject();
                containerItem = (Container) statement.getReceivingObject();
            }
        }

        if (itemToTake == null) {
            display("Take what?");
            String takeInput = IOUtils.getInputText();
            itemToTake = findMatchingGameObjectFromList(takeInput, IOUtils.getCombinedGameObjectsList(player.getItems(), containerItem.getItems()), false);
        }

        if (itemToTake == null) {
            display("Sorry - I can't find that object.");
            return;
        }

        if (itemToTake.isPermanentFixture()) {
            display("Unfortunately you can't take this item with you.");
            return;
        }

        if (Player.getInstance().getItems().contains(itemToTake)) {
            display("You already have " + IOUtils.getNickNameOrNameWithArticle(itemToTake));
            return;
        }

        boolean successfulRemoved = itemToTake.getParentContainer().remove(itemToTake);
        if (!successfulRemoved) {
            displayWithinAsterisks("Error taking object... could not remove from parent container.");
        }
        Player.getInstance().takeItem(itemToTake);
        displayWithinAsterisks("Picks up " + IOUtils.getNickNameOrNameWithArticle(itemToTake));
    }

    private void handleOpen(Statement statement) {
        if (statement.getReceivingObject() == null) {
            IOUtils.display("Open what?");
            String openInput = IOUtils.getInputText();
            statement.setReceivingObject(findMatchingGameObjectFromList(openInput, IOUtils.getCombinedGameObjectsList(player.getItems(), player.getCurrentRoom().getItems())));
        }

        if (statement.getReceivingObject() == null) {
            display("Sorry - I can't find that object.");
            return;
        }

        if (statement.getReceivingObject() instanceof Container) {
            ((Container) statement.getReceivingObject()).receiveOpen();
        }
        else {
            IOUtils.displayWithinAsterisks("You can't open " + IOUtils.getNickNameOrNameWithArticle(statement.getReceivingObject()));
        }
    }

    private void handleClose(Statement statement) {
        if (statement.getReceivingObject() == null) {
            IOUtils.display("Close what?");
            String closeInput = IOUtils.getInputText();
            statement.setReceivingObject(findMatchingGameObjectFromList(closeInput, IOUtils.getCombinedGameObjectsList(player.getItems(), player.getCurrentRoom().getItems())));
        }

        if (statement.getReceivingObject() == null) {
            display("Sorry - I can't find that object.");
            return;
        }

        if (statement.getReceivingObject() instanceof Container) {
            ((Container) statement.getReceivingObject()).receiveClose();
        }
        else {
            IOUtils.displayWithinAsterisks("You can't close " + IOUtils.getNickNameOrNameWithArticle(statement.getReceivingObject()));
        }
    }

    private void initializeGame() {
        // Initialize Room Objects
        RoomA.getInstance().addItem(new Blaster());
        RoomB.getInstance().addItem(new GameObject("Troll", "Big, smelly, hometown of \"cave\".") {});
        RoomB.getInstance().addItem(new GameObject("Rock", "Just your average cave rock.") {});
        RoomB.getInstance().addItem(new GameObject("Sock", "Wonder who left this here...") {});
        RoomB.getInstance().addItem(new GameObject("Clock", "It is unclear whether the time is correct.") {});

        display(Constants.SPACE_DUDES_TITLE);
        display(Constants.INTRO_TEXT);
        player.enterRoom(JanitorsQuarters.getInstance());
    }
}
