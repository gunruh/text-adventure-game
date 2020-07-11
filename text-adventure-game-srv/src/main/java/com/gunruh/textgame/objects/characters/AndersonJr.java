package com.gunruh.textgame.objects.characters;

import com.gunruh.textgame.Game;
import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.utils.IOUtils;

public class AndersonJr extends GameObject {
	public AndersonJr(Game game) {
		super(game, "Captain's Son", "The son of the captain. He is still earning his junior flying badge.");
		setNickName("Billy Anderson Jr.");
	}
	
	@Override
	public boolean isPermanentFixture() {
		return true;
	}

	@Override
	public void receiveShoot(GameObject actingObject) {
		game.getGameOutput().appendln(IOUtils.surroundWithAsterisks(IOUtils.getNickNameOrNameWithArticle(this) + " jumps out of the way."));
	}
	
	@Override
	public void receiveTalkTo(GameObject gameObject) {
		if (game.getPlayer().getCurrentRoom().containsInstanceOf(Captain.class)) {
			game.getGameOutput().appendln(IOUtils.getNickNameOrNameWithArticle(this) + " says:" +
																		 "\n\"I'm the next in line to be the big Kahoona of this ship." +
			                                                             "\nSo I'm on my best behavior... at least while my dad is in the room..." + 
			                                                             "\nWhat's that?" +
																		 "\n...I'm not sure how those cleaning supplies got here" +
																		 "\n...you should ask my dad.\"");
		}
		else {
			game.getGameOutput().appendln(IOUtils.getNickNameOrNameWithArticle(this) + " says:" +
					"\n\"Alright, ALRIGHT! YES, I took the cleaning supplies!" +
					"\nPlease take them - don't tell my dad!" +
					"\nI'm just going to sit here quietly and look at all these nifty controls...\"");
		}
	}
}