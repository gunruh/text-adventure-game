package com.gunruh.textgame.console;

import com.gunruh.textgame.Game;
import com.gunruh.textgame.objects.Statement;
import com.gunruh.textgame.utils.IOUtils;

import static com.gunruh.textgame.utils.IOUtils.getStatementFromInputText;
import static com.gunruh.textgame.utils.IOUtils.isNullOrEmpty;

public class ConsoleGameRunner {
    private Game game;

    public ConsoleGameRunner(Game game) {
        this.game = game;
    }

    public void run() {
        // First print any starting text
        System.out.println(game.getGameOutput().print());

        String input = null;

        while (!"quit".equalsIgnoreCase(input)) {
            input = IOUtils.getInputTextFromConsole();

            if (isNullOrEmpty(input)) {
                continue;
            }

            if (input.toLowerCase().trim().contains("quit")) {
                continue;
            }

            game.parseInput(input);

            System.out.println(game.getGameOutput().print());
        }

        // Exiting loop
        System.out.println("That's all folks.");
    }
}
