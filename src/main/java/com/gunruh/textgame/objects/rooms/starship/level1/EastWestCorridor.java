package com.gunruh.textgame.objects.rooms.starship.level1;

import com.gunruh.textgame.objects.items.Blaster;
import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.utils.IOUtils;

public class EastWestCorridor extends Room {
    private EastWestCorridor() {
        super("East-West Corridor", "It's a passage that goes West and East.");
        getAvailableObjects().add(new Blaster());
    }

    private static EastWestCorridor INSTANCE = new EastWestCorridor();

    public static EastWestCorridor getInstance() {
        return INSTANCE;
    }

    @Override
    public Room goEast() {
        IOUtils.display("Sorry, it's clogged. The game hasn't been made this far yet.");
        return Room.ROOM_NOT_PRESENT;
    }

    @Override
    public Room goWest() {
        return MainUpperHallway.getInstance();
    }
}
