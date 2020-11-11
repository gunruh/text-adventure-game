package com.gunruh.textgame.objects.containerObjects;

import com.gunruh.textgame.Game;
import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.items.KeyCardGreen;

public class Dresser extends ContainerObject {
    public Dresser(Game game) {
        super(game, "Dresser", "Just a basic dresser. Standard issue for janitors such as yourself.", 4, true);
        addItem(new GameObject(game, "Old Photograph", "It's a picture of your mom from back home.") {});
        addItem(new GameObject(game, "Alarm Clock", "It used to sit on top of the dresser, but... eh... too noisy.") {});
        addItem(new KeyCardGreen(game));
        ContainerObject lonelyLeftSock = new ContainerObject(game, "Lonely Left Sock", "The little fella is missing his friend.", 1, false) {};
        lonelyLeftSock.addItem(new GameObject(game, "Space Raisin", "Seems friendly, though no one knows where it came from.") {});
        addItem(lonelyLeftSock);
    }
}
