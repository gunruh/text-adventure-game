package com.gunruh.textgame.objects.items;

import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.utils.IOUtils;

public class GoldenSpatula extends GameObject {
	private GoldenSpatula() {
		super(game, "Golden Spatula", "A powerful golden spatula, weilded by the Master Corndog chef." +
		      "\nThe handle is inscribed: 'Rufus - Keeper of the corndogs'." +
		      "\nIt appears it can be used as a gun...");
	}
	
	private static GoldenSpatula INSTANCE = new GoldenSpatula();
	
	public static GoldenSpatula getInstance() {
		return INSTANCE;
	}
	
	@Override
	public int getEffectivenessAsBlaster() {
		return 100;
	}
	
	@Override
	public void shoot(GameObject receivingObject) {
		game.getGameOutput().appendln(IOUtils.surroundWithAsterisks("A corndog is launched from the end of " + IOUtils.getNickNameOrNameWithArticle(this) + " with tremendous speed.");
		super.shoot(receivingObject);
	}
}