package com.gunruh.textgame.objects.rooms.starship.level2.starboadSide;

import com.gunruh.textgame.Game;
import com.gunruh.textgame.objects.Player;
import com.gunruh.textgame.objects.items.KeyCardBlue;
import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.rooms.starship.level2.EastWestCorridor;
import com.gunruh.textgame.utils.IOUtils;

public class StarBoardHallway extends Room {
    public StarBoardHallway(Game game) {
        super(game, "Starboard Hallway", "This hallway runs north-south along the east side of the ship." +
                "\nThere are escape pods along the east wall.");
    }

    @Override
    public Room goNorth() {
        return game.getRoom(ShipDiningHall.class);
    }

    @Override
    public Room goEast() {
        return game.getRoom(EscapePod17.class);
    }

    @Override
    public Room goSouth() {
        if (game.getPlayer().containsInstanceOf(KeyCardBlue.class)) {
            game.getGameOutput().appendln(IOUtils.surroundWithAsterisks("BEEP BEEP BOOYEEP. The door opens."));
            return game.getRoom(ServiceAirLock.class);
        }
        else {
            game.getGameOutput().appendln(IOUtils.surroundWithAsterisks("BEEP BOO BEEP - You are not allowed without a card."));
            return Room.ROOM_NOT_PRESENT;
        }
    }

    @Override
    public Room goWest() {
        return game.getRoom(EastWestCorridor.class);
    }
}
