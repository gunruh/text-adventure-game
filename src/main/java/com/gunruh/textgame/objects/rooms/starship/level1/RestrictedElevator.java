package com.gunruh.textgame.objects.rooms.starship.level1;

import com.gunruh.textgame.objects.rooms.Room;

public class RestrictedElevator extends Room {
    private RestrictedElevator() {
        super("Restricted Elevator", "This elevator can only be accessed with the right code.");
    }

    private static RestrictedElevator INSTANCE = new RestrictedElevator();

    public static RestrictedElevator getInstance() {
        return INSTANCE;
    }

    @Override
    public Room goNorth() {
        return MainUpperHallway.getInstance();
    }
}
