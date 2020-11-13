
package com.gunruh.textgame;

import com.gunruh.textgame.enumerations.Action;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.gunruh.textgame.utils.IOUtils.*;

public class Game {
    // TODO Add logger

    private final String gameId;
    private final GameOutput gameOutput;
    private final Player player = new Player(this, "Space Dude", "A man on a mission.");
    private final List<Room> rooms = new ArrayList<Room>();

    private Statement lastStatement = null;
    private ExpectingStatement expectingStatement = new ExpectingStatement();

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
            getGameOutput().append("\n"); // Add another line.
            getGameOutput().appendln(Constants.INTRO_TEXT);
        }
        player.enterRoom(getRoom(JanitorsQuarters.class));
    }

    public void parseInput(String input) {
        Statement statement = getStatementFromInputText(input, player.getItems(), player.getCurrentRoom().getItems());
        if (expectingStatement.isExpecting()) {
            statement = enhanceLastStatement(statement);
        }
        parseStatement(statement);
        lastStatement = statement;
    }

    private Statement enhanceLastStatement(Statement statement) {
        // Populate new data into previous statement.
        if (expectingStatement.inputString && !IOUtils.isNullOrEmpty(statement.getInputString())) {
            lastStatement.setInputString(statement.getInputString());
        }

        if (expectingStatement.action && statement.getAction() != null) {
            lastStatement.setAction(statement.getAction());
        }

        if (expectingStatement.direction && statement.getDirection() != null) {
            lastStatement.setDirection(statement.getDirection());
        }

        if (expectingStatement.actingObject && statement.getActingObject() != null) {
            lastStatement.setActingObject(statement.getActingObject());
        }

        if (expectingStatement.receivingObject && statement.getReceivingObject() != null) {
            lastStatement.setReceivingObject(statement.getReceivingObject());
        }

        if (expectingStatement.remainingString) {
            if (!IOUtils.isNullOrEmpty(statement.getRemainingString())) {
                lastStatement.setRemainingString(statement.getRemainingString());
            }
            else if (!IOUtils.isNullOrEmpty(statement.getInputString())) {
                lastStatement.setRemainingString(statement.getInputString());
            }
        }

        statement = lastStatement;

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
        if (expectingStatement.direction) {
            expectingStatement.direction = false;
        }
        else if (statement.getDirection() == null) {
            getGameOutput().appendln("Which direction?");
            expectingStatement.direction = true;
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
        if (expectingStatement.receivingObject) {
            expectingStatement.receivingObject = false;
        }
        else if (statement.getReceivingObject() == null) {
            getGameOutput().appendln("Talk to who?");
            expectingStatement.receivingObject = true;
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

        if (expectingStatement.receivingObject) {
            expectingStatement.receivingObject = false;
        }
        else if (statement.getReceivingObject() == null) {
            getGameOutput().appendln("What do you want to shoot " + getNickNameOrNameWithArticle(statement.getActingObject()) + " at?");
            expectingStatement.receivingObject = true;
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

        if (expectingStatement.receivingObject) {
            expectingStatement.receivingObject = false;
        }
        else if (statement.getReceivingObject() == null) {
            getGameOutput().appendln("Name what?");
            expectingStatement.receivingObject = true;
            return;
        }

        if (statement.getReceivingObject() == null) {
            getGameOutput().appendln("Sorry - I can't find that object.");
            return;
        }

        if (expectingStatement.remainingString) {
            expectingStatement.remainingString = false;
        }
        else if (isNullOrEmpty(statement.getRemainingString())) {
            getGameOutput().appendln("What did you want to name it?");
            expectingStatement.remainingString = true;
            return;
        }

        if (isNullOrEmpty(statement.getRemainingString())) {
            getGameOutput().appendln("Sorry - I didn't catch that name.");
            return;
        }

        statement.getReceivingObject().setNickName(statement.getRemainingString());
        getGameOutput().appendln(IOUtils.prefixWithAsterisk("The " + statement.getReceivingObject().getName() + "'s name is " + statement.getRemainingString()));
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
        GameObject containerItem = player.getCurrentRoom(); // default to current room.

        // check to see if you're dropping this item into another item.
        if (statement.getReceivingObject() != null) {
            if (statement.getActingObject() != null) {
                itemToDrop = statement.getActingObject();
                containerItem = statement.getReceivingObject();
            }
        }

        if (expectingStatement.actingObject || expectingStatement.receivingObject) {
            expectingStatement.actingObject = false;
            expectingStatement.receivingObject = false;
        }
        else if (itemToDrop == null) {
            getGameOutput().appendln("Which object?");
            expectingStatement.actingObject = true;
            expectingStatement.receivingObject = true;
            return;
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
                getGameOutput().appendln(IOUtils.prefixWithAsterisk("Drops " + getNickNameOrNameWithArticle(removedObject) + "."));
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
                getGameOutput().appendln(IOUtils.prefixWithAsterisk(capitalizeFirstLetter(getNickNameOrNameWithArticle(itemToDrop)) + " cannot be moved."));
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

        if (expectingStatement.actingObject || expectingStatement.receivingObject) {
            expectingStatement.actingObject = false;
            expectingStatement.receivingObject = false;
        }
        else if (itemToTake == null) {
            getGameOutput().appendln("Take what?");
            expectingStatement.actingObject = true;
            expectingStatement.receivingObject = true;
            return;
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
            getGameOutput().appendln(IOUtils.prefixWithAsterisk("Error taking object... could not remove from parent container."));
        }
        getPlayer().addItem(itemToTake);
        getGameOutput().appendln(IOUtils.prefixWithAsterisk("Picks up " + getNickNameOrNameWithArticle(itemToTake)));
    }

    private void handleOpen(Statement statement) {
        if (expectingStatement.receivingObject) {
            expectingStatement.receivingObject = false;
        }
        else if (statement.getReceivingObject() == null) {
            getGameOutput().appendln("Open what?");
            expectingStatement.receivingObject = true;
            return;
        }

        if (statement.getReceivingObject() == null) {
            getGameOutput().appendln("Sorry - I can't find that object.");
            return;
        }

        if (statement.getReceivingObject() instanceof Container) {
            ((Container) statement.getReceivingObject()).receiveOpen();
        }
        else {
            getGameOutput().appendln(IOUtils.prefixWithAsterisk("You can't open " + getNickNameOrNameWithArticle(statement.getReceivingObject())));
        }
    }

    private void handleClose(Statement statement) {
        if (expectingStatement.receivingObject) {
            expectingStatement.receivingObject = false;
        }
        else if (statement.getReceivingObject() == null) {
            getGameOutput().appendln("Close what?");
            expectingStatement.receivingObject = true;
            return;
        }

        if (statement.getReceivingObject() == null) {
            getGameOutput().appendln("Sorry - I can't find that object.");
            return;
        }

        if (statement.getReceivingObject() instanceof Container) {
            ((Container) statement.getReceivingObject()).receiveClose();
        }
        else {
            getGameOutput().appendln(IOUtils.prefixWithAsterisk("You can't close " + getNickNameOrNameWithArticle(statement.getReceivingObject())));
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
