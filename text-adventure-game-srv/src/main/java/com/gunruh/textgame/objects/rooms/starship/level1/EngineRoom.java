package com.gunruh.textgame.objects.rooms.starship.level1;

import com.gunruh.textgame.Game;
import com.gunruh.textgame.objects.rooms.Room;

public class EngineRoom extends Room {
    public EngineRoom(Game game) {
        super(game, "Engine Room", "Here you can see the engines that propel the massive ship. There's an entryway to the north.");
    }

    @Override
    public Room goNorth() {
        return game.getRoom(MainLowerHallway.class);
    }
}
