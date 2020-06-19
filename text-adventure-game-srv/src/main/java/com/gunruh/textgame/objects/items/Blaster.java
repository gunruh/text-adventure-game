package com.gunruh.textgame.objects.items;

import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.utils.IOUtils;

public class Blaster extends GameObject {
    public Blaster() {
        super(game, "Blaster", "A laser gun.");
    }

    private int effectivenessAsGun = 50;

    @Override
    public int getEffectivenessAsBlaster() {
        return effectivenessAsGun;
    }

    @Override
    public void shoot(GameObject receivingObject) {
        game.getGameOutput().appendln(IOUtils.surroundWithAsterisks("A flash of red laser light shoots from the end of " + IOUtils.getNickNameOrNameWithArticle(this) + ".");
        super.shoot(receivingObject);
    }
}
