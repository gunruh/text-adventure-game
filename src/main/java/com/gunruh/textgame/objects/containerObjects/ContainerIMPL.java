package com.gunruh.textgame.objects.containerObjects;

import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.Player;
import com.gunruh.textgame.utils.IOUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class ContainerIMPL extends GameObject implements Container {
    private int itemLimit;
    private boolean isContainerOpen = false;
    private List<GameObject> items = new ArrayList<GameObject>();

    protected ContainerIMPL(String name, String description, int itemLimit) {
        this(name, description, itemLimit, false);
    }

    protected ContainerIMPL(String name, String description, int itemLimit, boolean isPermanentFixture) {
        this(name, description, itemLimit, isPermanentFixture, false);
    }

    protected ContainerIMPL(String name, String description, int itemLimit, boolean isPermanentFixture, boolean isContainerOpen) {
        super(name, description, isPermanentFixture);
        this.itemLimit = itemLimit;
        this.isContainerOpen = isContainerOpen;
    }

    @Override
    public String getDescription() {
        StringBuilder descriptionBuilder = new StringBuilder(super.getDescription());

        if (isContainerOpen) {
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

    // This method will always add the item - if you want to first check size limit, open/close, etc., then use 'receiveInsertInto()'.
    public void addItem(GameObject gameObject) {
        if (gameObject != null) {
            items.add(gameObject);
            gameObject.setParentContainer(items); // set its parent container to this container's items.
        }
    }

    public boolean receiveInsertInto(GameObject actingObject) {
        boolean isReceiveSuccess = false;

        if (isContainerOpen()) {
            if (getItemCount() < getItemLimit()) {
                addItem(actingObject);
                IOUtils.displayWithinAsterisks(IOUtils.capitalizeFirstLetter(IOUtils.getNickNameOrNameWithArticle(actingObject)) + " was put inside " + IOUtils.getNickNameOrNameWithArticle(this) + ".");
                isReceiveSuccess = true;
            }
            else {
                IOUtils.displayWithinAsterisks(IOUtils.capitalizeFirstLetter(IOUtils.getNickNameOrNameWithArticle(this)) + " cannot hold any more items.");
            }
        }
        else {
            IOUtils.displayWithinAsterisks(IOUtils.capitalizeFirstLetter(IOUtils.getNickNameOrNameWithArticle(this)) + " is not open.");
        }

        return isReceiveSuccess;
    }

    public GameObject removeItem(GameObject requestedObject) {
        GameObject removedObject = EMPTY_GAME_OBJECT;

        if (isContainerOpen()) {
            if (requestedObject != null) {
                if (items.contains(requestedObject)) {
                    removedObject = items.remove(items.indexOf(requestedObject));
                }
                else {
                    IOUtils.capitalizeFirstLetter(IOUtils.getNickNameOrNameWithArticle(requestedObject) + "is not inside " + IOUtils.getNickNameOrNameWithArticle(this));
                }
            }
        }
        else {
            IOUtils.displayWithinAsterisks(IOUtils.capitalizeFirstLetter(IOUtils.getNickNameOrNameWithArticle(this)) + " is not open.");
        }

        return removedObject;
    }

    public void receiveClose() {
        if (isContainerOpen()) {
            setContainerOpen(false);
            IOUtils.displayWithinAsterisks(IOUtils.capitalizeFirstLetter(IOUtils.getNickNameOrNameWithArticle(this)) + " is now closed.");
        }
        else {
            IOUtils.displayWithinAsterisks(IOUtils.capitalizeFirstLetter(IOUtils.getNickNameOrNameWithArticle(this)) + " is already closed.");
        }
    }

    public void receiveOpen() {
        if (!isContainerOpen()) {
            setContainerOpen(true);
            IOUtils.displayWithinAsterisks(IOUtils.capitalizeFirstLetter(IOUtils.getNickNameOrNameWithArticle(this)) + " is now open.");
        }
        else {
            IOUtils.displayWithinAsterisks(IOUtils.capitalizeFirstLetter(IOUtils.getNickNameOrNameWithArticle(this)) + " is already open.");
        }
    }

    public void destroy(boolean displayDestroyString) {
        super.destroy(displayDestroyString);

        Iterator<GameObject> gameObjectIterator = getItems().iterator();
        while (gameObjectIterator.hasNext()) {
            GameObject gameObject = gameObjectIterator.next();
            gameObject.destroy(false); // Don't display the string for contents of a container.
        }
    }

    public int getItemLimit() {
        return itemLimit;
    }

    public void setItemLimit(int itemLimit) {
        this.itemLimit = itemLimit;
    }

    public boolean isContainerOpen() {
        return isContainerOpen;
    }

    public void setContainerOpen(boolean open) {
        isContainerOpen = open;
    }

    public int getItemCount() {
        return items.size();
    }

    public List<GameObject> getItems() {
        return items;
    }
}
