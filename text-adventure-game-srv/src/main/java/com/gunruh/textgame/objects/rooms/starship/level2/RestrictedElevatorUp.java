package com.gunruh.textgame.objects.rooms.starship.level2;

import com.gunruh.textgame.Game;
import com.gunruh.textgame.objects.items.KeyCardBlue;
import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.rooms.starship.level3.RestrictedElevatorDown;
import com.gunruh.textgame.utils.IOUtils;

public class RestrictedElevatorUp extends Room {
    public RestrictedElevatorUp(Game game) {
        super(game, "Restricted Elevator (Level: 2)", "This elevator has magnetic authentication sensors. It will only go up or down if the keycard is nearby. The doors are on the north side.");
    }

    @Override
    public Room goNorth() {
        return game.getRoom(MainUpperHallway.class);
    }

    @Override
    public Room goUp() {
        // Check if key card is in player inventory, or just in the elevator.
        if (game.getPlayer().containsInstanceOf(KeyCardBlue.class) || this.containsInstanceOf(KeyCardBlue.class)) {
            game.getGameOutput().appendln(IOUtils.prefixWithAsterisk("The doors close, and you feel the elevator lurch upward. The northern doors open again."));
            return game.getRoom(RestrictedElevatorDown.class);
        }
        else {
            game.getGameOutput().appendln("Sorry, the key card must be in proximity to use this elevator.");
            return Room.ROOM_NOT_PRESENT;
        }
    }

    @Override
    public Room goDown() {
        if (game.getPlayer().containsInstanceOf(KeyCardBlue.class) || this.containsInstanceOf(KeyCardBlue.class)) {
            game.getGameOutput().appendln(IOUtils.prefixWithAsterisk("The elevator cannot go any lower."));
            return Room.ROOM_NOT_PRESENT;
        }
        else {
            game.getGameOutput().appendln("Sorry, the key card must be in proximity to use this elevator.");
            return Room.ROOM_NOT_PRESENT;
        }
    }
}
