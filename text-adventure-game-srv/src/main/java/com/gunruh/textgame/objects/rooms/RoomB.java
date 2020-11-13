package com.gunruh.textgame.objects.rooms;

import com.gunruh.textgame.Game;

public class RoomB extends Room {
    public RoomB(Game game) {
        super(game, "East Room", "This room feels more eastern than the previous one did.\nThere's a hole in the wall on the west side.");
    }

    @Override
    public Room goWest() {
        game.getGameOutput().appendln("You crawled through the blaster hole.");
        return game.getRoom(RoomA.class);
    }

    @Override
    public int getEffectivenessAsBlaster() {
        return 0;
    }
}
