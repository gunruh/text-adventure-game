package com.gunruh.textgame.objects.items;

import com.gunruh.textgame.Game;
import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.utils.IOUtils;

public class CleaningSprayGun extends GameObject {
    public CleaningSprayGun(Game game) {
        super(game, "Cleaning Spray Gun", "A cleaning device filled with window-cleaning solution. You can shoot it!");
    }
    
    @Override
    public int getEffectivenessAsBlaster() {
    	return 5;
    }

    @Override
    public void shoot(GameObject receivingObject) {
        game.getGameOutput().appendln(IOUtils.surroundWithAsterisks("A vaporous cloud of cleaning-solution is expelled from the nozzle."));
        super.shoot(receivingObject);
    }
}
