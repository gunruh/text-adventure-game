package com.gunruh.textgame.objects.rooms.starship.level1;

import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.items.containers.Container;
import com.gunruh.textgame.objects.items.containers.Dresser;
import com.gunruh.textgame.objects.items.containers.LightweightChest;
import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.utils.IOUtils;

public class JanitorsQuarters extends Room {
    private JanitorsQuarters() {
        super("Janitor's Quarters",
                "This place has become your home. It's a humble room, but is a good place to get some rest." +
                        "\nThere's a door on the east side of the room." +
                        "\nTry typing 'Go East' to walk through the east door.");
        addItem(new GameObject("Bed", "No time for sleep, gotta get to work!", true) {});
        addItem(Dresser.getInstance());
        addItem(LightweightChest.getInstance());

    }

    private static final JanitorsQuarters INSTANCE = new JanitorsQuarters();

    public static JanitorsQuarters getInstance() {
        return INSTANCE;
    }

    @Override
    public Room goEast() {
        IOUtils.display("Good Job! You went through the door.");
        return MainLowerHallway.getInstance();
    }
}
