package com.gunruh.textgame.objects;

import com.gunruh.textgame.utils.Constants;
import com.gunruh.textgame.utils.IOUtils;

import java.util.ArrayList;
import java.util.Iterator;
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

    public String getRoomDisplay() {
        return getName() + (isNewPlace? "\n" + getDescription() : "");
    }

    public boolean isNewPlace() {
        return isNewPlace;
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

    public String getAvailableObjectsString() {
        StringBuilder stringBuilder = new StringBuilder();

        if (availableObjects != null) {
            Iterator<GameObject> gameObjectIterator = availableObjects.iterator();
            while (gameObjectIterator.hasNext()) {
                GameObject gameObject = gameObjectIterator.next();

                // If the list contains more than one object, and we're at the second to last object
                if (availableObjects.size() > 1 && !gameObjectIterator.hasNext()) {
                    stringBuilder.append("and ");
                }

                if (!IOUtils.isNullOrEmpty(gameObject.getNickName())) {
                    stringBuilder.append(gameObject.getNickName());
                }
                else {
                    stringBuilder.append(IOUtils.getAorAnForString(gameObject.getName()) + " " + gameObject.getName());
                }

                if (gameObjectIterator.hasNext()) {
                    if (availableObjects.size() > 2) {
                        stringBuilder.append(", ");
                    }
                    else {
                        stringBuilder.append(" ");
                    }
                }
            }
        }

        return stringBuilder.toString();
    }
}
