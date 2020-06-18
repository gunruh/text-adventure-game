package com.gunruh.textgame.objects.characters;

import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.utils.IOUtils;

public class ShipChef extends GameObject {
	private ShipChef() {
		super("Ship Chef", "This dude cooks all sorts of food. He is wearing a blue ribbon that says 'First Place Corndog Champ'.");
		setNickName("Rufus");
	}
	
	private static ShipChef INSTANCE = new ShipChef();
	
	public static ShipChef getInstance() {
		return INSTANCE;
	}
	
	@Override
	public boolean isPermanentFixture() {
		return true;
	}
	
	@Override
	public void receiveTalkTo(GameObject gameObject) {
		IOUtils.display(outputBuffer, IOUtils.getNickNameOrNameWithArticle(this) + " says: " +
		                                                     "\n\"Why, hello there. You better not be scrambling around in my kitchen.\"");
	}
}