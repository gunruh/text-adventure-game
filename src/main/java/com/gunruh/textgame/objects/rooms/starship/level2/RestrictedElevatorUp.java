package com.gunruh.textgame.objects.rooms.starship.level2;

import com.gunruh.textgame.objects.Player;
import com.gunruh.textgame.objects.items.KeyCardBlue;
import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.rooms.starship.level3.RestrictedElevatorDown;
import com.gunruh.textgame.utils.IOUtils;

public class RestrictedElevatorUp extends Room {
    private RestrictedElevatorUp() {
        super("Restricted Elevator (Level: 2)", "This elevator has magnetic authentication sensors. It will only go up or down if the keycard is nearby. The doors are on the north side.");
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
        if (IOUtils.getCombinedGameObjectsList(Player.getInstance().getItems(), getItems()).contains(KeyCardBlue.getInstance())) {
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
        if (IOUtils.getCombinedGameObjectsList(Player.getInstance().getItems(), getItems()).contains(KeyCardBlue.getInstance())) {
            IOUtils.displayWithinAsterisks("The elevator cannot go any lower.");
            return Room.ROOM_NOT_PRESENT;
        }
        else {
            IOUtils.display("Sorry, the key card must be in proximity to use this elevator.");
            return Room.ROOM_NOT_PRESENT;
        }
    }
}
