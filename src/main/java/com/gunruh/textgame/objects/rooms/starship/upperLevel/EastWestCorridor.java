package com.gunruh.textgame.objects.rooms.starship.upperLevel;

import com.gunruh.textgame.objects.items.Blaster;
import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.rooms.starship.eastCorridor.StarBoardHallway;
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
        return StarBoardHallway.getInstance();
    }

    @Override
    public Room goWest() {
        return MainUpperHallway.getInstance();
    }
}
