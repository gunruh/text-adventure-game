package com.gunruh.textgame.objects.rooms.starship.level3;

import com.gunruh.textgame.objects.items.RubberSqueegee;
import com.gunruh.textgame.objects.items.WindowWashingCloth;
import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.rooms.characters.AndersonJr;
import com.gunruh.textgame.objects.rooms.characters.Captain;

public class NavigationRoom extends Room {
    private NavigationRoom() {
        super("Navigation Room", "This room is where the captain steers the ship. There's a door on the south wall.");

        getAvailableObjects().add(Captain.getInstance());
        getAvailableObjects().add(AndersonJr.getInstance());

        getAvailableObjects().add(RubberSqueegee.getInstance());
        getAvailableObjects().add(WindowWashingCloth.getInstance());
    }

    private static NavigationRoom INSTANCE = new NavigationRoom();

    public static NavigationRoom getInstance() {
        return INSTANCE;
    }

    @Override
    public Room goSouth() {
        return RestrictedLevelHallway.getInstance();
    }
}
