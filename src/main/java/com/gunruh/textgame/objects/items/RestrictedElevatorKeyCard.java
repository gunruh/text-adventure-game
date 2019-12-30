package com.gunruh.textgame.objects.items;

import com.gunruh.textgame.objects.GameObject;

public class RestrictedElevatorKeyCard extends GameObject {
    private RestrictedElevatorKeyCard() {
        super("Blue Key Card", "A blue key card with some sort of magnetic signature. It may allow access to restricted areas.");
    }

    private static RestrictedElevatorKeyCard INSTANCE = new RestrictedElevatorKeyCard();

    public static RestrictedElevatorKeyCard getInstance() {
        return INSTANCE;
    }
}
