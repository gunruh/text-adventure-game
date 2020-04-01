package com.gunruh.textgame.objects.items.containers;

import com.gunruh.textgame.objects.GameObject;

public class MediumChest extends Container {
    private static MediumChest INSTANCE = new MediumChest();

    private MediumChest() {
        super("Medium Chest", "A medium-sized container for nic-nacks and paddy-whacks.", 10);
        addItem(new GameObject("Nic-Nack", "Somethin' you found on the floor one day. Probably should get rid of it sometime.") {});
        addItem(new GameObject("Paddy-Whack", "Real small piece of a crumb.") {});
        setOpen(true);
    }

    public static MediumChest getInstance() {
        return INSTANCE;
    }
}
