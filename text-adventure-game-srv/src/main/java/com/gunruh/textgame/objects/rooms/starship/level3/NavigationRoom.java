package com.gunruh.textgame.objects.rooms.starship.level3;

import com.gunruh.textgame.objects.items.RubberSqueegee;
import com.gunruh.textgame.objects.items.WindowWashingCloth;
import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.characters.AndersonJr;
import com.gunruh.textgame.objects.characters.Captain;

public class NavigationRoom extends Room {
    private NavigationRoom() {
        super(game, "Navigation Room", "This room is where the captain steers the ship. There's a door on the south wall.");

        addItem(Captain.getInstance());
        addItem(AndersonJr.getInstance());

        addItem(RubberSqueegee.getInstance());
        addItem(WindowWashingCloth.getInstance());
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
