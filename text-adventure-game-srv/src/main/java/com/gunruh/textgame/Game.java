
package com.gunruh.textgame;

import com.gunruh.textgame.enumerations.Action;
import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.Player;
import com.gunruh.textgame.objects.Statement;
import com.gunruh.textgame.objects.containerObjects.Container;
import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.rooms.starship.level1.JanitorsQuarters;
import com.gunruh.textgame.utils.ContainerUtils;
import com.gunruh.textgame.utils.IOUtils;
import com.gunruh.textgame.utils.InputMaps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.gunruh.textgame.utils.IOUtils.*;

public class Game {
    private final String gameId;
    private final StringBuffer outputBuffer = new StringBuffer();
    private final Player player = new Player("Space Dude", "A man on a mission.");
    private final Map<String, Room> roomRegistry = new HashMap<String, Room>();
    private final Map<String, GameObject> objectRegistry = new HashMap<String, GameObject>();

    private Game() {
        gameId = UUID.randomUUID().toString();
    }

    public static Game createNewGame() {
        // todo - also need to specify what type of output here? Console or otherwise (like some StringBuffer or something for web service.)
        Game game = new Game();

        game.initializeGame();

        return game;
    }

    private void initializeGame() {
        player.enterRoom(JanitorsQuarters.getInstance());
    }

    public void parseInput(String input) {
        Statement statement = IOUtils.getStatementFromInputText(input, player.getItems(), player.getCurrentRoom().getItems());

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
                display(outputBuffer, "I don't know that action.");
                break;
            }
        }
    }

    private void handleMove(Statement statement) {
        if (statement.getDirection() == null) {
            display(outputBuffer, "Which direction?");
            String directionInput = IOUtils.getInputTextFromConsole();
            statement.setDirection(InputMaps.directionMap.get(directionInput));
        }

        if (statement.getDirection() == null) {
            display(outputBuffer, "Sorry - I don't know that direction.");
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
                display(outputBuffer, "I don't know that direction.");
                break;
            }
        }
    }

    private void handleInventory(Statement statement) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Inventory:\n");

        stringBuilder.append(IOUtils.getListStringFromGameObjectsList(Player.getInstance().getItems(), 0));

        IOUtils.display(outputBuffer, stringBuilder.toString());
    }

    private void handleSpeak(Statement statement) {
        if (statement.getReceivingObject() == null) {
            display(outputBuffer, "Talk to who?");
            String whoInput = getInputTextFromConsole();
            statement.setReceivingObject(findMatchingGameObjectFromList(whoInput, getCombinedGameObjectsList(player.getItems(), player.getCurrentRoom().getItems())));
        }

        if (statement.getReceivingObject() == null) {
            display(outputBuffer, "Sorry - I didn't understand who you wanted to talk to.");
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
                display(outputBuffer, "You don't have anything to use as a blaster.");
                return;
            }
            else {
                statement.setActingObject(bestMatchBlaster);
            }
        }

        if (statement.getReceivingObject() == null) {
            display(outputBuffer, "What do you want to shoot " + IOUtils.getNickNameOrNameWithArticle(statement.getActingObject()) + " at?");
            String searchText = IOUtils.getInputTextFromConsole();
            GameObject receivingObject = IOUtils.findMatchingGameObjectFromList(searchText, IOUtils.getCombinedGameObjectsList(Player.getInstance().getItems(), Player.getInstance().getCurrentRoom().getItems()));
            if (receivingObject == statement.getActingObject()) {
                display(outputBuffer, "You can't shoot an object with itself.");
                return;
            }
            else {
                statement.setReceivingObject(receivingObject);
            }
        }

        if (statement.getReceivingObject() == null) {
            display(outputBuffer, "Sorry, I couldn't determine your target.");
            return;
        }

        GameObject targetObject = statement.getReceivingObject();
        statement.getActingObject().shoot(targetObject);
    }

    private void handleName(Statement statement) {
        if (statement.getReceivingObject() == null) {
            display(outputBuffer, "Name what?");
            String nameInput = IOUtils.getInputTextFromConsole();
            statement.setReceivingObject(findMatchingGameObjectFromList(nameInput, IOUtils.getCombinedGameObjectsList(player.getItems(), player.getCurrentRoom().getItems())));
        }

        if (statement.getReceivingObject() == null) {
            display(outputBuffer, "Sorry - I can't find that object.");
            return;
        }

        if (isNullOrEmpty(statement.getRemainingString())) {
            display(outputBuffer, "What did you want to name it?");
            statement.setRemainingString(getInputTextFromConsole());
        }

        if (isNullOrEmpty(statement.getRemainingString())) {
            display(outputBuffer, "Sorry - I didn't catch that name.");
            return;
        }
        statement.getReceivingObject().setNickName(statement.getRemainingString());
        displayWithinAsterisks(outputBuffer, "The " + statement.getReceivingObject().getName() + "'s name is " + statement.getRemainingString());
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
        GameObject itemToDrop = statement.getReceivingObject(); // default to receiving object.
        Container containerItem = player.getCurrentRoom(); // default to current room.

        // check to see if you're dropping this item into another item.
        if (statement.getReceivingObject() != null) {
            if (statement.getActingObject() != null && statement.getReceivingObject() instanceof Container) {
                itemToDrop = statement.getActingObject();
                containerItem = (Container) statement.getReceivingObject();
            }
        }

        if (itemToDrop == null) {
            display(outputBuffer, "Which object?");
            String takeInput = IOUtils.getInputTextFromConsole();
            itemToDrop = findMatchingGameObjectFromList(takeInput, IOUtils.getCombinedGameObjectsList(player.getItems(), player.getCurrentRoom().getItems()));
        }

        if (itemToDrop == null) {
            display(outputBuffer, "Sorry - I can't find that object.");
            return;
        }

        // if containerItem is the current room - you can only drop it if you already have it.
        if (containerItem == player.getCurrentRoom()) {
            GameObject removedObject = ContainerUtils.recursiveRemove(player, itemToDrop);
            if (removedObject != GameObject.EMPTY_GAME_OBJECT) {
                player.getCurrentRoom().addItem(removedObject);
                displayWithinAsterisks(outputBuffer, "Drops " + IOUtils.getNickNameOrNameWithArticle(removedObject) + ".");
            }
            else {
                display(outputBuffer, "You don't have that.");
            }
        }
        else {
            // In this case, it's an 'insert into' command. The player doesn't have to have it in their inventory to move it.
            // You can't move permanent fixtures (can't put it inside a container)
            if (!itemToDrop.isPermanentFixture()) {
                List<GameObject> parentList = itemToDrop.getParentContainer();
                boolean successfulInsert = itemToDrop.insertInto((GameObject) containerItem);
                if (successfulInsert) {
                    parentList.remove(itemToDrop);
                }
            }
            else {
                displayWithinAsterisks(outputBuffer, IOUtils.capitalizeFirstLetter(IOUtils.getNickNameOrNameWithArticle(itemToDrop)) + " cannot be moved.");
            }
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
            display(outputBuffer, "Take what?");
            String takeInput = IOUtils.getInputTextFromConsole();
            itemToTake = findMatchingGameObjectFromList(takeInput, IOUtils.getCombinedGameObjectsList(player.getItems(), containerItem.getItems()), false);
        }

        if (itemToTake == null) {
            display(outputBuffer, "Sorry - I can't find that object.");
            return;
        }

        if (itemToTake.isPermanentFixture()) {
            display(outputBuffer, "Unfortunately you can't take this item with you.");
            return;
        }

        if (Player.getInstance().getItems().contains(itemToTake)) {
            display(outputBuffer, "You already have " + IOUtils.getNickNameOrNameWithArticle(itemToTake));
            return;
        }

        boolean successfulRemoved = itemToTake.getParentContainer().remove(itemToTake);
        if (!successfulRemoved) {
            displayWithinAsterisks(outputBuffer, "Error taking object... could not remove from parent container.");
        }
        Player.getInstance().addItem(itemToTake);
        displayWithinAsterisks(outputBuffer, "Picks up " + IOUtils.getNickNameOrNameWithArticle(itemToTake));
    }

    private void handleOpen(Statement statement) {
        if (statement.getReceivingObject() == null) {
            IOUtils.display(outputBuffer, "Open what?");
            String openInput = IOUtils.getInputTextFromConsole();
            statement.setReceivingObject(findMatchingGameObjectFromList(openInput, IOUtils.getCombinedGameObjectsList(player.getItems(), player.getCurrentRoom().getItems())));
        }

        if (statement.getReceivingObject() == null) {
            display(outputBuffer, "Sorry - I can't find that object.");
            return;
        }

        if (statement.getReceivingObject() instanceof Container) {
            ((Container) statement.getReceivingObject()).receiveOpen();
        }
        else {
            IOUtils.displayWithinAsterisks(outputBuffer, "You can't open " + IOUtils.getNickNameOrNameWithArticle(statement.getReceivingObject()));
        }
    }

    private void handleClose(Statement statement) {
        if (statement.getReceivingObject() == null) {
            IOUtils.display(outputBuffer, "Close what?");
            String closeInput = IOUtils.getInputTextFromConsole();
            statement.setReceivingObject(findMatchingGameObjectFromList(closeInput, IOUtils.getCombinedGameObjectsList(player.getItems(), player.getCurrentRoom().getItems())));
        }

        if (statement.getReceivingObject() == null) {
            display(outputBuffer, "Sorry - I can't find that object.");
            return;
        }

        if (statement.getReceivingObject() instanceof Container) {
            ((Container) statement.getReceivingObject()).receiveClose();
        }
        else {
            IOUtils.displayWithinAsterisks(outputBuffer, "You can't close " + IOUtils.getNickNameOrNameWithArticle(statement.getReceivingObject()));
        }
    }
}
