package com.gunruh.textgame.objects.rooms.characters;

import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.Player;
import com.gunruh.textgame.utils.IOUtils;

public class Captain extends GameObject {
	private Captain() {
        super("Captain", "The pilot of the space cruiser. He really loves corn-dogs. You might want to talk to him.");
        setNickName("Captain Anderson III");
    }

    private static Captain INSTANCE = new Captain();

    public static Captain getInstance() {
        return INSTANCE;
    }
    
    @Override
	public boolean isPermanentFixture() {
		return true;
	}

	@Override
    public void receiveShoot(GameObject actingObject) {
        IOUtils.displayWithinAsterisks(IOUtils.getNickNameOrNameWithArticle(this) + " is wearing official blaster-proof gear. " + IOUtils.getNickNameOrNameWithArticle(actingObject) + "'s attack has no effect.");
    }

    @Override
    public void receiveTalkTo(GameObject gameObject) {
    	IOUtils.display(IOUtils.getNickNameOrNameWithArticle(this) + " says: " +
                "\n\"I love my boy. I brought him to 'Bring-Your-Child-To-Work Day' today." +
                "\nOne day he's going to be the captain of this cruiser." +
    	                "\nI also love corndogs. What's that? You say there are free corndogs today? ... I'll be right back...\"");
    	IOUtils.displayWithinAsterisks(IOUtils.getNickNameOrNameWithArticle(this) + " leaves the room.");
    	Player.getInstance().getCurrentRoom().getAvailableObjects().remove(this);
    }
}
