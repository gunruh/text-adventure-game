package com.gunruh.textgame.objects.items.containers;

import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.items.KeyCardGreen;

public class Dresser extends Container {
    private static Dresser INSTANCE = new Dresser();

    private Dresser() {
        super("Dresser", "Just a basic dresser. Standard issue for janitors such as yourself.", 3, true);
        addItem(new GameObject("Old Photograph", "It's a picture of your mom from back home.") {});
        addItem(new GameObject("Alarm Clock", "It used to sit on top of the dresser, but... eh... too noisy.") {});
        addItem(KeyCardGreen.getInstance());
    }

    public static Dresser getInstance() {
        return INSTANCE;
    }
}
