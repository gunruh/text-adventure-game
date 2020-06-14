package com.gunruh.textgame.objects;

import com.gunruh.textgame.objects.containerObjects.Container;
import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.utils.ContainerUtils;

import java.util.ArrayList;
import java.util.List;

import static com.gunruh.textgame.utils.IOUtils.display;

public class Player extends GameObject implements Container {
    private static Player INSTANCE = new Player("Space Dude", "A man on a mission.");

    public static Player getInstance() {
        return INSTANCE;
    }

    private List<GameObject> inventory;
    private Room currentRoom;
    private int itemLimit = -1; // default item limit is infinite;

    private Player(String name, String description) {
        super(name, description);
        this.inventory = new ArrayList<GameObject>();
        this.currentRoom = Room.ROOM_NOT_PRESENT;
    }

    public void enterRoom(Room room) {
        if (room.equals(Room.ROOM_NOT_PRESENT)) {
            // do nothing
        }
        else {
            currentRoom = room;
            display(room.getRoomDisplay());
            room.setIsNewPlace(false);
        }
    }

    public void takeItem(GameObject gameObject) {
        if (gameObject != null) {
            addItem(gameObject);
            gameObject.setParentContainer(inventory);
        }
    }

    public void setInventory(List<GameObject> inventory) {
        this.inventory = inventory;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    @Override
    public void addItem(GameObject gameObject) {
        inventory.add(gameObject);
    }

    @Override
    public GameObject removeItem(GameObject requestedObject) {
        return ContainerUtils.recursiveRemove(this, requestedObject);
    }

    @Override
    public void receiveClose() {
        // do nothing
    }

    @Override
    public void receiveOpen() {
        // do nothing
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
        return true;
    }

    @Override
    public void setContainerOpen(boolean open) {
        // do nothing
    }

    @Override
    public int getItemCount() {
        return inventory.size();
    }

    @Override
    public List<GameObject> getItems() {
        return inventory;
    }
}
