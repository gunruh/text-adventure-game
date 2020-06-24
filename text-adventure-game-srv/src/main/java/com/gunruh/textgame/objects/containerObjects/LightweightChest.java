package com.gunruh.textgame.objects.containerObjects;

import com.gunruh.textgame.Game;
import com.gunruh.textgame.objects.GameObject;

public class LightweightChest extends ContainerObject {
    public LightweightChest(Game game) {
        super(game, "Lightweight Chest", "A container for nic-nacks and paddy-whacks.", 10);
        GameObject fuzzersTheSpaceChimp = new GameObject(game, "Stuffed Animal", "It's just a stuffed animal, not a real space animal.") {};
        fuzzersTheSpaceChimp.setNickName("Fuzzers the Space Chimp");
        addItem(fuzzersTheSpaceChimp);
        addItem(new GameObject(game, "Sock Squid", "Remember this? You made a 'sock squid' out of all the socks without matches.") {});
        addItem(new MediumChest(game));
    }
}
