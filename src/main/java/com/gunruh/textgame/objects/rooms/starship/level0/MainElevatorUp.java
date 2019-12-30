package com.gunruh.textgame.objects.rooms.starship.level0;

import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.rooms.starship.level1.MainUpperHallway;
import com.gunruh.textgame.utils.IOUtils;

public class MainElevatorUp extends Room {
    private MainElevatorUp() {
        super("Main Elevator", "The central elevator at the heart of the ship. It goes Up and Down (as all good elevators should).");
    }

    private static MainElevatorUp INSTANCE = new MainElevatorUp();

    public static MainElevatorUp getInstance() {
        return INSTANCE;
    }

    @Override
    public Room goSouth() {
        return MainLowerHallway.getInstance();
    }

    @Override
    public Room goUp() {
        IOUtils.displayWithinAsterisks("You feel heavier for a moment as the elevator glides upward. The doors open and you step out.");
        return MainUpperHallway.getInstance();
    }

    @Override
    public Room goDown() {
        IOUtils.displayWithinAsterisks("The elevator cannot go any lower.");
        return Room.ROOM_NOT_PRESENT;
    }
}
