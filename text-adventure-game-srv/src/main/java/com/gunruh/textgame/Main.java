package com.gunruh.textgame;

import com.gunruh.textgame.console.ConsoleGameRunner;

public class Main {
    public static void main(String[] args) {
        Game game = Game.createNewGame();

        ConsoleGameRunner consoleGameRunner = new ConsoleGameRunner(game);

        consoleGameRunner.run();
    }
}
