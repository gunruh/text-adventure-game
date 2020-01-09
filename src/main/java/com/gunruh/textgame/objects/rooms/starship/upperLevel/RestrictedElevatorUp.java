package com.gunruh.textgame.objects.rooms.starship.upperLevel;

import com.gunruh.textgame.objects.Player;
import com.gunruh.textgame.objects.items.BlueKeyCard;
import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.rooms.starship.restrictedLevel.RestrictedElevatorDown;
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
        if (IOUtils.getCombinedGameObjectsList(Player.getInstance().getInventory(), getAvailableObjects()).contains(BlueKeyCard.getInstance())) {
            IOUtils.displayWithinAsterisks("The doors close, and you feel the elevator lurch upward. The northern doors open again.");
            return RestrictedElevatorDown.getInstance();
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
