package com.gunruh.textgame.objects.rooms.starship.lowerLevel;

import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.utils.IOUtils;

public class JanitorsQuarters extends Room {
    private JanitorsQuarters() {
        super("Janitor's Quarters",
                "This place has become your home. It's a humble room, but is a good place to get some rest." +
                        "\nThere's a door on the east side of the room." +
                        "\nTry typing 'Go East' to walk through the east door.");
        getAvailableObjects().add(new GameObject("Bed", "No time for sleep, gotta get to work!", true) {});
        getAvailableObjects().add(new GameObject("Dresser", "Just a basic dresser. Standard issue for janitors such as yourself.", true) {});
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
