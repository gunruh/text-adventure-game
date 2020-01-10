package com.gunruh.textgame.objects.rooms.starship.level2.starboadSide;

import com.gunruh.textgame.objects.Player;
import com.gunruh.textgame.objects.items.BlueKeyCard;
import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.rooms.starship.level2.EastWestCorridor;
import com.gunruh.textgame.utils.IOUtils;

public class StarBoardHallway extends Room {
    private StarBoardHallway() {
        super("Starboard Hallway", "This hallway runs north-south along the east side of the ship." +
                "\nThere are escape pods along the east wall.");
    }

    private static StarBoardHallway INSTANCE = new StarBoardHallway();

    public static StarBoardHallway getInstance() {
        return INSTANCE;
    }

    @Override
    public Room goNorth() {
        return ShipDiningHall.getInstance();
    }

    @Override
    public Room goEast() {
        return EscapePod17.getInstance();
    }

    @Override
    public Room goSouth() {
        if (Player.getInstance().getInventory().contains(BlueKeyCard.getInstance())) {
            IOUtils.displayWithinAsterisks("BEEP BEEP BOOYEEP. The door opens.");
            return ServiceAirLock.getInstance();
        }
        else {
            IOUtils.displayWithinAsterisks("BEEP BOO BEEP - You are not allowed without a card.");
            return Room.ROOM_NOT_PRESENT;
        }
    }

    @Override
    public Room goWest() {
        return EastWestCorridor.getInstance();
    }
}
