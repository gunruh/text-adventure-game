package com.gunruh.textgame.objects.rooms.starship.level0;

import com.gunruh.textgame.objects.rooms.Room;

public class MainHallwayLevel0 extends Room {
    private MainHallwayLevel0() {
        super("Main Hallway",
                "This is the main hallway on the bottom floor of the space cruiser." +
                        "\nIt runs North and South (with North at the front of the ship)." +
                        "\nThe Janitor's Quarters room is on the west wall.");
    }

    private static final MainHallwayLevel0 INSTANCE = new MainHallwayLevel0();

    public static MainHallwayLevel0 getInstance() {
        return INSTANCE;
    }

    @Override
    public Room goNorth() {
        return MainElevatorUp.getInstance();
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
