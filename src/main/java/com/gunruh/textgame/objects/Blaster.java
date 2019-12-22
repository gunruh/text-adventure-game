package com.gunruh.textgame.objects;

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
