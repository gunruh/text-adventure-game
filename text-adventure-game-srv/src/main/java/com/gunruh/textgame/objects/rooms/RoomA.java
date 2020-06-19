package com.gunruh.textgame.objects.rooms;

public class RoomA extends Room {
    private RoomA() {
        super(game, "The Main Place", "You're in a place - there's a hole in the east wall. You could probably crawl through...");
    }

    private static final RoomA INSTANCE = new RoomA();

    public static RoomA getInstance() {
        return INSTANCE;
    }

    @Override
    public Room goEast() {
        game.getGameOutput().appendln("You crawled through the hole - good job.");
        return RoomB.getInstance();
    }
}
