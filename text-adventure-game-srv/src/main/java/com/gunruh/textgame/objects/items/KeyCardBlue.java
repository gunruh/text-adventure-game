package com.gunruh.textgame.objects.items;

import com.gunruh.textgame.objects.GameObject;

public class KeyCardBlue extends GameObject {
    private KeyCardBlue() {
        super(game, "Blue Key Card", "A blue key card with some sort of magnetic signature. It may allow access to restricted areas.");
    }

    private static KeyCardBlue INSTANCE = new KeyCardBlue();

    public static KeyCardBlue getInstance() {
        return INSTANCE;
    }
}
