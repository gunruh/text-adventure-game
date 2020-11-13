package com.gunruh.textgame.utils;

import com.gunruh.textgame.Game;
import com.gunruh.textgame.enumerations.Action;
import com.gunruh.textgame.enumerations.Direction;
import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.Player;
import com.gunruh.textgame.objects.rooms.Room;
import com.gunruh.textgame.objects.Statement;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static junit.framework.TestCase.*;

public class IOUtilsTest {
    Game game;

    @Before
    public void beforeEachTest() {
        game = Game.createNewGame(false);
    }

    @Test
    public void testGetInputTextList() {
        assertEquals("go", IOUtils.getInputListFromText("go west").get(0));
        assertEquals("west", IOUtils.getInputListFromText("go west").get(1));
        assertEquals("go", IOUtils.getInputListFromText("go  west").get(0));
        assertEquals("west", IOUtils.getInputListFromText("go  west").get(1));
        assertEquals("go", IOUtils.getInputListFromText("go\nwest").get(0));
        assertEquals("go", IOUtils.getInputListFromText("go\n\nwest").get(0));
        assertEquals("west", IOUtils.getInputListFromText("go\nwest").get(1));
        assertEquals("west", IOUtils.getInputListFromText("go\n\nwest").get(1));

        assertEquals("pick", IOUtils.getInputListFromText("pick up blaster").get(0));
        assertEquals("up", IOUtils.getInputListFromText("pick up blaster").get(1));
        assertEquals("blaster", IOUtils.getInputListFromText("pick up blaster").get(2));
        assertEquals("up", IOUtils.getInputListFromText("pick  up blaster").get(1));
        assertEquals("up", IOUtils.getInputListFromText("pick up  blaster").get(1));
        assertEquals("blaster", IOUtils.getInputListFromText("pick up  blaster").get(2));
        assertEquals("blaster", IOUtils.getInputListFromText("pick up    blaster").get(2));
        assertEquals("pick", IOUtils.getInputListFromText(" pick up blaster").get(0));
        assertEquals("blaster", IOUtils.getInputListFromText("pick up blaster ").get(2));

        assertEquals("blaster", IOUtils.getInputListFromText("pick up blaster.").get(2));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testInputListFromTextShouldntHave3rdIndex() {
        assertEquals("pick", IOUtils.getInputListFromText("pick up blaster ").get(3));
    }

    @Test
    public void testIsArticleAdjective() {
        assertTrue(IOUtils.isArticleAdjective("the"));
        assertTrue(IOUtils.isArticleAdjective("an"));
        assertTrue(IOUtils.isArticleAdjective("a"));

        assertFalse(IOUtils.isArticleAdjective("blaster"));
        assertFalse(IOUtils.isArticleAdjective(" "));
        assertFalse(IOUtils.isArticleAdjective(null));
        assertFalse(IOUtils.isArticleAdjective(""));
    }

    @Test
    public void testParseDirection() {
        assertEquals(Direction.North, IOUtils.parseDirection("north"));
        assertEquals(Direction.NorthEast, IOUtils.parseDirection("northeast"));
        assertEquals(Direction.East, IOUtils.parseDirection("east"));
        assertEquals(Direction.SouthEast, IOUtils.parseDirection("southeast"));
        assertEquals(Direction.South, IOUtils.parseDirection("south"));
        assertEquals(Direction.SouthWest, IOUtils.parseDirection("southwest"));
        assertEquals(Direction.West, IOUtils.parseDirection("west"));
        assertEquals(Direction.NorthWest, IOUtils.parseDirection("northwest"));

        assertEquals(Direction.North, IOUtils.parseDirection("n"));
        assertEquals(Direction.NorthEast, IOUtils.parseDirection("ne"));
        assertEquals(Direction.East, IOUtils.parseDirection("e"));
        assertEquals(Direction.SouthEast, IOUtils.parseDirection("se"));
        assertEquals(Direction.South, IOUtils.parseDirection("s"));
        assertEquals(Direction.SouthWest, IOUtils.parseDirection("sw"));
        assertEquals(Direction.West, IOUtils.parseDirection("w"));
        assertEquals(Direction.NorthWest, IOUtils.parseDirection("nw"));
    }

    @Test
    public void testConvertInputListToAction_DefaultLook() {
        String sampleInput = "Blaster.";
        GameObject blaster = new GameObject(game, "Blaster", "A laser gun.") {};
        Statement statement = IOUtils.getStatementFromInputText(sampleInput, Collections.singletonList(blaster), null);

        assertEquals(Action.Look, statement.getAction());
        assertEquals(blaster, statement.getReceivingObject());
    }

    @Test
    public void testConvertInputListToStatement() {
        String sampleInput = "pick up the blaster.";
        GameObject blaster = new GameObject(game, "Blaster", "A laser gun.") {};
        Statement statement = IOUtils.getStatementFromInputText(sampleInput, new ArrayList<GameObject>(), Collections.singletonList(blaster));

        assertEquals(Action.Take, statement.getAction());
        assertEquals(blaster, statement.getReceivingObject());
    }

    @Test
    public void testConvertInputListToDirection() {
        String sampleInput = "walk north.";
        GameObject blaster = new GameObject(game, "Blaster", "A laser gun.") {};
        Statement statement = IOUtils.getStatementFromInputText(sampleInput, Collections.singletonList(blaster), null);

        assertEquals(Action.Move, statement.getAction());
        assertEquals(Direction.North, statement.getDirection());
    }

    @Test
    public void testConvertInputListToActionRightToLeft() {
        String sampleInput = "shoot the rock with the blaster";
        GameObject blaster = new GameObject(game, "Blaster", "A laser gun.") {};
        GameObject rock = new GameObject(game, "Rock", "It's Just a rock.") {};

        Statement statement = IOUtils.getStatementFromInputText(sampleInput, Collections.singletonList(blaster), Collections.singletonList(rock));

        assertEquals(Action.Shoot, statement.getAction());
        assertNull(statement.getDirection());
        assertEquals("acting object was wrong", blaster, statement.getActingObject());
        assertEquals("receiving object was wrong", rock, statement.getReceivingObject());
    }

    @Test
    public void testConvertInputListToActionLeftToRight() {
        String sampleInput = "shoot the blaster at the rock";
        GameObject blaster = new GameObject(game, "Blaster", "A laser gun.") {};
        GameObject rock = new GameObject(game, "Rock", "It's Just a rock.") {};

        Statement statement = IOUtils.getStatementFromInputText(sampleInput, Collections.singletonList(blaster), Collections.singletonList(rock));

        assertEquals(Action.Shoot, statement.getAction());
        assertNull(statement.getDirection());
        assertEquals("acting object was wrong", blaster, statement.getActingObject());
        assertEquals("receiving object was wrong", rock, statement.getReceivingObject());
    }

    @Test
    public void testIsNullOrEmpty() {
        assertTrue(IOUtils.isNullOrEmpty(null));
        assertTrue(IOUtils.isNullOrEmpty(""));

        assertFalse(IOUtils.isNullOrEmpty(" "));
        assertFalse(IOUtils.isNullOrEmpty("hello"));
        assertFalse(IOUtils.isNullOrEmpty(" hello "));
    }

    @Test
    public void testGetAorAn() {
        assertEquals("a", IOUtils.getAorAnForString("grape"));
        assertEquals("a", IOUtils.getAorAnForString("ball"));
        assertEquals("a", IOUtils.getAorAnForString("Cactus"));
        assertEquals("a", IOUtils.getAorAnForString(" young man"));
        assertEquals("a", IOUtils.getAorAnForString("yule tide"));
        assertEquals("a", IOUtils.getAorAnForString("western movie"));
        assertEquals("a", IOUtils.getAorAnForString("Duckling"));
        assertEquals("a", IOUtils.getAorAnForString("pineapple "));
        assertEquals("a", IOUtils.getAorAnForString("flute"));
        assertEquals("a", IOUtils.getAorAnForString("Quick brown fox"));
        assertEquals("a", IOUtils.getAorAnForString("carwash"));

        assertEquals("an", IOUtils.getAorAnForString("apple"));
        assertEquals("an", IOUtils.getAorAnForString("Android"));
        assertEquals("an", IOUtils.getAorAnForString("Elephant"));
        assertEquals("an", IOUtils.getAorAnForString(" ink pen"));
        assertEquals("an", IOUtils.getAorAnForString("Octopus "));
        assertEquals("an", IOUtils.getAorAnForString("Human"));
        assertEquals("an", IOUtils.getAorAnForString("old goose"));
        assertEquals("an", IOUtils.getAorAnForString("uncle"));
    }

    @Test
    public void testGetItemsText_ThreeItems() {
        // A Blaster nicknamed "Mike"
        GameObject mike = new GameObject(game, "Blaster", "A laser gun.") {};
        mike.setNickName("Mike");
        GameObject bike = new GameObject(game, "Bike", "A blue bicycle.") {};
        GameObject apple = new GameObject(game, "Apple", "A delicious and nutritious fruit.") {};

        List<GameObject> gameObjects = new ArrayList<GameObject>(Arrays.asList(mike, bike, apple));

        assertEquals("Mike, a Bike, and an Apple", IOUtils.getSentenceStringFromGameObjectsList(gameObjects));
    }

    @Test
    public void testGetItemsText_TwoItems() {
        // A Blaster nicknamed "Mike"
        GameObject mike = new GameObject(game, "Blaster", "A laser gun.") {};
        mike.setNickName("Mike");
        GameObject apple = new GameObject(game, "Apple", "A delicious and nutritious fruit.") {};

        List<GameObject> gameObjects = new ArrayList<GameObject>(Arrays.asList(mike, apple));

        assertEquals("Mike and an Apple", IOUtils.getSentenceStringFromGameObjectsList(gameObjects));
    }

    @Test
    public void testGetItemsText_OneItemWithNickName() {
        // A Blaster nicknamed "Mike"
        GameObject mike = new GameObject(game, "Blaster", "A laser gun.") {};
        mike.setNickName("Mike");

        List<GameObject> gameObjects = new ArrayList<GameObject>(Arrays.asList(mike));

        assertEquals("Mike", IOUtils.getSentenceStringFromGameObjectsList(gameObjects));
    }

    @Test
    public void testGetItemsText_OneItemNoNickname() {
        GameObject apple = new GameObject(game, "Apple", "A delicious and nutritious fruit.") {};

        List<GameObject> gameObjects = new ArrayList<GameObject>(Arrays.asList(apple));

        assertEquals("an Apple", IOUtils.getSentenceStringFromGameObjectsList(gameObjects));
    }

    @Test
    public void testCapitlizeFirstLetter() {
        assertEquals("An apricot.", IOUtils.capitalizeFirstLetter("an apricot."));
        assertEquals("An apricot.", IOUtils.capitalizeFirstLetter("An apricot."));
        assertEquals("A", IOUtils.capitalizeFirstLetter("a"));
        assertEquals("The", IOUtils.capitalizeFirstLetter("the"));
        assertEquals("Blaster", IOUtils.capitalizeFirstLetter("blaster"));
        assertEquals("", IOUtils.capitalizeFirstLetter(""));
        assertEquals(null, IOUtils.capitalizeFirstLetter(null));
    }

    @Test
    public void testRoomDescriptionMultipleItems() {
        Room room = new Room(game, "Test Room", "A room to test.") {};

        room.addItem(new GameObject(game, "blaster", "A blasting device.") {});
        room.addItem(new GameObject(game, "rock", "Just an old rock.") {});
        room.addItem(new GameObject(game, "apple", "Tasty fruit.") {});

        assertEquals("A room to test.\nA blaster, a rock, and an apple are here.", room.getDescription());
    }

    @Test
    public void testRoomDescriptionSingleItem() {
        Room room = new Room(game, "Test Room", "A room to test.") {};

        room.addItem(new GameObject(game, "blaster", "A blasting device.") {});

        assertEquals("A room to test.\nA blaster is here.", room.getDescription());
    }

    @Test
    public void testNameObject() {
        GameObject blaster = new GameObject(game, "Blaster", "A laser gun.") {};

        Statement statement = IOUtils.getStatementFromInputText("Name the blaster Big Steve 2000", Collections.singletonList(blaster), null);

        assertEquals("Big Steve 2000", statement.getRemainingString());
    }

    @Test
    public void testNameAlreadyNamedObject() {
        GameObject blaster = new GameObject(game, "Blaster", "A laser gun.") {};
        blaster.setNickName("Big Steve 2000");

        Statement statement = IOUtils.getStatementFromInputText("Name Big Steve 2000 Reginald Stevenson III", Collections.singletonList(blaster), null);

        assertEquals("Reginald Stevenson III", statement.getRemainingString());
    }

    @Test
    public void testGetNickNameOrNameWithArticle() {
        GameObject anderson = new GameObject(game, "Rock", "A test rock.") {};
        anderson.setNickName("Anderson");
        assertEquals("Anderson", IOUtils.getNickNameOrNameWithArticle(anderson));

        GameObject rock = new GameObject(game, "Rock", "Another test rock.") {};
        assertEquals("the Rock", IOUtils.getNickNameOrNameWithArticle(rock));
    }

    @Test
    public void testDestroyRoomObject() {
        GameObject rock = new GameObject(game, "Rock", "A rock for testing.") {};
        Room room = new Room(game, "Room", "A Room for testing") {};
        room.addItem(rock);
        game.getPlayer().setCurrentRoom(room);
        rock.takeDamage(100);
        assertFalse(room.getItems().contains(rock));
    }

    @Test
    public void testDestroyPlayerInventoryObject() {
        GameObject rock = new GameObject(game, "Rock", "A rock for testing.") {};
        game.getPlayer().addItem(rock);
        rock.takeDamage(100);
        assertFalse(game.getPlayer().getItems().contains(rock));
    }

    @Test
    public void testNormalizeInput_RemoveArticleAdjectives() {
        assertEquals("", IOUtils.normalizeInput("", true));
        assertEquals("", IOUtils.normalizeInput("the", true));
        assertEquals("", IOUtils.normalizeInput("a", true));
        assertEquals("", IOUtils.normalizeInput("an", true));
        assertEquals("apple", IOUtils.normalizeInput("the apple", true));
        assertEquals("apple", IOUtils.normalizeInput("an apple ", true));
        assertEquals("apple", IOUtils.normalizeInput("a apple  ", true));
    }

    @Test
    public void shouldPrintDivider() {
        assertEquals("\n* ", IOUtils.getDivider(1));
        assertEquals("\n* * ", IOUtils.getDivider(2));
        assertEquals("\n* * * ", IOUtils.getDivider(3));
    }

    @Test
    public void shouldSplitIntoLinesOfDesiredWidth() {
        // test for width 50.
//        assertEquals(
//                "1234567 10 234567 20 234567 30 234567 40 234567 50\n 1234567 10 234567 20 234567 30 234567 40 234567\n 50",
//                IOUtils.controlWidth(
//                        "1234567 10 234567 20 234567 30 234567 40 234567 50 1234567 10 234567 20 234567 30 234567 40 234567 50")
//        );

        int numberOfWords = 10;
        int numberOfCharsPerWord = 9;
        String text = getLongRandomString(numberOfWords, numberOfCharsPerWord);
        System.out.println("test text:\n" + text);
        assertEquals(numberOfWords * numberOfCharsPerWord + (numberOfWords - 1), text.length());

        String controlledWidthText = IOUtils.controlWidth(text);
        System.out.println("controlled width text:\n" + controlledWidthText);

        String[] lines = controlledWidthText.split("\n");
        for (String line : lines) {
            assertTrue(line.length() <= Constants.ESTIMATED_SCREEN_WIDTH);
        }
    }

    private String getLongRandomString(int words, int wordLength) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < words; i++) {
            for (int j = 0; j < wordLength; j++) {
                stringBuilder.append("a");
            }
            if (i + 1 < words) {
                stringBuilder.append(" ");
            }
        }

        return stringBuilder.toString();
    }
}
