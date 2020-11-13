package com.gunruh.textgame.objects.rooms.starship.level3;

import com.gunruh.textgame.Game;
import com.gunruh.textgame.objects.items.KeyCardBlue;
import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.rooms.starship.level2.RestrictedElevatorUp;
import com.gunruh.textgame.utils.IOUtils;

public class RestrictedElevatorDown extends Room {
    public RestrictedElevatorDown(Game game) {
        super(game, "Restricted Elevator (Level: 3)", "This elevator has magnetic authentication sensors. It will only go up or down if the keycard is nearby. The doors are on the north side.");
        setIsNewPlace(false);
    }

    @Override
    public Room goNorth() {
        return game.getRoom(RestrictedLevelHallway.class);
    }

    @Override
    public Room goDown() {
        // Check if key card is in player inventory, or just in the elevator.
        if (game.getPlayer().containsInstanceOf(KeyCardBlue.class) || this.containsInstanceOf(KeyCardBlue.class)) {
            game.getGameOutput().appendln(IOUtils.prefixWithAsterisk("The doors close, and you feel the elevator lurch downward. The northern doors open again."));
            return game.getRoom(RestrictedElevatorUp.class);
        }
        else {
            game.getGameOutput().appendln("Sorry, the key card must be in proximity to use this elevator.");
            return Room.ROOM_NOT_PRESENT;
        }
    }

    @Override
    public Room goUp() {
        if (game.getPlayer().containsInstanceOf(KeyCardBlue.class) || this.containsInstanceOf(KeyCardBlue.class)) {
            game.getGameOutput().appendln(IOUtils.prefixWithAsterisk("The elevator cannot go any higher."));
            return Room.ROOM_NOT_PRESENT;
        }
        else {
            game.getGameOutput().appendln("Sorry, the key card must be in proximity to use this elevator.");
            return Room.ROOM_NOT_PRESENT;
        }
    }
}
