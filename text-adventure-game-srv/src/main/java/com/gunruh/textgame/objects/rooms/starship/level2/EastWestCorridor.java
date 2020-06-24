package com.gunruh.textgame.objects.rooms.starship.level2;

import com.gunruh.textgame.Game;
import com.gunruh.textgame.objects.items.Blaster;
import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.rooms.starship.level2.starboadSide.StarBoardHallway;

public class EastWestCorridor extends Room {
    public EastWestCorridor(Game game) {
        super(game, "East-West Corridor", "It's a passage that goes West and East.");
        addItem(new Blaster(game));
    }

    @Override
    public Room goEast() {
        return game.getRoom(StarBoardHallway.class);
    }

    @Override
    public Room goWest() {
        return game.getRoom(MainUpperHallway.class);
    }
}
