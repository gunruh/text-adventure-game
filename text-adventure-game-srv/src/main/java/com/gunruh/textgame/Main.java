package com.gunruh.textgame;

import com.gunruh.textgame.utils.Constants;

import static com.gunruh.textgame.utils.IOUtils.display;

public class Main {
    public static void main(String[] args) {
        Game game = Game.createNewGame();



        game.run();
    }
}
