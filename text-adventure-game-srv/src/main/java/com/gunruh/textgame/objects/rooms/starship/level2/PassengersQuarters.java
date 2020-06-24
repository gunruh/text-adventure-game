package com.gunruh.textgame.objects.rooms.starship.level2;

import com.gunruh.textgame.Game;
import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.containerObjects.ContainerObject;
import com.gunruh.textgame.objects.rooms.Room;

public class PassengersQuarters extends Room {
    public PassengersQuarters(Game game) {
        super(game, "Passenger's Quarters", "A nice-looking room for passengers to live in.");
        addItem(new GameObject(game, "Luxury Bed", "It looks cozy, but you shouldn't sleep here.", true) {});
        addItem(new ContainerObject(game, "Fancy Dresser", "Hand-carved dresser from space trees.", 5, true) {});
    }

    @Override
    public Room goEast() {
        return game.getRoom(MainUpperHallway.class);
    }
}
