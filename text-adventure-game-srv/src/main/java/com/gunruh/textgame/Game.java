
package com.gunruh.textgame;

import com.gunruh.textgame.enumerations.Action;
import com.gunruh.textgame.enumerations.Direction;
import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.Player;
import com.gunruh.textgame.objects.Statement;
import com.gunruh.textgame.objects.containerObjects.Container;
import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.rooms.starship.level1.JanitorsQuarters;
import com.gunruh.textgame.utils.Constants;
import com.gunruh.textgame.utils.ContainerUtils;
import com.gunruh.textgame.utils.IOUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static com.gunruh.textgame.utils.IOUtils.*;

public class Game {
    // TODO Add logger

    private final String gameId;
    private final GameOutput gameOutput;
    private final Player player = new Player(this, "Space Dude", "A man on a mission.");
    private final List<Room> rooms = new ArrayList<Room>();

    private Statement lastStatement = null;
    private boolean isExpectingClarifiedStatement = false;

    private Game() {
        gameId = UUID.randomUUID().toString();
        gameOutput = new GameOutput();
    }

    public static Game createNewGame(boolean displayStartText) {
        Game game = new Game();

        game.initializeGame(displayStartText);

        return game;
    }

    private void initializeGame(boolean displayStartText) {
        if (displayStartText) {
            getGameOutput().appendln(Constants.SPACE_DUDES_TITLE);
            getGameOutput().appendln(Constants.INTRO_TEXT);
        }
        player.enterRoom(getRoom(JanitorsQuarters.class));
    }

    public void parseInput(String input) {
        Statement statement = getStatementFromInputText(input, player.getItems(), player.getCurrentRoom().getItems());
        if (isExpectingClarifiedStatement) {
            statement = enhanceLastStatement(statement);
        }
        parseStatement(statement);
        lastStatement = statement;
    }

    private Statement enhanceLastStatement(Statement statement) {
        // Populate new data into previous statement.
        switch (lastStatement.getAction()) {
            default: {
                if (!IOUtils.isNullOrEmpty(statement.getInputString())) {
                    lastStatement.setInputString(statement.getInputString());
                }
                // keep lastStatement.Action the same.

                if (statement.getDirection() != null && !statement.getDirection().equals(Direction.NotADirection)) {
                    lastStatement.setDirection(statement.getDirection());
                }

                if (statement.getActingObject() != null && !statement.getActingObject().equals(GameObject.EMPTY_GAME_OBJECT)) {
                    lastStatement.setActingObject(statement.getActingObject());
                }

                if (statement.getReceivingObject() != null && !statement.getReceivingObject().equals(GameObject.EMPTY_GAME_OBJECT)) {
                    lastStatement.setReceivingObject(statement.getReceivingObject());
                }

                if (!IOUtils.isNullOrEmpty(statement.getRemainingString())) {
                    lastStatement.setRemainingString(statement.getRemainingString());
                }
                else {
                    lastStatement.setRemainingString(statement.getInputString());
                }

                statement = lastStatement;
                break;
            }
        }

        return statement;
    }

    public void parseStatement(Statement statement) {
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
                getGameOutput().appendln("I don't know that action.");
                break;
            }
        }
    }

    private void handleMove(Statement statement) {
        if (isExpectingClarifiedStatement) {
            isExpectingClarifiedStatement = false;
        }
        else if (statement.getDirection() == null) {
            getGameOutput().appendln("Which direction?");
            isExpectingClarifiedStatement = true;
            return;
        }

        if (statement.getDirection() == null) {
            getGameOutput().appendln("Sorry - I don't know that direction.");
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
                getGameOutput().appendln("I don't know that direction.");
                break;
            }
        }
    }

    private void handleInventory(Statement statement) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Inventory:\n");

        stringBuilder.append(getListStringFromGameObjectsList(getPlayer().getItems(), 0));

        getGameOutput().appendln(stringBuilder.toString());
    }

    private void handleSpeak(Statement statement) {
        if (isExpectingClarifiedStatement) {
            isExpectingClarifiedStatement = false;
        }
        else if (statement.getReceivingObject() == null) {
            getGameOutput().appendln("Talk to who?");
            isExpectingClarifiedStatement = true;
            return;
        }

        if (statement.getReceivingObject() == null) {
            getGameOutput().appendln("Sorry - I didn't understand who you wanted to talk to.");
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
            GameObject bestMatchBlaster = getGameObjectWithHighestEffectiveness(getPlayer().getItems(), Action.Shoot, statement.getReceivingObject());
            if (bestMatchBlaster == null) {
                getGameOutput().appendln("You don't have anything to use as a blaster.");
                return;
            }
            else {
                statement.setActingObject(bestMatchBlaster);
            }
        }

        if (isExpectingClarifiedStatement) {
            isExpectingClarifiedStatement = false;
        }
        else if (statement.getReceivingObject() == null) {
            getGameOutput().appendln("What do you want to shoot " + getNickNameOrNameWithArticle(statement.getActingObject()) + " at?");
            isExpectingClarifiedStatement = true;
            return;
        }

        if (statement.getReceivingObject() == null) {
            getGameOutput().appendln("Sorry, I couldn't determine your target.");
            return;
        }

        GameObject targetObject = statement.getReceivingObject();
        statement.getActingObject().shoot(targetObject);
    }

    private void handleName(Statement statement) {
        if (statement.getReceivingObject() == null) {
            if (isExpectingClarifiedStatement) {
                // Was expecting this to be filled, but user didn't type it right, so move on.
                isExpectingClarifiedStatement = false;
            }
            else {
                getGameOutput().appendln("Name what?");
                isExpectingClarifiedStatement = true;
                return;
            }
        }

        if (isNullOrEmpty(statement.getRemainingString())) {
            if (isExpectingClarifiedStatement) {
                // Was expecting this to be filled, but user didn't type it right, so move on.
                statement.setRemainingString(statement.getInputString());
                isExpectingClarifiedStatement = false;
            }
            else {
                getGameOutput().appendln("What did you want to name it?");
                isExpectingClarifiedStatement = true;
                return;
            }
        }

        if (statement.getReceivingObject() == null) {
            getGameOutput().appendln("Sorry - I can't find that object.");
            return;
        }

        if (isNullOrEmpty(statement.getRemainingString())) {
            getGameOutput().appendln("Sorry - I didn't catch that name.");
            return;
        }
        statement.getReceivingObject().setNickName(statement.getRemainingString());
        getGameOutput().appendln(IOUtils.surroundWithAsterisks("The " + statement.getReceivingObject().getName() + "'s name is " + statement.getRemainingString()));
    }

    private void handleLook(Statement statement) {
        if (statement.getReceivingObject() != null) {
            getGameOutput().appendln(displayGameObject(statement.getReceivingObject()));
        }
        else {
            getGameOutput().appendln(displayGameObject(player.getCurrentRoom()));
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
            getGameOutput().appendln("Which object?");
            String takeInput = getInputTextFromConsole();
            itemToDrop = findMatchingGameObjectFromList(takeInput, getCombinedGameObjectsList(player.getItems(), player.getCurrentRoom().getItems()));
        }

        if (itemToDrop == null) {
            getGameOutput().appendln("Sorry - I can't find that object.");
            return;
        }

        // if containerItem is the current room - you can only drop it if you already have it.
        if (containerItem == player.getCurrentRoom()) {
            GameObject removedObject = ContainerUtils.recursiveRemove(player, itemToDrop);
            if (removedObject != GameObject.EMPTY_GAME_OBJECT) {
                player.getCurrentRoom().addItem(removedObject);
                getGameOutput().appendln(IOUtils.surroundWithAsterisks("Drops " + getNickNameOrNameWithArticle(removedObject) + "."));
            }
            else {
                getGameOutput().appendln("You don't have that.");
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
                getGameOutput().appendln(IOUtils.surroundWithAsterisks(capitalizeFirstLetter(getNickNameOrNameWithArticle(itemToDrop)) + " cannot be moved."));
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
            getGameOutput().appendln("Take what?");
            String takeInput = getInputTextFromConsole();
            itemToTake = findMatchingGameObjectFromList(takeInput, getCombinedGameObjectsList(player.getItems(), containerItem.getItems()), false);
        }

        if (itemToTake == null) {
            getGameOutput().appendln("Sorry - I can't find that object.");
            return;
        }

        if (itemToTake.isPermanentFixture()) {
            getGameOutput().appendln("Unfortunately you can't take this item with you.");
            return;
        }

        if (getPlayer().getItems().contains(itemToTake)) {
            getGameOutput().appendln("You already have " + getNickNameOrNameWithArticle(itemToTake));
            return;
        }

        boolean successfulRemoved = itemToTake.getParentContainer().remove(itemToTake);
        if (!successfulRemoved) {
            getGameOutput().appendln(IOUtils.surroundWithAsterisks("Error taking object... could not remove from parent container."));
        }
        getPlayer().addItem(itemToTake);
        getGameOutput().appendln(IOUtils.surroundWithAsterisks("Picks up " + getNickNameOrNameWithArticle(itemToTake)));
    }

    private void handleOpen(Statement statement) {
        if (statement.getReceivingObject() == null) {
            getGameOutput().appendln("Open what?");
            String openInput = getInputTextFromConsole();
            statement.setReceivingObject(findMatchingGameObjectFromList(openInput, getCombinedGameObjectsList(player.getItems(), player.getCurrentRoom().getItems())));
        }

        if (statement.getReceivingObject() == null) {
            getGameOutput().appendln("Sorry - I can't find that object.");
            return;
        }

        if (statement.getReceivingObject() instanceof Container) {
            ((Container) statement.getReceivingObject()).receiveOpen();
        }
        else {
            getGameOutput().appendln(IOUtils.surroundWithAsterisks("You can't open " + getNickNameOrNameWithArticle(statement.getReceivingObject())));
        }
    }

    private void handleClose(Statement statement) {
        if (statement.getReceivingObject() == null) {
            getGameOutput().appendln("Close what?");
            String closeInput = getInputTextFromConsole();
            statement.setReceivingObject(findMatchingGameObjectFromList(closeInput, getCombinedGameObjectsList(player.getItems(), player.getCurrentRoom().getItems())));
        }

        if (statement.getReceivingObject() == null) {
            getGameOutput().appendln("Sorry - I can't find that object.");
            return;
        }

        if (statement.getReceivingObject() instanceof Container) {
            ((Container) statement.getReceivingObject()).receiveClose();
        }
        else {
            getGameOutput().appendln(IOUtils.surroundWithAsterisks("You can't close " + getNickNameOrNameWithArticle(statement.getReceivingObject())));
        }
    }

    public GameOutput getGameOutput() {
        return gameOutput;
    }

    public Player getPlayer() {
        return player;
    }

    public Room getRoom(Class <? extends Room> roomClass) {
        Room roomObject = Room.ROOM_NOT_PRESENT;

        for (Room room : rooms){
            if (room.getClass().equals(roomClass)) {
                roomObject = room;
                break;
            }
        }

        if (roomObject.equals(Room.ROOM_NOT_PRESENT)) {
            try {
                // TODO - see if there's a better way to do this - maybe a factory implementation instead of reflection...

                Class<?> clazz = Class.forName(roomClass.getName());
                Constructor roomConstructor = clazz.getConstructor(Game.class);
                roomObject = (Room) roomConstructor.newInstance(this);

                rooms.add(roomObject);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return roomObject;
    }
}
