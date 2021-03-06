package com.gunruh.textgame.objects.rooms.starship.level2;

import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.containerObjects.ContainerIMPL;
import com.gunruh.textgame.objects.rooms.Room;

public class PassengersQuarters extends Room {
    private PassengersQuarters() {
        super("Passenger's Quarters", "A nice-looking room for passengers to live in.");
        addItem(new GameObject("Luxury Bed", "It looks cozy, but you shouldn't sleep here.", true) {});
        addItem(new ContainerIMPL("Fancy Dresser", "Hand-carved dresser from space trees.", 5, true) {});
    }

    private static PassengersQuarters INSTANCE = new PassengersQuarters();

    public static PassengersQuarters getInstance() {
        return INSTANCE;
    }

    @Override
    public Room goEast() {
        return MainUpperHallway.getInstance();
    }
}
