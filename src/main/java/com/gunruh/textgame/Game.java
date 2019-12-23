package com.gunruh.textgame;

import com.gunruh.textgame.enumerations.Action;
import com.gunruh.textgame.objects.*;
import com.gunruh.textgame.utils.Constants;
import com.gunruh.textgame.utils.IOUtils;
import com.gunruh.textgame.utils.InputMaps;

import static com.gunruh.textgame.utils.IOUtils.*;

public class Game {
    Player player = new Player("Kevin", "A man on a mission.");

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

            Statement statement = IOUtils.getStatementFromInputList(IOUtils.getInputListFromText(input), player.getInventory(), player.getCurrentRoom().getAvailableObjects());

            if (Action.Take == statement.getAction()) {
                if (statement.getReceivingObject() == null) {
                    display("Take what?");
                    String takeInput = IOUtils.getInputText();
                    statement.setReceivingObject(getMatchingGameObjectFromList(takeInput, IOUtils.getCombinedGameObjectsList(player.getInventory(), player.getCurrentRoom().getAvailableObjects())));
                }

                if (statement.getReceivingObject() == null) {
                    display("Sorry - I can't find that object.");
                    continue;
                }

                int roomObjectIndex = player.getCurrentRoom().getAvailableObjects().indexOf(statement.getReceivingObject());
                if (roomObjectIndex != -1) {
                    GameObject takenObject = player.getCurrentRoom().getAvailableObjects().remove(roomObjectIndex);
                    player.getInventory().add(takenObject);
                    displayWithinAsterisks("Picks up " + (!isNullOrEmpty(takenObject.getNickName()) ? takenObject.getNickName() : takenObject.getName()));
                }
                else {
                    int playerInventoryIndex = player.getInventory().indexOf(statement.getReceivingObject());
                    if (playerInventoryIndex != -1) {
                        display("You already have " + (!isNullOrEmpty(statement.getReceivingObject().getNickName()) ? statement.getReceivingObject().getNickName() : ("the " + statement.getReceivingObject().getName())));
                    }
                }
            }

            if (Action.Drop == statement.getAction()) {
                if (statement.getReceivingObject() == null) {
                    display("Drop what?");
                    String takeInput = IOUtils.getInputText();
                    statement.setReceivingObject(getMatchingGameObjectFromList(takeInput, player.getInventory()));
                }

                if (statement.getReceivingObject() == null) {
                    display("Sorry - I can't find that object.");
                    continue;
                }

                int inventoryIndex = player.getInventory().indexOf(statement.getReceivingObject());
                if (inventoryIndex != -1) {
                    GameObject droppedObject = player.getInventory().remove(inventoryIndex);
                    player.getCurrentRoom().getAvailableObjects().add(droppedObject);
                    displayWithinAsterisks("Drops " + (!isNullOrEmpty(droppedObject.getNickName()) ? droppedObject.getNickName() : droppedObject.getName()));
                }
                else {
                    display("you don't have that.");
                }
            }

            else if (Action.Look == statement.getAction()) {
                if (statement.getReceivingObject() != null) {
                    displayGameObject(statement.getReceivingObject());
                }
                else {
                    displayGameObject(player.getCurrentRoom());
                }
            }

            else if (Action.Move == statement.getAction()) {
                if (statement.getDirection() == null) {
                    display("Which direction?");
                    String directionInput = IOUtils.getInputText();
                    statement.setDirection(InputMaps.directionMap.get(directionInput));
                }

                if (statement.getDirection() == null) {
                    display("Sorry - I don't know that direction.");
                    continue;
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
                    default: {
                        display("I don't know that direction.");
                        break;
                    }
                }
            }

            else {
                display("I don't know that action.");
            }
        }

        // Exiting loop
        display("That's all folks.");
    }

    private void initializeGame() {
        // Initialize Room Objects
        RoomB.getInstance().getAvailableObjects().add(new Blaster());

        display(Constants.INTRO_TEXT);
        player.enterRoom(RoomA.getInstance());
    }
}
