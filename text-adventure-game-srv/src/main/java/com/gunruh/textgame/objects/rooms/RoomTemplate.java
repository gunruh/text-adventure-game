package com.gunruh.textgame.objects.rooms;

public class RoomTemplate extends Room {
    private RoomTemplate() {
        super("Name", "Description");
    }

    private static final RoomTemplate INSTANCE = new RoomTemplate();

    public static RoomTemplate getInstance() {
        return INSTANCE;
    }
}
