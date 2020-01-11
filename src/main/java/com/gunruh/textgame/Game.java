
package com.gunruh.textgame;

import com.gunruh.textgame.enumerations.Action;
import com.gunruh.textgame.objects.*;
import com.gunruh.textgame.objects.items.Blaster;
import com.gunruh.textgame.objects.rooms.RoomA;
import com.gunruh.textgame.objects.rooms.RoomB;
import com.gunruh.textgame.objects.rooms.starship.level1.JanitorsQuarters;
import com.gunruh.textgame.utils.Constants;
import com.gunruh.textgame.utils.IOUtils;
import com.gunruh.textgame.utils.InputMaps;

import java.util.Iterator;

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

            Statement statement = IOUtils.getStatementFromInputText(input, player.getInventory(), player.getCurrentRoom().getAvailableObjects());

            switch (statement.getAction()) {
                case Take: {
                    handleTake(statement);
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
                    handleShowInventory(statement);
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

    private void handleShowInventory(Statement statement) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Inventory:");

        if (Player.getInstance().getInventory().isEmpty()) {
            stringBuilder.append(" Nothing.");
        }
        else {
            stringBuilder.append("\n\n"); // Next line - double spaced.
            Iterator<GameObject> itemIterator = Player.getInstance().getInventory().iterator();
            while (itemIterator.hasNext()) {
                GameObject gameObject = itemIterator.next();
                stringBuilder.append("- ");
                stringBuilder.append(IOUtils.getNickNameAndNameString(gameObject));

                if (itemIterator.hasNext()) {
                    stringBuilder.append("\n");
                }
            }
        }

        IOUtils.display(stringBuilder.toString());
    }

    private void handleSpeak(Statement statement) {
        if (statement.getReceivingObject() == null) {
            display("Talk to who?");
            String whoInput = getInputText();
            statement.setReceivingObject(getMatchingGameObjectFromList(whoInput, getCombinedGameObjectsList(player.getInventory(), player.getCurrentRoom().getAvailableObjects())));
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
            GameObject bestMatchBlaster = IOUtils.getGameObjectWithHighestEffectiveness(Player.getInstance().getInventory(), Action.Shoot, statement.getReceivingObject());
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
            GameObject receivingObject = IOUtils.getMatchingGameObjectFromList(searchText, IOUtils.getCombinedGameObjectsList(Player.getInstance().getInventory(), Player.getInstance().getCurrentRoom().getAvailableObjects()));
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
            statement.setReceivingObject(getMatchingGameObjectFromList(nameInput, IOUtils.getCombinedGameObjectsList(player.getInventory(), player.getCurrentRoom().getAvailableObjects())));
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
            statement.setReceivingObject(getMatchingGameObjectFromList(takeInput, player.getInventory()));
        }

        if (statement.getReceivingObject() == null) {
            display("Sorry - I can't find that object.");
            return;
        }

        int inventoryIndex = player.getInventory().indexOf(statement.getReceivingObject());
        if (inventoryIndex != -1) {
            GameObject droppedObject = player.getInventory().remove(inventoryIndex);
            player.getCurrentRoom().getAvailableObjects().add(droppedObject);
            displayWithinAsterisks("Drops " + (!isNullOrEmpty(droppedObject.getNickName()) ? droppedObject.getNickName() : droppedObject.getName()));
        }
        else {
            display("You don't have that.");
        }
    }

    private void handleTake(Statement statement) {
        if (statement.getReceivingObject() == null) {
            display("Take what?");
            String takeInput = IOUtils.getInputText();
            statement.setReceivingObject(getMatchingGameObjectFromList(takeInput, IOUtils.getCombinedGameObjectsList(player.getInventory(), player.getCurrentRoom().getAvailableObjects())));
        }

        if (statement.getReceivingObject() == null) {
            display("Sorry - I can't find that object.");
            return;
        }

        if (statement.getReceivingObject().isPermanentFixture()) {
            display("Unfortunately you can't take this item with you.");
            return;
        }

        int roomObjectIndex = player.getCurrentRoom().getAvailableObjects().indexOf(statement.getReceivingObject());
        if (roomObjectIndex != -1) {
            GameObject takenObject = player.getCurrentRoom().getAvailableObjects().remove(roomObjectIndex);
            player.getInventory().add(takenObject);
            displayWithinAsterisks("Picks up " + IOUtils.getNickNameOrNameWithArticle(takenObject));
        }
        else {
            int playerInventoryIndex = player.getInventory().indexOf(statement.getReceivingObject());
            if (playerInventoryIndex != -1) {
                display("You already have " + IOUtils.getNickNameOrNameWithArticle(statement.getReceivingObject()));
            }
        }
    }

    private void initializeGame() {
        // Initialize Room Objects
        RoomA.getInstance().getAvailableObjects().add(new Blaster());
        RoomB.getInstance().getAvailableObjects().add(new GameObject("Troll", "Big, smelly, hometown of \"cave\".") {});
        RoomB.getInstance().getAvailableObjects().add(new GameObject("Rock", "Just your average cave rock.") {});
        RoomB.getInstance().getAvailableObjects().add(new GameObject("Sock", "Wonder who left this here...") {});
        RoomB.getInstance().getAvailableObjects().add(new GameObject("Clock", "It is unclear whether the time is correct.") {});

        display(Constants.SPACE_DUDES_TITLE);
        display(Constants.INTRO_TEXT);
        player.enterRoom(JanitorsQuarters.getInstance());
    }
}
