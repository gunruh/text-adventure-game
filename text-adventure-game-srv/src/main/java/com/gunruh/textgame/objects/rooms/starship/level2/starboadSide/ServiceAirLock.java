package com.gunruh.textgame.objects.rooms.starship.level2.starboadSide;

import com.gunruh.textgame.Game;
import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.items.CleaningSprayGun;
import com.gunruh.textgame.objects.items.RubberSqueegee;
import com.gunruh.textgame.objects.items.WindowWashingCloth;
import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.utils.IOUtils;

import java.util.List;

public class ServiceAirLock extends Room {
    public ServiceAirLock(Game game) {
        super(game, "Service AirLock", "This is the gateway to outer space!" +
                "\nJanitors use this room to clean the escape pod windows." +
                "\nThe airlock gateway is on the east side of the room." +
                "\nAll space cleaning supplies are required to enter." +
                "\nThe door back into the ship is to the north.");
    }

    @Override
    public Room goNorth() {
        return game.getRoom(StarBoardHallway.class);
    }

    @Override
    public Room goEast() {
        List<GameObject> playerInventory = game.getPlayer().getItems();
        if (game.getPlayer().containsInstanceOf(CleaningSprayGun.class) && game.getPlayer().containsInstanceOf(RubberSqueegee.class) && game.getPlayer().containsInstanceOf(WindowWashingCloth.class)) {
            game.getGameOutput().appendln(IOUtils.prefixWithAsterisk("WAAAWOOOOBEE BOOOOP... SWOOOOOOSHHHHH. The airlock sensor reads all three items and opens, sucking you out to space."));
            return game.getRoom(SpaceOutsideEscapePods.class);
        }
        else {
            game.getGameOutput().appendln(IOUtils.prefixWithAsterisk("wub wub wub.\nThree low beeps are heard.\nYou need a cleaning-spray gun, a squeegee, and a window-washing cloth in order to go outside."));
            return Room.ROOM_NOT_PRESENT;
        }
    }
}
