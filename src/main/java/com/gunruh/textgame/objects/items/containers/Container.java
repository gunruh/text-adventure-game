package com.gunruh.textgame.objects.items.containers;

import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.Player;
import com.gunruh.textgame.utils.IOUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class Container extends GameObject {
    private int itemLimit;
    private boolean isOpen = false;
    private List<GameObject> items = new ArrayList<GameObject>();

    protected Container(String name, String description, int itemLimit) {
        super(name, description);
        this.itemLimit = itemLimit;
    }

    protected Container(String name, String description, int itemLimit, boolean isPermanentFixture) {
        super(name, description, isPermanentFixture);
        this.itemLimit = itemLimit;
    }

    @Override
    public String getDescription() {
        StringBuilder descriptionBuilder = new StringBuilder(super.getDescription());

        if (isOpen) {
            if (items != null && !items.isEmpty()) {
                descriptionBuilder.append("\nContains: ");
                descriptionBuilder.append(IOUtils.capitalizeFirstLetter(IOUtils.getSentenceStringFromGameObjectsList(items)) + ".");
            }
            else {
                descriptionBuilder.append("\nContains: nothing.");
            }
        }
        else {
            descriptionBuilder.append("\nIt's currently closed.");
        }

        return descriptionBuilder.toString();
    }

    public void addItem(GameObject gameObject) {
        if (gameObject != null) {
            if (items.size() < itemLimit) {
                items.add(gameObject);
            }
            else {
                IOUtils.capitalizeFirstLetter(IOUtils.getNickNameOrNameWithArticle(this) + " cannot hold any more items.");
            }
        }
    }

    public void receiveInsertInto(GameObject actingObject) {
        if (isOpen()) {
            if (getItemCount() < getItemLimit()) {
                addItem(actingObject);
                Player.getInstance().getInventory().remove(actingObject); // remove from player inventory, since it's now inside the container.
                IOUtils.displayWithinAsterisks(IOUtils.capitalizeFirstLetter(IOUtils.getNickNameOrNameWithArticle(actingObject)) + " was put inside " + IOUtils.getNickNameOrNameWithArticle(this) + ".");
            }
            else {
                IOUtils.displayWithinAsterisks(IOUtils.capitalizeFirstLetter(IOUtils.getNickNameOrNameWithArticle(this)) + " cannot hold any more items.");
            }
        }
        else {
            IOUtils.displayWithinAsterisks(IOUtils.capitalizeFirstLetter(IOUtils.getNickNameOrNameWithArticle(this)) + " is not open.");
        }
    }

    public void receiveRemove(GameObject requestedObject) {
        if (isOpen()) {
            if (requestedObject != null) {
                if (items.contains(requestedObject)) {
                    items.remove(items.indexOf(requestedObject));
                }
                else {
                    IOUtils.capitalizeFirstLetter(IOUtils.getNickNameOrNameWithArticle(requestedObject) + "is not inside " + IOUtils.getNickNameOrNameWithArticle(this));
                }
            }
        }
        else {
            IOUtils.displayWithinAsterisks(IOUtils.capitalizeFirstLetter(IOUtils.getNickNameOrNameWithArticle(this)) + " is not open.");
        }
    }

    public void receiveClose() {
        if (isOpen()) {
            setOpen(false);
            IOUtils.displayWithinAsterisks(IOUtils.capitalizeFirstLetter(IOUtils.getNickNameOrNameWithArticle(this)) + " is now closed.");
        }
        else {
            IOUtils.displayWithinAsterisks(IOUtils.capitalizeFirstLetter(IOUtils.getNickNameOrNameWithArticle(this)) + " is already closed.");
        }
    }

    public void receiveOpen() {
        if (!isOpen()) {
            setOpen(true);
            IOUtils.displayWithinAsterisks(IOUtils.capitalizeFirstLetter(IOUtils.getNickNameOrNameWithArticle(this)) + " is now open.");
        }
        else {
            IOUtils.displayWithinAsterisks(IOUtils.capitalizeFirstLetter(IOUtils.getNickNameOrNameWithArticle(this)) + " is already open.");
        }
    }

    public int getItemLimit() {
        return itemLimit;
    }

    public void setItemLimit(int itemLimit) {
        this.itemLimit = itemLimit;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public int getItemCount() {
        return items.size();
    }

    public List<GameObject> getItems() {
        return items;
    }
}
