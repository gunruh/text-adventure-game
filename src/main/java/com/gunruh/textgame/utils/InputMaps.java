package com.gunruh.textgame.utils;

import com.gunruh.textgame.enumerations.Action;
import com.gunruh.textgame.enumerations.Direction;

import java.util.HashMap;
import java.util.Map;

public class InputMaps {
    public static final Map<String, Action> actionMap = new HashMap<String, Action>();
    public static final Map<String, Direction> directionMap = new HashMap<String, Direction>();

    static {
        actionMap.put("go", Action.Move);
        actionMap.put("walk", Action.Move);
        actionMap.put("move", Action.Move);
        actionMap.put("travel", Action.Move);
        actionMap.put("head", Action.Move);
        actionMap.put("run", Action.Move);
        actionMap.put("crawl", Action.Move);

        actionMap.put("take", Action.Take);
        actionMap.put("pick up", Action.Take);
        actionMap.put("pick", Action.Take);
        actionMap.put("grab", Action.Take);
        actionMap.put("steal", Action.Take);
        actionMap.put("snatch", Action.Take);
        actionMap.put("collect", Action.Take);
        actionMap.put("find", Action.Take);

        actionMap.put("shoot", Action.Shoot);
        actionMap.put("blast", Action.Shoot);
        actionMap.put("fire", Action.Shoot);

        actionMap.put("look", Action.Look);
        actionMap.put("observe", Action.Look);
        actionMap.put("see", Action.Look);
        actionMap.put("describe", Action.Look);

        directionMap.put("north", Direction.North);
        directionMap.put("n", Direction.North);
        directionMap.put("northeast", Direction.NorthEast);
        directionMap.put("ne", Direction.NorthEast);
        directionMap.put("east", Direction.East);
        directionMap.put("e", Direction.East);
        directionMap.put("southeast", Direction.SouthEast);
        directionMap.put("se", Direction.SouthEast);
        directionMap.put("south", Direction.South);
        directionMap.put("s", Direction.South);
        directionMap.put("southwest", Direction.SouthWest);
        directionMap.put("sw", Direction.SouthWest);
        directionMap.put("west", Direction.West);
        directionMap.put("w", Direction.West);
        directionMap.put("northwest", Direction.NorthWest);
        directionMap.put("nw", Direction.NorthWest);
    }
}
