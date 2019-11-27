package com.gunruh.textgame.utils;

import com.gunruh.textgame.objects.Direction;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IOUtils {
    private static Scanner scanner = new Scanner(System.in);

    public static void display(String text) {
        System.out.println(text);
        System.out.println(""); // Spacing
    }

    public static String getInputText() {
        System.out.print("> ");
        String input = scanner.nextLine();
        System.out.println(""); // spacing
        return input;
    }

    public static List<String> getInputListFromText(String userInput) {
        String[] inputList = userInput.trim().split("[\\s]+"); // split on groups of one or more white space.

        return Arrays.asList(inputList);
    }

    public static boolean isArticleAdjective(String inputString) {
        return "a".equalsIgnoreCase(inputString) || "an".equalsIgnoreCase(inputString) || "the".equalsIgnoreCase(inputString);
    }

    public static Direction parseDirection(String input) {
        String cleanedInput = input.toLowerCase().trim();

        if (cleanedInput.contains("northeast") || cleanedInput.contains("ne")) {
            return Direction.NorthEast;
        }
        else if (cleanedInput.contains("southeast") || cleanedInput.contains("se")) {
            return Direction.SouthEast;
        }
        else if (cleanedInput.contains("southwest") || cleanedInput.contains("sw")) {
            return Direction.SouthWest;
        }
        else if (cleanedInput.contains("northwest") || cleanedInput.contains("nw")) {
            return Direction.NorthWest;
        }
        else if (cleanedInput.contains("north") || cleanedInput.contains("n")) {
            return Direction.North;
        }
        else if (cleanedInput.contains("west") || cleanedInput.contains("w")) {
            return Direction.West;
        }
        else if (cleanedInput.contains("east") || cleanedInput.contains("e")) {
            return Direction.East;
        }
        else if (cleanedInput.contains("south") || cleanedInput.contains("s")) {
            return Direction.South;
        }
        else {
            return Direction.NotADirection;
        }
    }
}
