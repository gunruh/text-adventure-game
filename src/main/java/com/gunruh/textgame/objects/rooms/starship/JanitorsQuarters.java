package com.gunruh.textgame.objects.rooms.starship;

import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.rooms.Room;

public class JanitorsQuarters extends Room {
    private JanitorsQuarters() {
        super("Janitor's Quarters",
                "This place has become your home. It's a humble room, but is a good place to get some rest." +
                        "\nThere's a door on the east wall.");
        getAvailableObjects().add(new GameObject("Bed", "No time for sleep, gotta get to work!", true) {});
        getAvailableObjects().add(new GameObject("Dresser", "Just a basic dresser. Standard issue for janitors such as yourself.", true) {});
    }

    private static final JanitorsQuarters INSTANCE = new JanitorsQuarters();

    public static JanitorsQuarters getInstance() {
        return INSTANCE;
    }

    @Override
    public Room goEast() {
        return MainHallway.getInstance();
    }
}
