package com.gunruh.textgame.objects.rooms.starship.restrictedLevel;

import com.gunruh.textgame.objects.items.RestrictedElevatorKeyCard;
import com.gunruh.textgame.objects.rooms.Room;

public class RestrictedLevelHallway extends Room {
    private RestrictedLevelHallway() {
        super("Captain's Hallway", "This hallway allows authorized personnel access only.");
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
