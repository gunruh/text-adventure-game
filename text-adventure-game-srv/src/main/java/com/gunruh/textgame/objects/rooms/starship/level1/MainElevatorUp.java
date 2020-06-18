package com.gunruh.textgame.objects.rooms.starship.level1;

import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.rooms.starship.level2.MainElevatorDown;
import com.gunruh.textgame.utils.IOUtils;

public class MainElevatorUp extends Room {
    private MainElevatorUp() {
        super("Main Elevator (Level: 1)", "The central elevator at the heart of the ship. It goes Up and Down (as all good elevators should). The doors are on the south side.");
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
        IOUtils.displayWithinAsterisks(outputBuffer, "The doors close and you feel heavier as the elevator glides upward. The elevator doors open to the South.");
        return MainElevatorDown.getInstance();
    }

    @Override
    public Room goDown() {
        IOUtils.displayWithinAsterisks(outputBuffer, "The elevator cannot go any lower.");
        return Room.ROOM_NOT_PRESENT;
    }
}
