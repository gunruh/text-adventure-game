package com.gunruh.textgame.objects.rooms.starship.level1;

import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.items.GoldenSpatula;
import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.rooms.characters.ShipChef;

public class Kitchen extends Room {
	private Kitchen() {
		super("Kitchen", "Smells like corndogs in here, amongst other things." + 
		      "\nThe double doors on the southwest lead back to the hallway.");
		getAvailableObjects().add(ShipChef.getInstance());
		getAvailableObjects().add(new GameObject("Corn Dog Cooker", "It's a professional grade corndog cooker, with roller wheels.") {});
		getAvailableObjects().add(new GameObject("Big Pan", "A really big cooking pan.") {});
		getAvailableObjects().add(new GameObject("Metal Pot", "Looks useful for cooking a cheesy bake stew.") {});
		getAvailableObjects().add(GoldenSpatula.getInstance());
		getAvailableObjects().add(new GameObject("Chef's Hat", "A hat used by the finest chefs in the galaxy.") {});
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