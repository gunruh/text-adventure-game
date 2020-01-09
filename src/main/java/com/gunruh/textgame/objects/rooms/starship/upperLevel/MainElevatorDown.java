package com.gunruh.textgame.objects.rooms.starship.upperLevel;

import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.rooms.starship.lowerLevel.MainElevatorUp;
import com.gunruh.textgame.utils.IOUtils;

public class MainElevatorDown extends Room {
    private MainElevatorDown() {
        super("Main Elevator", "The central elevator at the heart of the ship. It goes Up and Down (as all good elevators should). The doors are on the south side.");
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
        IOUtils.displayWithinAsterisks("The elevator cannot go any higher.");
        return Room.ROOM_NOT_PRESENT;
    }

    @Override
    public Room goDown() {
        IOUtils.displayWithinAsterisks("The doors close, and you feel lighter as the elevator glides downward. The elevator doors open to the South.");
        return MainElevatorUp.getInstance();
    }
}
