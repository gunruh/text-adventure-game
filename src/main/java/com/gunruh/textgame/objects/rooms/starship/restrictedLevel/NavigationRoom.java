package com.gunruh.textgame.objects.rooms.starship.restrictedLevel;

import com.gunruh.textgame.Game;
import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.rooms.characters.AndersonJr;
import com.gunruh.textgame.objects.rooms.characters.Captain;
import com.gunruh.textgame.objects.rooms.starship.level0.MainElevatorUp;

public class NavigationRoom extends Room {
    private NavigationRoom() {
        super("Navigation Room", "This room is where the captain steers the ship. There's an elevator on the south wall.");

        getAvailableObjects().add(Captain.getInstance());
        getAvailableObjects().add(AndersonJr.getInstance());

        getAvailableObjects().add(new GameObject("Bucket", "A bucket for window washing.") {});
        getAvailableObjects().add(new GameObject("Squeegie", "Useful for squeeging space gunk off escape pod windows.") {});
    }

    private static NavigationRoom INSTANCE = new NavigationRoom();

    public static NavigationRoom getInstance() {
        return INSTANCE;
    }

    @Override
    public Room goSouth() {
        return RestrictedLevelHallway.getInstance();
    }
}
