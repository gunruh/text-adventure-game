package com.gunruh.textgame.objects.rooms.starship.level2.starboadSide;

import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.utils.IOUtils;

public class SpaceOutsideEscapePods extends Room {
    private SpaceOutsideEscapePods() {
        super("Outer Space", "To your west you see the outside of the 20 escape pods, ready to be launched in an emergency." +
                "\nTo the east, you see the vastness of space. Looks pretty big.");
    }

    private static SpaceOutsideEscapePods INSTANCE = new SpaceOutsideEscapePods();

    public static SpaceOutsideEscapePods getInstance() {
        return INSTANCE;
    }

    @Override
    public Room goWest() {
        IOUtils.displayWithinAsterisks(outputBuffer, "The Airlock doors open, and you are pulled in by a tractor beam.");
        return ServiceAirLock.getInstance();
    }
}
