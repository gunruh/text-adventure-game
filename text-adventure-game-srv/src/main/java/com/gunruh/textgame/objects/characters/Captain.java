package com.gunruh.textgame.objects.characters;

import com.gunruh.textgame.Game;
import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.Player;
import com.gunruh.textgame.utils.IOUtils;

public class Captain extends GameObject {
	public Captain(Game game) {
        super(game, "Captain", "The pilot of the space cruiser. He really loves corn-dogs. You might want to talk to him.");
        setNickName("Captain Anderson III");
    }

    @Override
	public boolean isPermanentFixture() {
		return true;
	}

	@Override
    public void receiveShoot(GameObject actingObject) {
        game.getGameOutput().appendln(IOUtils.surroundWithAsterisks(IOUtils.getNickNameOrNameWithArticle(this) + " is wearing official blaster-proof gear. " + IOUtils.getNickNameOrNameWithArticle(actingObject) + "'s attack has no effect."));
    }

    @Override
    public void receiveTalkTo(GameObject gameObject) {
    	game.getGameOutput().appendln(IOUtils.getNickNameOrNameWithArticle(this) + " says: " +
                "\n\"I love my boy. I brought him to 'Bring-Your-Child-To-Work Day' today." +
                "\nOne day he's going to be the captain of this cruiser." +
    	                "\nI also love corndogs. What's that? You say there are free corndogs today? ... I'll be right back...\"");
    	game.getGameOutput().appendln(IOUtils.surroundWithAsterisks(IOUtils.getNickNameOrNameWithArticle(this) + " leaves the room."));
    	game.getPlayer().getCurrentRoom().getItems().remove(this);
    }
}
