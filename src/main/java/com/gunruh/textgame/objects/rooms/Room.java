package com.gunruh.textgame.objects.rooms;

import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.containerObjects.Container;
import com.gunruh.textgame.utils.Constants;
import com.gunruh.textgame.utils.ContainerUtils;
import com.gunruh.textgame.utils.IOUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class Room extends GameObject implements Container {
    public static final Room ROOM_NOT_PRESENT = new Room(null, null) {};
    private boolean isNewPlace = true;
    private int itemLimit = -1; // rooms can have infinite items by default.
    private boolean itemsVisible = true;
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

        if (getItems() != null && !getItems().isEmpty()) {
            descriptionBuilder.append("\n");
            descriptionBuilder.append(IOUtils.capitalizeFirstLetter(IOUtils.getSentenceStringFromGameObjectsList(availableObjects)));
            if (getItems().size() == 1) {
                descriptionBuilder.append(" is here.");
            }
            else {
                descriptionBuilder.append(" are here.");
            }
        }

        return descriptionBuilder.toString();
    }

    public void addItem(GameObject gameObject) {
        if (gameObject != null) {
            availableObjects.add(gameObject);
            gameObject.setParentContainer(availableObjects);
        }
    }

    public void setIsNewPlace(boolean newPlace) {
        isNewPlace = newPlace;
    }

    public void setItems(List<GameObject> items) {
        this.availableObjects = items;
    }

    @Override
    public GameObject removeItem(GameObject requestedObject) {
        return ContainerUtils.recursiveRemove(this, requestedObject);
    }

    @Override
    public void receiveClose() {
        setContainerOpen(false);
    }

    @Override
    public void receiveOpen() {
        setContainerOpen(true);
    }

    @Override
    public int getItemLimit() {
        return itemLimit;
    }

    @Override
    public void setItemLimit(int itemLimit) {
        this.itemLimit = itemLimit;
    }

    @Override
    public boolean isContainerOpen() {
        return itemsVisible;
    }

    @Override
    public void setContainerOpen(boolean open) {
        itemsVisible = open;
    }

    @Override
    public int getItemCount() {
        return availableObjects.size();
    }

    @Override
    public List<GameObject> getItems() {
        return availableObjects;
    }

}
