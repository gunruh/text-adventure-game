package com.gunruh.textgame.objects.rooms.starship.level2;

import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.items.KeyCardBlue;
import com.gunruh.textgame.objects.rooms.Room;

public class MainUpperHallway extends Room {
    private MainUpperHallway() {
        super("Main Upper Hallway",
                "This is the main north-south hallway on the top floor of the space cruiser." +
                        "\nThe passengers' quarters are along the west wall. One of them has left the door open." + 
                        "\nThe Kitchen's double doors are in the northeast corner." +
                        "\nThere is a corridor leading east.");

        addItem(new GameObject("Eye-catching Poster", "It says: \"Bring-Your-Child-To-Work Day is TODAY!\"") {});
        addItem(new GameObject("Handwritten Note", "It says: \"REMEMBER: Free CORN-DOGS after lunch! -Rufus") {});
        addItem(KeyCardBlue.getInstance());
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
    public Room goNorthEast() {
    	return Kitchen.getInstance();
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
