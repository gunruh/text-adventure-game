package com.gunruh.textgame.objects.rooms.characters;

import com.gunruh.textgame.objects.GameObject;

public class Captain extends GameObject {
    private Captain() {
        super("Captain", "The pilot of the space cruiser. He really loves corn-dogs. You might want to talk to him.");
        setNickName("Captain Anderson III");
    }

    private static Captain INSTANCE = new Captain();

    public static Captain getInstance() {
        return INSTANCE;
    }
}
