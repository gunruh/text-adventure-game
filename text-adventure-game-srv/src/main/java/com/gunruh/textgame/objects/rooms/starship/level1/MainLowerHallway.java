package com.gunruh.textgame.objects.rooms.starship.level1;

import com.gunruh.textgame.Game;
import com.gunruh.textgame.objects.rooms.Room;

public class MainLowerHallway extends Room {
    public MainLowerHallway(Game game) {
        super(game, "Main Hallway",
                "This is the main hallway on the bottom floor of the space cruiser." +
                        "\nIt runs North and South (with North at the front of the ship)." +
                        "\nThe Janitor's Closet is on the east side of the hall." +
                        "\nThe Janitor's Quarters is on the west wall.");
    }

    @Override
    public Room goNorth() {
        return game.getRoom(MainElevatorUp.class);
    }
    
    @Override
    public Room goEast() {
    	return game.getRoom(JanitorsCloset.class);
    }

    @Override
    public Room goSouth() {
        return game.getRoom(EngineRoom.class);
    }

    @Override
    public Room goWest() {
        return game.getRoom(JanitorsQuarters.class);
    }
}
