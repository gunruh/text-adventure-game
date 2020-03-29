package com.gunruh.textgame.objects.items;

import com.gunruh.textgame.objects.GameObject;

public class KeyCardGreen extends GameObject {
    private KeyCardGreen() {
        super("Green Key Card", "A green key card with some sort of magnetic signature. It may allow access to restricted areas.");
    }

    private static KeyCardGreen INSTANCE = new KeyCardGreen();

    public static KeyCardGreen getInstance() {
        return INSTANCE;
    }
}
