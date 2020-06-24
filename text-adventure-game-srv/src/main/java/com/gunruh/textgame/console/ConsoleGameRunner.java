package com.gunruh.textgame.console;

import com.gunruh.textgame.Game;
import com.gunruh.textgame.utils.Constants;
import com.gunruh.textgame.utils.IOUtils;

import static com.gunruh.textgame.utils.IOUtils.isNullOrEmpty;

public class ConsoleGameRunner {
    private Game game;

    public ConsoleGameRunner(Game game) {
        this.game = game;
    }

    private void displayStartText() {
        game.getGameOutput().appendln(Constants.SPACE_DUDES_TITLE);
        game.getGameOutput().appendln(Constants.INTRO_TEXT);
        System.out.print(game.getGameOutput().print());
    }

    public void run() {
        displayStartText();

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


            System.out.print(game.getGameOutput().print());
        }

        // Exiting loop
        game.getGameOutput().appendln("That's all folks.");
    }
}
