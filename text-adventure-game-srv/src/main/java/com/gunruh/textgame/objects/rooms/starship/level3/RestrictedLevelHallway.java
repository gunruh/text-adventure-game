package com.gunruh.textgame.objects.rooms.starship.level3;

import com.gunruh.textgame.objects.rooms.Room;

public class RestrictedLevelHallway extends Room {
    private RestrictedLevelHallway() {
        super(game, "Captain's Hallway", "This north-south hallway looks like its used for very important people...");
    }

    private static RestrictedLevelHallway INSTANCE = new RestrictedLevelHallway();

    public static RestrictedLevelHallway getInstance() {
        return INSTANCE;
    }

    @Override
    public Room goNorth() {
        return NavigationRoom.getInstance();
    }

    @Override
    public Room goSouth() {
        return RestrictedElevatorDown.getInstance();
    }
}
