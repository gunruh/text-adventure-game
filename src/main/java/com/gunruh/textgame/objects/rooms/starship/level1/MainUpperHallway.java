package com.gunruh.textgame.objects.rooms.starship.level1;

import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.rooms.starship.level0.EngineRoom;
import com.gunruh.textgame.objects.rooms.starship.level0.JanitorsQuarters;
import com.gunruh.textgame.objects.rooms.starship.level0.MainElevatorUp;

public class MainUpperHallway extends Room {
    private MainUpperHallway() {
        super("Main Upper Hallway",
                "This is the main hallway on the top floor of the space cruiser." +
                        "\nIt runs North and South (with North at the front of the ship)." +
                        "\nThe passengers' quarters are along the west wall. One of them has left the door open." +
                        "\nThere is a corridor leading east.");
    }

    private static final MainUpperHallway INSTANCE = new MainUpperHallway();

    public static MainUpperHallway getInstance() {
        return INSTANCE;
    }

    @Override
    public Room goNorth() {
        return MainElevatorDown.getInstance();
    }

    @Override
    public Room goEast() {
        return EastWestCorridor.getInstance();
    }

    @Override
    public Room goSouth() {
        return RestrictedElevator.getInstance();
    }

    @Override
    public Room goWest() {
        return PassengersQuarters.getInstance();
    }
}
