package com.gunruh.textgame.objects.rooms.starship.level2.starboadSide;

import com.gunruh.textgame.Game;
import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.characters.KevinThePodBot17;

public class EscapePod17 extends Room {
    public EscapePod17(Game game) {
        super(game, "Escape Pod #17", "The seventeenth escape pod along the eastern wall of the ship." +
                "\nThere's a large viewing window on the east side of the pod. There's a bit of space gunk on the outside of the glass.");
        addItem(new KevinThePodBot17(game));
    }

    @Override
    public Room goWest() {
        return game.getRoom(StarBoardHallway.class);
    }
}
