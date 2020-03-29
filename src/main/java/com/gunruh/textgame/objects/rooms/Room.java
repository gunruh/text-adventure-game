package com.gunruh.textgame.objects.rooms;

import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.utils.Constants;
import com.gunruh.textgame.utils.IOUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class Room extends GameObject {
    private boolean isNewPlace = true;
    public static final Room ROOM_NOT_PRESENT = new Room(null, null) {};
    List<GameObject> availableObjects = new ArrayList<GameObject>();

    public Room(String name, String description) {
        super(name, description);
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

    public Room goUp() {
        System.out.println(Constants.CANT_GO_THAT_WAY);
        return ROOM_NOT_PRESENT;
    }

    public Room goDown() {
        System.out.println(Constants.CANT_GO_THAT_WAY);
        return ROOM_NOT_PRESENT;
    }

    public String getRoomDisplay() {
        return getName() + (isNewPlace? "\n" + getDescription() : "");
    }

    public boolean isNewPlace() {
        return isNewPlace;
    }

    @Override
    public String getDescription() {
        StringBuilder descriptionBuilder = new StringBuilder(super.getDescription());

        if (getAvailableObjects() != null && !getAvailableObjects().isEmpty()) {
            descriptionBuilder.append("\n");
            descriptionBuilder.append(IOUtils.capitalizeFirstLetter(IOUtils.getObjectListAsString(availableObjects)));
            if (getAvailableObjects().size() == 1) {
                descriptionBuilder.append(" is here.");
            }
            else {
                descriptionBuilder.append(" are here.");
            }
        }

        return descriptionBuilder.toString();
    }

    public void setIsNewPlace(boolean newPlace) {
        isNewPlace = newPlace;
    }

    public List<GameObject> getAvailableObjects() {
        return availableObjects;
    }

    public void setAvailableObjects(List<GameObject> availableObjects) {
        this.availableObjects = availableObjects;
    }

}
