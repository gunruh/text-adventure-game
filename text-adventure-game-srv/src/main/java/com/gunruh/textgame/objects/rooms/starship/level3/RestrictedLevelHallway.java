package com.gunruh.textgame.objects.rooms.starship.level3;

import com.gunruh.textgame.Game;
import com.gunruh.textgame.objects.rooms.Room;

public class RestrictedLevelHallway extends Room {
    public RestrictedLevelHallway(Game game) {
        super(game, "Captain's Hallway", "This north-south hallway looks like its used for very important people...");
    }

    @Override
    public Room goNorth() {
        return game.getRoom(NavigationRoom.class);
    }

    @Override
    public Room goSouth() {
        return game.getRoom(RestrictedElevatorDown.class);
    }
}
