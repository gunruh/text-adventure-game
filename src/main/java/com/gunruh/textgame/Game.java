package com.gunruh.textgame;

import com.gunruh.textgame.objects.Direction;
import com.gunruh.textgame.objects.Room;
import com.gunruh.textgame.objects.RoomA;
import com.gunruh.textgame.utils.Constants;
import com.gunruh.textgame.utils.IOUtils;

import static com.gunruh.textgame.utils.IOUtils.display;
import static com.gunruh.textgame.utils.IOUtils.parseDirection;

public class Game {
    private Room currentRoom;

    public void run() {
        initializeGame();

        display(Constants.INTRO_TEXT);
        enterRoom(currentRoom);

        String input = null;
        while (!"quit".equalsIgnoreCase(input)) {
            input = IOUtils.getInputText();

            if (input.toLowerCase().trim().contains("quit")) {
                continue;
            }

            if (input.toLowerCase().trim().contains("look")) {
                display(currentRoom.getName() + "\n" + currentRoom.getDescription());
            }
            else if (input.toLowerCase().trim().contains("take")) {
            	if (input.toLowerCase().trim().contains("blaster")) {
            		display("picked up blaster...kind of...");
            	}
            	else {
            		display("what's that sweet babe?");
            	}
            }
            else {
                Direction direction = parseDirection(input);
                switch (direction) {
                    case North: {
                        enterRoom(currentRoom.goNorth());
                        break;
                    }
                    case NorthEast: {
                        enterRoom(currentRoom.goNorthEast());
                        break;
                    }
                    case East: {
                        enterRoom(currentRoom.goEast());
                        break;
                    }
                    case SouthEast: {
                        enterRoom(currentRoom.goSouthEast());
                        break;
                    }
                    case South: {
                        enterRoom(currentRoom.goSouth());
                        break;
                    }
                    case SouthWest: {
                        enterRoom(currentRoom.goSouthWest());
                        break;
                    }
                    case West: {
                        enterRoom(currentRoom.goWest());
                        break;
                    }
                    case NorthWest: {
                        enterRoom(currentRoom.goNorthWest());
                        break;
                    }
                    default: {
                        display("I don't know that direction.");
                        break;
                    }
                }
            }
        }

        // Exiting loop
        display("That's all folks.");
    }

    private void enterRoom(Room room) {
        if (room.equals(Room.ROOM_NOT_PRESENT)) {
            // do nothing
        }
        else {
            currentRoom = room;
            display(room.getRoomDisplay());
            room.setIsNewPlace(false);
        }
    }

    private void initializeGame() {
        currentRoom = RoomA.getInstance();
    }
}
