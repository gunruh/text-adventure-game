package com.gunruh.textgame.objects.rooms.starship.eastCorridor;

import com.gunruh.textgame.objects.rooms.Room;

public class ServiceAirLock extends Room {
    private ServiceAirLock() {
        super("Service AirLock", "This is the gateway to outer space!" +
                "\nJanitor's such as yourself often come through here to clean the escape pod windows.");
    }

    private static ServiceAirLock INSTANCE = new ServiceAirLock();

    public static ServiceAirLock getInstance() {
        return INSTANCE;
    }

    @Override
    public Room goNorth() {
        return StarBoardHallway.getInstance();
    }
}
