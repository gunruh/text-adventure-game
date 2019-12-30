package com.gunruh.textgame.objects.rooms.starship;

import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.utils.IOUtils;

public class MainElevator extends Room {
    private MainElevator() {
        super("Main Elevator", "The central elevator at the heart of the ship. It goes Up and Down (as all good elevators should).");
    }

    private static MainElevator INSTANCE = new MainElevator();

    public static MainElevator getInstance() {
        return INSTANCE;
    }

    @Override
    public Room goUp() {
        IOUtils.displayWithinAsterisks("You feel heavier for a moment as the elevator glides upward. The doors open and you step out.");
        return NavigationRoom.getInstance();
    }

    @Override
    public Room goDown() {
        IOUtils.displayWithinAsterisks("You feel lighter for a moment as the elevator glides downward. The doors open and you step out.");
        return MainHallway.getInstance();
    }
}
