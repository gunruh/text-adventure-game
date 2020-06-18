package com.gunruh.textgame.objects.rooms.starship.level2.starboadSide;

import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.rooms.starship.level2.Kitchen;
import com.gunruh.textgame.utils.IOUtils;

public class ShipDiningHall extends Room {
    private ShipDiningHall() {
        super("Ship Dining Hall", "This is where the passengers eat all their food!" +
                "\nThere is one large window on the east side where you can see the vastness of outer space as you eat.");
    }

    private static ShipDiningHall INSTANCE = new ShipDiningHall();

    public static ShipDiningHall getInstance() {
        return INSTANCE;
    }

    @Override
    public Room goSouth() {
        return StarBoardHallway.getInstance();
    }

    @Override
    public Room goWest() {
        IOUtils.displayWithinAsterisks("You found a secret entrance to the Kitchen!");
        return Kitchen.getInstance();
    }
}
