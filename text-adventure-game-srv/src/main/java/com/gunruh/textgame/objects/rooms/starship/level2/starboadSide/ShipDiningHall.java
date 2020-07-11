package com.gunruh.textgame.objects.rooms.starship.level2.starboadSide;

import com.gunruh.textgame.Game;
import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.rooms.starship.level2.Kitchen;
import com.gunruh.textgame.utils.IOUtils;

public class ShipDiningHall extends Room {
    public ShipDiningHall(Game game) {
        super(game, "Ship Dining Hall", "This is where the passengers eat all their food!" +
                "\nThere is one large window on the east side where you can see the vastness of outer space as you eat.");
    }

    @Override
    public Room goSouth() {
        return game.getRoom(StarBoardHallway.class);
    }

    @Override
    public Room goWest() {
        game.getGameOutput().appendln(IOUtils.surroundWithAsterisks("You found a secret entrance to the Kitchen!"));
        return game.getRoom(Kitchen.class);
    }
}
