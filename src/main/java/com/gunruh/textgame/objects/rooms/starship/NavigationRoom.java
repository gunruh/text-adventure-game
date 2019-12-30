package com.gunruh.textgame.objects.rooms.starship;

import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.rooms.starship.level0.MainElevatorUp;

public class NavigationRoom extends Room {
    private NavigationRoom() {
        super("Navigation Room", "This room is where the captain steers the ship. There's an elevator on the south wall.");
    }

    private static NavigationRoom INSTANCE = new NavigationRoom();

    public static NavigationRoom getInstance() {
        return INSTANCE;
    }

    @Override
    public Room goSouth() {
        return MainElevatorUp.getInstance();
    }
}
