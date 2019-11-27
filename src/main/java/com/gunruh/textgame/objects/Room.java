package com.gunruh.textgame.objects;

import com.gunruh.textgame.utils.Constants;

public abstract class Room extends GameObject {
    private final String name;
    private final String description;
    private boolean isNewPlace = true;
    public static final Room ROOM_NOT_PRESENT = new Room(null, null) {};

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Room goNorth() {
        System.out.println(Constants.CANT_GO_THAT_WAY);
        return ROOM_NOT_PRESENT;
    }

    public Room goNorthEast() {
        System.out.println(Constants.CANT_GO_THAT_WAY);
        return ROOM_NOT_PRESENT;
    }

    public Room goEast() {
        System.out.println(Constants.CANT_GO_THAT_WAY);
        return ROOM_NOT_PRESENT;
    }

    public Room goSouthEast() {
        System.out.println(Constants.CANT_GO_THAT_WAY);
        return ROOM_NOT_PRESENT;
    }

    public Room goSouth() {
        System.out.println(Constants.CANT_GO_THAT_WAY);
        return ROOM_NOT_PRESENT;
    }

    public Room goSouthWest() {
        System.out.println(Constants.CANT_GO_THAT_WAY);
        return ROOM_NOT_PRESENT;
    }

    public Room goWest() {
        System.out.println(Constants.CANT_GO_THAT_WAY);
        return ROOM_NOT_PRESENT;
    }

    public Room goNorthWest() {
        System.out.println(Constants.CANT_GO_THAT_WAY);
        return ROOM_NOT_PRESENT;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getRoomDisplay() {
        return name + (isNewPlace? "\n" + getDescription() : "");
    }

    public boolean isNewPlace() {
        return isNewPlace;
    }

    public void setIsNewPlace(boolean newPlace) {
        isNewPlace = newPlace;
    }
}
