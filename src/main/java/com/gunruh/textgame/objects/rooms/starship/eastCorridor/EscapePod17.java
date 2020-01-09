package com.gunruh.textgame.objects.rooms.starship.eastCorridor;

import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.rooms.characters.KevinThePodBot17;

public class EscapePod17 extends Room {
    private EscapePod17() {
        super("Escape Pod #17", "The seventeenth escape pod along the eastern wall of the ship.");
        getAvailableObjects().add(KevinThePodBot17.getInstance());
    }

    private static EscapePod17 INSTANCE = new EscapePod17();

    public static EscapePod17 getInstance() {
        return INSTANCE;
    }

    @Override
    public Room goWest() {
        return StarBoardHallway.getInstance();
    }
}
