package com.gunruh.textgame.objects.rooms.starship.restrictedLevel;

import com.gunruh.textgame.objects.Player;
import com.gunruh.textgame.objects.items.RestrictedElevatorKeyCard;
import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.rooms.starship.level1.MainUpperHallway;
import com.gunruh.textgame.utils.IOUtils;

public class RestrictedElevatorDown extends Room {
    private RestrictedElevatorDown() {
        super("Restricted Elevator", "This elevator can only be used with a key card. The doors are on the north side.");
    }

    private static RestrictedElevatorDown INSTANCE = new RestrictedElevatorDown();

    public static RestrictedElevatorDown getInstance() {
        return INSTANCE;
    }

    @Override
    public Room goNorth() {
        return RestrictedLevelHallway.getInstance();
    }

    @Override
    public Room goDown() {
        // Check if key card is in player inventory, or just in the elevator.
        if (IOUtils.getCombinedGameObjectsList(Player.getInstance().getInventory(), getAvailableObjects()).contains(RestrictedElevatorKeyCard.getInstance())) {
            return MainUpperHallway.getInstance();
        }
        else {
            IOUtils.display("Sorry, the key card must be in proximity to use this elevator.");
            return Room.ROOM_NOT_PRESENT;
        }
    }

    @Override
    public Room goUp() {
        IOUtils.displayWithinAsterisks("The elevator cannot go any higher.");
        return Room.ROOM_NOT_PRESENT;
    }
}
