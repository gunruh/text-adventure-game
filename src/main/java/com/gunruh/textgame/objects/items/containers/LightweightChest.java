package com.gunruh.textgame.objects.items.containers;

import com.gunruh.textgame.objects.GameObject;

public class LightweightChest extends Container {
    private static LightweightChest INSTANCE = new LightweightChest();

    private LightweightChest() {
        super("Lightweight Chest", "A container for nic-nacks and paddy-whacks.", 10);
        addItem(new GameObject("Fuzzers the Space Chimp", "It's just a stuffed animal, not a real space chimp.") {});
        addItem(new GameObject("Sock Squid", "Remember this? You made a 'sock squid' out of all the socks without matches.") {});
    }

    public static LightweightChest getInstance() {
        return INSTANCE;
    }
}
