import objects.Direction;
import objects.Room;
import objects.RoomA;
import utils.Constants;

import static utils.IOUtils.*;

public class Game {
    private Room currentRoom;

    public void run() {
        initializeGame();

        display(Constants.INTRO_TEXT);
        enterRoom(currentRoom);

        String input = null;
        while (!"quit".equalsIgnoreCase(input)) {
            input = getInput();

            if (input.toLowerCase().trim().contains("quit")) {
                continue;
            }

            if (input.toLowerCase().trim().contains("look")) {
                display(currentRoom.getName() + "\n" + currentRoom.getDescription());
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
