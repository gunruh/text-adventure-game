package com.gunruh.textgame.objects.rooms.starship;

import com.gunruh.textgame.objects.rooms.Room;

public class MainHallway extends Room {
    private MainHallway() {
        super("Main Hallway",
                "This is the main hallway on the bottom floor of the space cruiser." +
                        "\nIt runs North and South (with North at the front of the ship)." +
                        "\nThe Janitor's Quarters room is on the west wall.");
    }

    private static final MainHallway INSTANCE = new MainHallway();

    public static MainHallway getInstance() {
        return INSTANCE;
    }

    @Override
    public Room goNorth() {
        return MainElevator.getInstance();
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
