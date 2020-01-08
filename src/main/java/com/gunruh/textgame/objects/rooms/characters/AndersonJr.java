package com.gunruh.textgame.objects.rooms.characters;

import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.Player;
import com.gunruh.textgame.utils.IOUtils;

public class AndersonJr extends GameObject {
	private AndersonJr() {
		super("Captain's Son", "The son of the captain. He is still earning his junior flying badge.");
		setNickName("Billy Anderson Jr.");
	}
	
	private static AndersonJr INSTANCE = new AndersonJr();
	
	public static AndersonJr getInstance() {
		return INSTANCE;
	}
	
	@Override
	public boolean isPermanentFixture() {
		return true;
	}

	@Override
	public void receiveShoot(GameObject actingObject) {
		IOUtils.displayWithinAsterisks(IOUtils.getNickNameOrNameWithArticle(this) + " jumps out of the way.");
	}
	
	@Override
	public void receiveTalkTo(GameObject gameObject) {
		if (Player.getInstance().getCurrentRoom().getAvailableObjects().contains(Captain.getInstance())) {
			IOUtils.display(IOUtils.getNickNameOrNameWithArticle(this) + " says: \n\"I'm the next in line to be the big Kahoona of this ship." +
			                                                             "\nSo I'm on my best behavior... at least while my dad is in the room..." + 
			                                                             "\nWhat's that? ... I'm not sure how those cleaning supplies got here... you should ask my dad.\"");
		}
		else {
			IOUtils.display(IOUtils.getNickNameOrNameWithArticle(this) + " says: \n\"Alright, ALRIGHT! YES, I took the cleaning supplies!" +
			                                                             "\nPlease don't tell my dad! You can have them back! I'm just going to sit here quietly and look at all these nifty controls...\"");
		}
	}
}