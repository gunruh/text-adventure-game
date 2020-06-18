package com.gunruh.textgame.objects.rooms.starship.level2;

import com.gunruh.textgame.objects.items.Blaster;
import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.rooms.starship.level2.starboadSide.StarBoardHallway;

public class EastWestCorridor extends Room {
    private EastWestCorridor() {
        super("East-West Corridor", "It's a passage that goes West and East.");
        addItem(new Blaster());
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
