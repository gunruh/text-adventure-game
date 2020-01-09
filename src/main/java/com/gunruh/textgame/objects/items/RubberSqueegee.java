package com.gunruh.textgame.objects.items;

import com.gunruh.textgame.objects.GameObject;

public class RubberSqueegee extends GameObject {
    private RubberSqueegee() {
        super("Rubber Squeegee", "This squeegee is made of 100 percent space rubber. Professional quality, for a professional squeeger.");
    }

    private static RubberSqueegee INSTANCE = new RubberSqueegee();

    public static RubberSqueegee getInstance() {
        return INSTANCE;
    }
}
