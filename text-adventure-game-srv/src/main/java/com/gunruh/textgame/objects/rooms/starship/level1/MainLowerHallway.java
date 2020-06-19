package com.gunruh.textgame.objects.rooms.starship.level1;

import com.gunruh.textgame.objects.rooms.Room;

public class MainLowerHallway extends Room {
    private MainLowerHallway() {
        super(game, "Main Hallway",
                "This is the main hallway on the bottom floor of the space cruiser." +
                        "\nIt runs North and South (with North at the front of the ship)." +
                        "\nThe Janitor's Closet is on the east side of the hall." +
                        "\nThe Janitor's Quarters is on the west wall.");
    }

    private static final MainLowerHallway INSTANCE = new MainLowerHallway();

    public static MainLowerHallway getInstance() {
        return INSTANCE;
    }

    @Override
    public Room goNorth() {
        return MainElevatorUp.getInstance();
    }
    
    @Override
    public Room goEast() {
    	return JanitorsCloset.getInstance();
    }

    @Override
    public Room goSouth() {
        return EngineRoom.getInstance();
    }

    @Override
    public Room goWest() {
        return JanitorsQuarters.getInstance();
    }
}
