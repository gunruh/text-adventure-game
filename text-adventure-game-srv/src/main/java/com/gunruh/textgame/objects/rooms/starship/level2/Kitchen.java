package com.gunruh.textgame.objects.rooms.starship.level2;

import com.gunruh.textgame.Game;
import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.items.GoldenSpatula;
import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.characters.ShipChef;

public class Kitchen extends Room {
	public Kitchen(Game game) {
		super(game, "Kitchen", "Smells like corndogs in here, amongst other things." +
		      "\nThe double doors on the southwest lead back to the hallway.");
		addItem(new ShipChef(game));
		addItem(new GameObject(game, "Corn Dog Cooker", "It's a professional grade corndog cooker, with roller wheels.") {});
		addItem(new GameObject(game, "Big Pan", "A really big cooking pan.") {});
		addItem(new GameObject(game, "Metal Pot", "Looks useful for cooking a cheesy bake stew.") {});
		addItem(new GoldenSpatula(game));
		addItem(new GameObject(game, "Chef's Hat", "A hat used by the finest chefs in the galaxy.") {});
	}
	
	@Override
	public Room goSouthWest() {
		return game.getRoom(MainUpperHallway.class);
	}
}