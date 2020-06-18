package com.gunruh.textgame.objects.rooms.starship.level2;

import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.items.GoldenSpatula;
import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.characters.ShipChef;

public class Kitchen extends Room {
	private Kitchen() {
		super("Kitchen", "Smells like corndogs in here, amongst other things." + 
		      "\nThe double doors on the southwest lead back to the hallway.");
		addItem(ShipChef.getInstance());
		addItem(new GameObject("Corn Dog Cooker", "It's a professional grade corndog cooker, with roller wheels.") {});
		addItem(new GameObject("Big Pan", "A really big cooking pan.") {});
		addItem(new GameObject("Metal Pot", "Looks useful for cooking a cheesy bake stew.") {});
		addItem(GoldenSpatula.getInstance());
		addItem(new GameObject("Chef's Hat", "A hat used by the finest chefs in the galaxy.") {});
	}
	
	private static Kitchen INSTANCE = new Kitchen();
	
	public static Kitchen getInstance() {
		return INSTANCE;
	}
	
	@Override
	public Room goSouthWest() {
		return MainUpperHallway.getInstance();
	}
}