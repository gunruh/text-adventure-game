package com.gunruh.textgame.objects.rooms.starship.level1;

import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.items.CleaningSprayGun;
import com.gunruh.textgame.objects.rooms.Room;

public class JanitorsCloset extends Room {
    private JanitorsCloset() {
        super(game, "Janitor's Closet", "This room stores the window-cleaning supplies, but a few items seem to have been taken..." +
              "\nThe door is on the west side.");

        addItem(CleaningSprayGun.getInstance());
        addItem(new GameObject(game, "Crumpled Note", "Opening the note reveals the text: " +
                "\n\"BiLL wuz HeeRe.\"") {});
    }

    private static JanitorsCloset INSTANCE = new JanitorsCloset();

    public static JanitorsCloset getInstance() {
        return INSTANCE;
    }
    
    @Override
    public Room goWest() {
    	return MainLowerHallway.getInstance();
    }
}
