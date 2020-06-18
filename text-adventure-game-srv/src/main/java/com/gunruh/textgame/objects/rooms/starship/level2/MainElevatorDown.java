package com.gunruh.textgame.objects.rooms.starship.level2;

import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.rooms.starship.level1.MainElevatorUp;
import com.gunruh.textgame.utils.IOUtils;

public class MainElevatorDown extends Room {
    private MainElevatorDown() {
        super("Main Elevator (Level: 2)", "The central elevator at the heart of the ship. It goes Up and Down (as all good elevators should). The doors are on the south side.");
        setIsNewPlace(false);
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
        IOUtils.displayWithinAsterisks(outputBuffer, "The elevator cannot go any higher.");
        return Room.ROOM_NOT_PRESENT;
    }

    @Override
    public Room goDown() {
        IOUtils.displayWithinAsterisks(outputBuffer, "The doors close, and you feel lighter as the elevator glides downward. The elevator doors open to the South.");
        return MainElevatorUp.getInstance();
    }
}
