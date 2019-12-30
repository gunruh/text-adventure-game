package com.gunruh.textgame.objects.rooms.starship.level1;

import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.rooms.Room;

public class MainUpperHallway extends Room {
    private MainUpperHallway() {
        super("Main Upper Hallway",
                "This is the main hallway on the top floor of the space cruiser." +
                        "\nIt runs North and South (with North at the front of the ship)." +
                        "\nThe passengers' quarters are along the west wall. One of them has left the door open." +
                        "\nThere is a corridor leading east.");

        getAvailableObjects().add(new GameObject("Eye-catching Poster", "It says: \"Bring-Your-Child-To-Work Day is TODAY!\"") {});
        getAvailableObjects().add(new GameObject("Handwritten Note", "It says: \"REMEMBER: Free CORN-DOGS after lunch! -Rufus") {});
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
        return RestrictedElevatorUp.getInstance();
    }

    @Override
    public Room goWest() {
        return PassengersQuarters.getInstance();
    }
}
