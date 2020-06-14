package com.gunruh.textgame.objects.containerObjects;

import com.gunruh.textgame.objects.GameObject;

import java.util.List;

public interface Container {
    void addItem(GameObject gameObject);

    GameObject removeItem(GameObject requestedObject);

    void receiveClose();

    void receiveOpen();

    int getItemLimit();

    void setItemLimit(int itemLimit);

    boolean isContainerOpen();

    void setContainerOpen(boolean open);

    int getItemCount();

    List<GameObject> getItems();
}
