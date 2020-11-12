package com.gunruh.textgame.objects.rooms.starship.level1;

import com.gunruh.textgame.Game;
import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.items.CleaningSprayGun;
import com.gunruh.textgame.objects.rooms.Room;

public class JanitorsCloset extends Room {
    public JanitorsCloset(Game game) {
        super(game, "Janitor's Closet", "This room stores the window-cleaning supplies,\nbut a few items seem to have been taken..." +
              "\nThe door is on the west side.");

        addItem(new CleaningSprayGun(game));
        addItem(new GameObject(game, "Crumpled Note", "Opening the note reveals the text: " +
                "\n\"BiLL wuz HeeRe.\"") {});
    }
    
    @Override
    public Room goWest() {
    	return game.getRoom(MainLowerHallway.class);
    }
}
