package com.gunruh.textgame.objects.rooms.starship.level2;

import com.gunruh.textgame.Game;
import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.items.KeyCardBlue;
import com.gunruh.textgame.objects.rooms.Room;

public class MainUpperHallway extends Room {
    public MainUpperHallway(Game game) {
        super(game, "Main Upper Hallway",
                "This is the main north-south hallway on the top floor of the space cruiser." +
                        "\nThe passengers' quarters are along the west wall. One of them has left the door open." + 
                        "\nThe Kitchen's double doors are in the northeast corner." +
                        "\nThere is a corridor leading east.");

        addItem(new GameObject(game, "Eye-catching Poster", "It says: \"Bring-Your-Child-To-Work Day is TODAY!\"") {});
        addItem(new GameObject(game, "Handwritten Note", "It says: \"REMEMBER: Free CORN-DOGS after lunch! -Rufus") {});
        addItem(new KeyCardBlue(game));
    }

    @Override
    public Room goNorth() {
        return game.getRoom(MainElevatorDown.class);
    }
    
    @Override
    public Room goNorthEast() {
    	return game.getRoom(Kitchen.class);
    }

    @Override
    public Room goEast() {
        return game.getRoom(EastWestCorridor.class);
    }

    @Override
    public Room goSouth() {
        return game.getRoom(RestrictedElevatorUp.class);
    }

    @Override
    public Room goWest() {
        return game.getRoom(PassengersQuarters.class);
    }
}
