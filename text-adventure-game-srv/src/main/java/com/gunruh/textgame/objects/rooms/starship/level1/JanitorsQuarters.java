package com.gunruh.textgame.objects.rooms.starship.level1;

import com.gunruh.textgame.Game;
import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.containerObjects.Dresser;
import com.gunruh.textgame.objects.containerObjects.LightweightChest;
import com.gunruh.textgame.objects.rooms.Room;

public class JanitorsQuarters extends Room {
    public JanitorsQuarters(Game game) {
        super(game, "Janitor's Quarters",
                "This place has become your home. It's a humble room, but is a good place to get some rest." +
                        "\nThere's a door on the east side of the room." +
                        "\nTry typing 'Go East' to walk through the east door.");
        addItem(new GameObject(game, "Bed", "No time for sleep, gotta get to work!", true) {});
        addItem(new Dresser(game));
        addItem(new LightweightChest(game));

    }

    @Override
    public Room goEast() {
        game.getGameOutput().appendln("Good Job! You went through the door.");
        return game.getRoom(MainLowerHallway.class);
    }
}
