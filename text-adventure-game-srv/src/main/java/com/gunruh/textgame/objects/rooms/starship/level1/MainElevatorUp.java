package com.gunruh.textgame.objects.rooms.starship.level1;

import com.gunruh.textgame.Game;
import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.rooms.starship.level2.MainElevatorDown;
import com.gunruh.textgame.utils.IOUtils;

public class MainElevatorUp extends Room {
    public MainElevatorUp(Game game) {
        super(game, "Main Elevator (Level: 1)", "The central elevator at the heart of the ship.\nIt goes Up and Down (as all good elevators should).\nThe doors are on the south side.");
    }

    @Override
    public Room goSouth() {
        return game.getRoom(MainLowerHallway.class);
    }

    @Override
    public Room goUp() {
        game.getGameOutput().appendln(IOUtils.prefixWithAsterisk("The doors close and you feel heavier as the elevator glides upward. The elevator doors open to the South."));
        return game.getRoom(MainElevatorDown.class);
    }

    @Override
    public Room goDown() {
        game.getGameOutput().appendln(IOUtils.prefixWithAsterisk("The elevator cannot go any lower."));
        return Room.ROOM_NOT_PRESENT;
    }
}
