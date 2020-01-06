package com.gunruh.textgame.objects.rooms.starship.level1;

import com.gunruh.textgame.objects.Player;
import com.gunruh.textgame.objects.items.RestrictedElevatorKeyCard;
import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.rooms.starship.restrictedLevel.NavigationRoom;
import com.gunruh.textgame.objects.rooms.starship.restrictedLevel.RestrictedLevelHallway;
import com.gunruh.textgame.utils.IOUtils;

public class RestrictedElevatorUp extends Room {
    private RestrictedElevatorUp() {
        super("Restricted Elevator", "This elevator can only be used with a key card. The doors are on the north side.");
    }

    private static RestrictedElevatorUp INSTANCE = new RestrictedElevatorUp();

    public static RestrictedElevatorUp getInstance() {
        return INSTANCE;
    }

    @Override
    public Room goNorth() {
        return MainUpperHallway.getInstance();
    }

    @Override
    public Room goUp() {
        // Check if key card is in player inventory, or just in the elevator.
        if (IOUtils.getCombinedGameObjectsList(Player.getInstance().getInventory(), getAvailableObjects()).contains(RestrictedElevatorKeyCard.getInstance())) {
            return RestrictedLevelHallway.getInstance();
        }
        else {
            IOUtils.display("Sorry, the key card must be in proximity to use this elevator.");
            return Room.ROOM_NOT_PRESENT;
        }
    }

    @Override
    public Room goDown() {
        IOUtils.displayWithinAsterisks("The elevator cannot go any lower.");
        return Room.ROOM_NOT_PRESENT;
    }
}
