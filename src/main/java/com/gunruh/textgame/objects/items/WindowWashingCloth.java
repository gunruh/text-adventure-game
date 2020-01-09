package com.gunruh.textgame.objects.items;

import com.gunruh.textgame.objects.GameObject;

public class WindowWashingCloth extends GameObject {
    private WindowWashingCloth() {
        super("Window Washing Cloth", "A pristine wash cloth made from the finest micro-fibers in space. Pretty neat, can wash your feet.");
    }

    private static WindowWashingCloth INSTANCE = new WindowWashingCloth();

    public static WindowWashingCloth getInstance() {
        return INSTANCE;
    }
}
