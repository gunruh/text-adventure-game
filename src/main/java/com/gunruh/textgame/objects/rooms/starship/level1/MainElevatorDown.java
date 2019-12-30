package com.gunruh.textgame.objects.rooms.starship.level1;

import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.rooms.starship.level0.MainLowerHallway;
import com.gunruh.textgame.utils.IOUtils;

public class MainElevatorDown extends Room {
    private MainElevatorDown() {
        super("Main Elevator", "The central elevator at the heart of the ship. It goes Up and Down (as all good elevators should).");
    }

    private static MainElevatorDown INSTANCE = new MainElevatorDown();

    public static MainElevatorDown getInstance() {
        return INSTANCE;
    }

    @Override
    public Room goSouth() {
        return MainUpperHallway.getInstance();
    }

    @Override
    public Room goUp() {
        IOUtils.displayWithinAsterisks("The elevator cannot go any higher.");
        return Room.ROOM_NOT_PRESENT;
    }

    @Override
    public Room goDown() {
        IOUtils.displayWithinAsterisks("You feel lighter for a moment as the elevator glides downward. The doors open and you step out.");
        return MainLowerHallway.getInstance();
    }
}
