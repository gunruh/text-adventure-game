package com.gunruh.textgame.console;

import com.gunruh.textgame.Game;
import com.gunruh.textgame.utils.Constants;
import com.gunruh.textgame.utils.IOUtils;

import static com.gunruh.textgame.utils.IOUtils.display;
import static com.gunruh.textgame.utils.IOUtils.isNullOrEmpty;

public class ConsoleGameRunner {
    private Game game;

    public ConsoleGameRunner(Game game) {
        this.game = game;
    }

    private void displayStartText() {
        display(outputBuffer, Constants.SPACE_DUDES_TITLE);
        display(outputBuffer, Constants.INTRO_TEXT);
    }

    public void run() {
        String input = null;

        while (!"quit".equalsIgnoreCase(input)) {
            input = IOUtils.getInputTextFromConsole();

            if (isNullOrEmpty(input)) {
                continue;
            }

            if (input.toLowerCase().trim().contains("quit")) {
                continue;
            }

            // todo - send user input to be parsed by game
        }

        // Exiting loop
        display(outputBuffer, "That's all folks.");
    }
}
