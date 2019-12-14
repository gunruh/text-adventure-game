package com.gunruh.textgame.utils;

import com.gunruh.textgame.objects.Action;
import com.gunruh.textgame.objects.Direction;
import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.Statement;

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
        String cleanedInput = userInput.replaceAll("[.!?\\-\"'%]", "");

        String[] inputList = cleanedInput.trim().split("[\\s]+"); // split on groups of one or more white space.

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

    public static Statement getStatementFromInputList(List<String> inputList, List<GameObject> availableObjects) {
        if (inputList == null || inputList.isEmpty()) {
            return Statement.EMPTY_STATEMENT;
        }
        /*
        Thoughts about parsing...

        Sample sentences:
        UPPERCASE = recognize, lowercase = ignored

        pick up blaster (PICK up BLASTER)
        pick up the blaster (PICK up the BLASTER)
        please pick up the blaster now (please PICK up the BLASTER now)
        the blaster is charging (what would this do?) (BLASTER) --> need verb!
        say "goose" (SAY <anything - remove non-alpha characters>) --> print everything after "say"
        say goose. (SAY <anything>) --> print everything after "say"
        shoot the troll (SHOOT the TROLL) --> print "(with blaster)" or item with highest value of "effectivenessAsBlaster"
        unlock the door with the key (UNLOCK the DOOR with the KEY)
        eat the burrito (EAT the BURRITO)
        go east (GO EAST)
        walk east (WALK EAST)
        look around (LOOK around)
        look at the troll (LOOK at the TROLL)
        where am I? (WHERE am I) --> you're in the East Room
        where is the troll? (WHERE is the TROLL) --> the Troll stands before you
        how healthy is the troll? (how HEALTHY is the TROLL) --> the Troll's health is: <trollHealth>
        get health levels (get HEALTH levels) --> Your health is: <playerHealth>
        unlock the door (UNLOCK the DOOR) --> print "(using the key)" or item with highest value of "effectivenessAsKey"
        use the key to unlock the door (use the KEY to UNLOCK door)
        get on top of the box (GET ON top of the BOX)
        climb the ladder (CLIMB the LADDER)
        climb down the ladder (CLIMB DOWN LADDER)
        go down (GO DOWN)
        go up (GO UP)
        */

        Statement statement = new Statement(null, null, null, null);

        String previousWord = null;
        String currentWord = null;
        int firstObjectIndex = -1;
        GameObject firstObject = null;
        int secondObjectIndex = -1;
        GameObject secondObject = null;
        int actionIndex = -1;
        int directionIndex = -1;
        boolean isActionLeftToRight = true;
        int relationalWordIndex = -1;

        for (int i = 0; i < inputList.size(); i++) {
            if (i > 0) {
                previousWord = inputList.get(i - 1);
            }

            currentWord = inputList.get(i);

            if (isArticleAdjective(currentWord)) {
                // do nothing
                continue;
            }

            Action action = InputMaps.actionMap.get(currentWord);
            if (action != null) {
                actionIndex = i;
                statement.setAction(action);
                continue;
            }

            Direction direction = InputMaps.directionMap.get(currentWord);
            if (direction != null) {
                directionIndex = i;
                statement.setDirection(direction);
                continue;
            }

            if (relationalWordIndex == -1) {
                if (isLeftToRightRelationalWord(currentWord)) {
                    relationalWordIndex = i;
                    continue;
                }
                else if (isRightToLeftRelationalWord(currentWord)) {
                    relationalWordIndex = i;
                    isActionLeftToRight = false;
                    continue;
                }
            }

            // Lastly check for matching available GameObject
            for (GameObject gameObject : availableObjects) {
                if (currentWord.equalsIgnoreCase(gameObject.getNickName()) || currentWord.equalsIgnoreCase(gameObject.getName())) {
                    if (firstObjectIndex == -1) {
                        firstObjectIndex = i;
                        firstObject = gameObject;
                    }
                    else if (secondObjectIndex == -1) {
                        secondObjectIndex = i;
                        secondObject = gameObject;
                    }
                    break;
                }
            }
        }

        // See if objects were recognized, and determine which is the acting object and which is the receiving object.

        // If at least one object was recognized in the statement
        if (firstObjectIndex > -1) {
            // If an action wasn't recognized, just perform "Look" at the first object
            if (actionIndex == -1) {
                // look at first object.
                statement.setAction(Action.Look);
                statement.setReceivingObject(firstObject);
            }
            else if (isActionLeftToRight) {
                if (secondObjectIndex > -1) {
                    statement.setActingObject(firstObject);
                    statement.setReceivingObject(secondObject);
                }
                else {
                    statement.setReceivingObject(firstObject);
                }
            }
            else {
                if (secondObjectIndex > -1) {
                    statement.setActingObject(secondObject);
                    statement.setReceivingObject(firstObject);
                }
                else {
                    statement.setReceivingObject(firstObject);
                }
            }
        }

        return statement;
    }

    private static boolean isRightToLeftRelationalWord(String currentWord) {
        boolean isRightToLeftRelationalWord = false;

        if (!isNullOrEmpty(currentWord)) {
            isRightToLeftRelationalWord = ("using".equalsIgnoreCase(currentWord) || "with".equalsIgnoreCase(currentWord) || "from".equalsIgnoreCase(currentWord));
        }

        return isRightToLeftRelationalWord;
    }

    private static boolean isLeftToRightRelationalWord(String currentWord) {
        boolean isLeftToRightRelationalWord = false;

        if (!isNullOrEmpty(currentWord)) {
            isLeftToRightRelationalWord = ("to".equalsIgnoreCase(currentWord) || "at".equalsIgnoreCase(currentWord) || "upon".equalsIgnoreCase(currentWord) || "onto".equalsIgnoreCase(currentWord) || "into".equalsIgnoreCase(currentWord)
            || "against".equalsIgnoreCase(currentWord));
        }

        return isLeftToRightRelationalWord;
    }

    public static boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }
}
