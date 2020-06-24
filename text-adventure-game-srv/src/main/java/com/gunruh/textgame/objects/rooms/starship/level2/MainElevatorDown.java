package com.gunruh.textgame.objects.rooms.starship.level2;

import com.gunruh.textgame.Game;
import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.rooms.starship.level1.MainElevatorUp;
import com.gunruh.textgame.utils.IOUtils;

public class MainElevatorDown extends Room {
    public MainElevatorDown(Game game) {
        super(game, "Main Elevator (Level: 2)", "The central elevator at the heart of the ship. It goes Up and Down (as all good elevators should). The doors are on the south side.");
        setIsNewPlace(false);
    }

    @Override
    public Room goSouth() {
        return game.getRoom(MainUpperHallway.class);
    }

    @Override
    public Room goUp() {
        game.getGameOutput().appendln(IOUtils.surroundWithAsterisks("The elevator cannot go any higher."));
        return Room.ROOM_NOT_PRESENT;
    }

    @Override
    public Room goDown() {
        game.getGameOutput().appendln(IOUtils.surroundWithAsterisks("The doors close, and you feel lighter as the elevator glides downward. The elevator doors open to the South."));
        return game.getRoom(MainElevatorUp.class);
    }
}
