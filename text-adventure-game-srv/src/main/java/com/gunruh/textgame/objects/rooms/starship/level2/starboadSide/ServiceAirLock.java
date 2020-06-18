package com.gunruh.textgame.objects.rooms.starship.level2.starboadSide;

import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.Player;
import com.gunruh.textgame.objects.items.CleaningSprayGun;
import com.gunruh.textgame.objects.items.RubberSqueegee;
import com.gunruh.textgame.objects.items.WindowWashingCloth;
import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.utils.IOUtils;

import java.util.List;

public class ServiceAirLock extends Room {
    private ServiceAirLock() {
        super("Service AirLock", "This is the gateway to outer space!" +
                "\nJanitor's such as yourself often come through here to clean the escape pod windows." +
                "\nThe airlock gateway is on the east side of the room. You'll need your cleaning supplies before going out." +
                "\nThe door back into the ship is to the north.");
    }

    private static ServiceAirLock INSTANCE = new ServiceAirLock();

    public static ServiceAirLock getInstance() {
        return INSTANCE;
    }

    @Override
    public Room goNorth() {
        return StarBoardHallway.getInstance();
    }

    @Override
    public Room goEast() {
        List<GameObject> playerInventory = Player.getInstance().getItems();
        if (playerInventory.contains(CleaningSprayGun.getInstance()) && playerInventory.contains(RubberSqueegee.getInstance()) && playerInventory.contains(WindowWashingCloth.getInstance())) {
            IOUtils.displayWithinAsterisks("WAAAWOOOOBEE BOOOOP... SWOOOOOOSHHHHH. The airlock sensor reads all three items and opens, sucking you out to space.");
            return SpaceOutsideEscapePods.getInstance();
        }
        else {
            IOUtils.displayWithinAsterisks("wub wub wub. Three low beeps are heard. You need a cleaning-spray gun, a squeegee, and a window-washing cloth in order to go outside.");
            return Room.ROOM_NOT_PRESENT;
        }
    }
}
