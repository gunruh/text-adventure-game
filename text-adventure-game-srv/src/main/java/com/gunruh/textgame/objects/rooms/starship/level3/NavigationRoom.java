package com.gunruh.textgame.objects.rooms.starship.level3;

import com.gunruh.textgame.Game;
import com.gunruh.textgame.objects.items.RubberSqueegee;
import com.gunruh.textgame.objects.items.WindowWashingCloth;
import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.characters.AndersonJr;
import com.gunruh.textgame.objects.characters.Captain;

public class NavigationRoom extends Room {
    public NavigationRoom(Game game) {
        super(game, "Navigation Room", "This room is where the captain steers the ship. There's a door on the south wall.");

        addItem(new Captain(game));
        addItem(new AndersonJr(game));

        addItem(new RubberSqueegee(game));
        addItem(new WindowWashingCloth(game));
    }

    @Override
    public Room goSouth() {
        return game.getRoom(RestrictedLevelHallway.class);
    }
}
