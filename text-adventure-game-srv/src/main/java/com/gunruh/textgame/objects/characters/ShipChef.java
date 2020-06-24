package com.gunruh.textgame.objects.characters;

import com.gunruh.textgame.Game;
import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.utils.IOUtils;

public class ShipChef extends GameObject {
	public ShipChef(Game game) {
		super(game, "Ship Chef", "This dude cooks all sorts of food. He is wearing a blue ribbon that says 'First Place Corndog Champ'.");
		setNickName("Rufus");
	}
	
	@Override
	public boolean isPermanentFixture() {
		return true;
	}
	
	@Override
	public void receiveTalkTo(GameObject gameObject) {
		game.getGameOutput().appendln(IOUtils.getNickNameOrNameWithArticle(this) + " says: " +
		                                                     "\n\"Why, hello there. You better not be scrambling around in my kitchen.\"");
	}
}