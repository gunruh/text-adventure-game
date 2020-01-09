package com.gunruh.textgame.objects.rooms.starship.level0;

import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.items.SprayBottle;
import com.gunruh.textgame.objects.rooms.Room;

public class JanitorsCloset extends Room {
    private JanitorsCloset() {
        super("Janitor's Closet", "This is where the janitor supplies usually are, but the bucket and squeegie are missing..." + 
              "\nThe door is on the west side.");

        getAvailableObjects().add(SprayBottle.getInstance());
        getAvailableObjects().add(new GameObject("Crumpled Note", "Opening the note reveals the text: " +
                "\n\"BiLL wuz HeeRe.\"") {});
    }
    
    @Override
    public Room goWest() {
    	return MainLowerHallway.getInstance();
    }
}
