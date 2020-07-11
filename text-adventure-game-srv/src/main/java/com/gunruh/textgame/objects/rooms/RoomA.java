package com.gunruh.textgame.objects.rooms;

import com.gunruh.textgame.Game;

public class RoomA extends Room {
    public RoomA(Game game) {
        super(game, "The Main Place", "You're in a place - there's a hole in the east wall. You could probably crawl through...");
    }

    @Override
    public Room goEast() {
        game.getGameOutput().appendln("You crawled through the hole - good job.");
        return game.getRoom(RoomB.class);
    }
}
