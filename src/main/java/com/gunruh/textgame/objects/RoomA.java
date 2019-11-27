package com.gunruh.textgame.objects;

import com.gunruh.textgame.utils.IOUtils;

public class RoomA extends Room {
    private RoomA() {
        super("The Main Place", "You're in a place - there's a hole in the east wall. You could probably crawl through...");
    }

    private static final RoomA INSTANCE = new RoomA();

    public static RoomA getInstance() {
        return INSTANCE;
    }

    @Override
    public Room goEast() {
        IOUtils.display("You crawled through the whole - good job.");
        return RoomB.getInstance();
    }
}
