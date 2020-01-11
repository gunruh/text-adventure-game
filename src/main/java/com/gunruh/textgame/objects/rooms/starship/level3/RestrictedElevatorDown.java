package com.gunruh.textgame.objects.rooms.starship.level3;

import com.gunruh.textgame.objects.Player;
import com.gunruh.textgame.objects.items.BlueKeyCard;
import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.rooms.starship.level2.RestrictedElevatorUp;
import com.gunruh.textgame.utils.IOUtils;

public class RestrictedElevatorDown extends Room {
    private RestrictedElevatorDown() {
        super("Restricted Elevator (Level: 3)", "This elevator has magnetic authentication sensors. It will only go up or down if the keycard is nearby. The doors are on the north side.");
        setIsNewPlace(false);
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
        if (IOUtils.getCombinedGameObjectsList(Player.getInstance().getInventory(), getAvailableObjects()).contains(BlueKeyCard.getInstance())) {
            IOUtils.displayWithinAsterisks("The doors close, and you feel the elevator lurch downward. The northern doors open again.");
            return RestrictedElevatorUp.getInstance();
        }
        else {
            IOUtils.display("Sorry, the key card must be in proximity to use this elevator.");
            return Room.ROOM_NOT_PRESENT;
        }
    }

    @Override
    public Room goUp() {
        if (IOUtils.getCombinedGameObjectsList(Player.getInstance().getInventory(), getAvailableObjects()).contains(BlueKeyCard.getInstance())) {
            IOUtils.displayWithinAsterisks("The elevator cannot go any higher.");
            return Room.ROOM_NOT_PRESENT;
        }
        else {
            IOUtils.display("Sorry, the key card must be in proximity to use this elevator.");
            return Room.ROOM_NOT_PRESENT;
        }
    }
}
