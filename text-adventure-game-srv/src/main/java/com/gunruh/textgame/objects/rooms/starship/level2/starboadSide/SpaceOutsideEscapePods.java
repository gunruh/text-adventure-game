package com.gunruh.textgame.objects.rooms.starship.level2.starboadSide;

import com.gunruh.textgame.Game;
import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.utils.IOUtils;

public class SpaceOutsideEscapePods extends Room {
    public SpaceOutsideEscapePods(Game game) {
        super(game, "Outer Space", "To your west you see the outside of the 20 escape pods, ready to be launched in an emergency." +
                "\nTo the east, you see the vastness of space. Looks pretty big.");
    }

    @Override
    public Room goWest() {
        game.getGameOutput().appendln(IOUtils.prefixWithAsterisk("The Airlock doors open, and you are pulled in by a tractor beam."));
        return game.getRoom(ServiceAirLock.class);
    }
}
