package com.gunruh.textgame.utils;

import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.containerObjects.Container;

import java.util.Iterator;

public class ContainerUtils {
    public static GameObject recursiveRemove(Container container, GameObject gameObject) {
        return recursiveFind(container, gameObject, true);
    }

    public static GameObject recursiveFind(Container container, GameObject gameObject) {
        return recursiveFind(container, gameObject, false);
    }

    // todo maybe make this breadth-first instead of depth-first search eventually.
    public static GameObject recursiveFind(Container container, GameObject gameObject, boolean isRemove) {
        GameObject foundItem = GameObject.EMPTY_GAME_OBJECT;

        Iterator<GameObject> gameObjectIterator = container.getItems().iterator();
        while (gameObjectIterator.hasNext()) {
            GameObject listObject = gameObjectIterator.next();
            if (listObject == gameObject) {
                foundItem = listObject;
                if (isRemove) {
                    gameObjectIterator.remove();
                }
                break;
            }
            else if (listObject instanceof Container) {
                foundItem = recursiveFind((Container) listObject, gameObject, isRemove);
                if (foundItem != GameObject.EMPTY_GAME_OBJECT) {
                    break;
                }
            }
        }

        return foundItem;
    }
}
