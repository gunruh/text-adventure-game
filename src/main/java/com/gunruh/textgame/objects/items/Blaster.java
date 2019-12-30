package com.gunruh.textgame.objects.items;

import com.gunruh.textgame.objects.GameObject;

public class Blaster extends GameObject {
    public Blaster() {
        super("Blaster", "A laser gun.");
    }

    private int effectivenessAsGun = 50;

    @Override
    public int getEffectivenessAsBlaster() {
        return effectivenessAsGun;
    }
}
