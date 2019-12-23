package com.gunruh.textgame.objects;

public class Item extends GameObject {
	
	public Item(String name, String description) {
		super(name, description);
	}
	
	public int getEffectivenessAsBlaster() {
		return 0;
	}
}