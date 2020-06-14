package com.gunruh.textgame.utils;

import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.containerObjects.Container;

import java.util.Iterator;

public class ContainerUtils {
    // todo maybe make this breadth-first instead of depth-first search eventually.
    public static GameObject recursiveRemove(Container container, GameObject gameObject) {
        GameObject removedItem = GameObject.EMPTY_GAME_OBJECT;

        Iterator<GameObject> gameObjectIterator = container.getItems().iterator();
        while (gameObjectIterator.hasNext()) {
            GameObject listObject = gameObjectIterator.next();
            if (listObject instanceof Container) {
                removedItem = recursiveRemove((Container) listObject, gameObject);
                if (removedItem != null) {
                    return removedItem;
                }
            }
            else if (listObject == gameObject) {
                removedItem = listObject;
                gameObjectIterator.remove();
                break;
            }
        }

        return removedItem;
    }
}
