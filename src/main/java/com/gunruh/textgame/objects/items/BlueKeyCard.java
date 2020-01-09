package com.gunruh.textgame.objects.items;

import com.gunruh.textgame.objects.GameObject;

public class BlueKeyCard extends GameObject {
    private BlueKeyCard() {
        super("Blue Key Card", "A blue key card with some sort of magnetic signature. It may allow access to restricted areas.");
    }

    private static BlueKeyCard INSTANCE = new BlueKeyCard();

    public static BlueKeyCard getInstance() {
        return INSTANCE;
    }
}
