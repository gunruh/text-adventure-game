package com.gunruh.textgame.objects.rooms.starship.level1;

import com.gunruh.textgame.objects.rooms.Room;

public class EngineRoom extends Room {
    private EngineRoom() {
        super("Engine Room", "Here you can see the engines that propel the massive ship. There's an entryway to the north.");
    }

    private static EngineRoom INSTANCE = new EngineRoom();

    public static EngineRoom getInstance() {
        return INSTANCE;
    }

    @Override
    public Room goNorth() {
        return MainLowerHallway.getInstance();
    }
}
