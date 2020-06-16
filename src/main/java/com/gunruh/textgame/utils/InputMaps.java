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
        actionMap.put("t", Action.Take);
        actionMap.put("pick up", Action.Take);
        actionMap.put("pick", Action.Take);
        actionMap.put("grab", Action.Take);
        actionMap.put("steal", Action.Take);
        actionMap.put("snatch", Action.Take);
        actionMap.put("collect", Action.Take);
        actionMap.put("find", Action.Take);
        actionMap.put("get", Action.Take);
        actionMap.put("remove", Action.Take);
        actionMap.put("extract", Action.Take);

        actionMap.put("drop", Action.Drop);
        actionMap.put("leave", Action.Drop);
        actionMap.put("let go", Action.Drop); // todo figure out how to handle this phrase? ... phrases in general...
        actionMap.put("set", Action.Drop);
        actionMap.put("dump", Action.Drop);
        actionMap.put("insert", Action.Drop);
        actionMap.put("put", Action.Drop);

        actionMap.put("shoot", Action.Shoot);
        actionMap.put("blast", Action.Shoot);
        actionMap.put("fire", Action.Shoot);
        actionMap.put("spritz", Action.Shoot);

        actionMap.put("look", Action.Look);
        actionMap.put("l", Action.Look);
        actionMap.put("observe", Action.Look);
        actionMap.put("see", Action.Look);
        actionMap.put("describe", Action.Look);
        
        actionMap.put("name", Action.Name);
        actionMap.put("rename", Action.Name);
        actionMap.put("call", Action.Name);

        actionMap.put("say", Action.Speak);
        actionMap.put("speak", Action.Speak);
        actionMap.put("chat", Action.Speak);
        actionMap.put("whisper", Action.Speak);
        actionMap.put("recite", Action.Speak);
        actionMap.put("tell", Action.Speak);
        actionMap.put("hello", Action.Speak);
        actionMap.put("talk", Action.Speak);
        actionMap.put("ask", Action.Speak);
        actionMap.put("command", Action.Speak);

        actionMap.put("i", Action.Inventory);
        actionMap.put("inventory", Action.Inventory);
        actionMap.put("inv", Action.Inventory);

        actionMap.put("push", Action.Push);
        actionMap.put("press", Action.Push);
        actionMap.put("activate", Action.Push);

        actionMap.put("open", Action.Open);

        actionMap.put("close", Action.Close);
        actionMap.put("shut", Action.Close);

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
        directionMap.put("u", Direction.Up);
        directionMap.put("up", Direction.Up);
        directionMap.put("d", Direction.Down);
        directionMap.put("down", Direction.Down);
    }
}
