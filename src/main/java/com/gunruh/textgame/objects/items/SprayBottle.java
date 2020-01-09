package com.gunruh.textgame.objects.items;

import com.gunruh.textgame.objects.GameObject;

public class SprayBottle extends GameObject {
    private SprayBottle() {
        super("Spray Bottle", "A spray bottle filled with window-cleaning solution. You can spray it!");
    }
    
    private static SprayBottle INSTANCE = new SprayBottle();
    
    public static SprayBottle getInstance() {
    	return INSTANCE;
    }
    
    @Override
    public int getEffectivenessAsBlaster() {
    	return 5;
    }
}
