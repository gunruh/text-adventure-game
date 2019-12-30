package com.gunruh.textgame.objects.rooms;

import static com.gunruh.textgame.utils.IOUtils.display;

public class RoomB extends Room {
    private RoomB() {
        super("East Room", "This room feels more eastern than the previous one did.\nThere's a hole which appears to have been blasted open on the west side.");
    }

    public static final RoomB INSTANCE = new RoomB();

    public static RoomB getInstance() {
        return INSTANCE;
    }

    @Override
    public Room goWest() {
        display("You crawled through the blaster hole.");
        return RoomA.getInstance();
    }

    @Override
    public int getEffectivenessAsBlaster() {
        return 0;
    }
}
