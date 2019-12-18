package com.gunruh.textgame.objects;

import java.util.ArrayList;
import java.util.List;

import static com.gunruh.textgame.utils.IOUtils.display;

public class Player extends GameObject {
    private List<GameObject> inventory;
    private Room currentRoom;

    public Player(String name, String description) {
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

    public List<GameObject> getInventory() {
        return inventory;
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
}