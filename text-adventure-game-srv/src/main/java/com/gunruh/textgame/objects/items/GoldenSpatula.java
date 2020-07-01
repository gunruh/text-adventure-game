package com.gunruh.textgame.objects.items;

import com.gunruh.textgame.Game;
import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.utils.IOUtils;

public class GoldenSpatula extends GameObject {
	public GoldenSpatula(Game game) {
		super(game, "Golden Spatula", "A powerful golden spatula, weilded by the Master Corndog chef." +
		      "\nThe handle is inscribed: 'Rufus - Keeper of the corndogs'." +
		      "\nIt appears it can be used as a gun...");
	}
	
	@Override
	public int getEffectivenessAsBlaster() {
		return 100;
	}
	
	@Override
	public void shoot(GameObject receivingObject) {
		super.shoot(receivingObject, IOUtils.surroundWithAsterisks("A corndog is launched from the end of " + IOUtils.getNickNameOrNameWithArticle(this) + " with tremendous speed."));
	}
}