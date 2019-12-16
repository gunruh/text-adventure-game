package com.gunruh.textgame.utils;

import com.gunruh.textgame.enumerations.Action;
import com.gunruh.textgame.enumerations.Direction;
import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.Statement;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.*;

public class IOUtilsTest {
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
        GameObject blaster = new GameObject("Blaster", "A laser gun.") {};
        List<String> inputList = IOUtils.getInputListFromText(sampleInput);
        Statement statement = IOUtils.getStatementFromInputList(inputList, Collections.singletonList(blaster), null);

        assertEquals(Action.Look, statement.getAction());
        assertEquals(blaster, statement.getReceivingObject());
    }

    @Test
    public void testConvertInputListToStatement() {
        String sampleInput = "pick up the blaster.";
        GameObject blaster = new GameObject("Blaster", "A laser gun.") {};
        List<String> inputList = IOUtils.getInputListFromText(sampleInput);
        Statement statement = IOUtils.getStatementFromInputList(inputList, new ArrayList<GameObject>(), Collections.singletonList(blaster));

        assertEquals(Action.Take, statement.getAction());
        assertEquals(blaster, statement.getReceivingObject());
    }

    @Test
    public void testConvertInputListToDirection() {
        String sampleInput = "walk north.";
        GameObject blaster = new GameObject("Blaster", "A laser gun.") {};
        List<String> inputList = IOUtils.getInputListFromText(sampleInput);
        Statement statement = IOUtils.getStatementFromInputList(inputList, Collections.singletonList(blaster), null);

        assertEquals(Action.Move, statement.getAction());
        assertEquals(Direction.North, statement.getDirection());
    }

    @Test
    public void testConvertInputListToActionRightToLeft() {
        String sampleInput = "shoot the rock with the blaster";
        GameObject blaster = new GameObject("Blaster", "A laser gun.") {};
        GameObject rock = new GameObject("Rock", "It's Just a rock.") {};

        List<String> inputList = IOUtils.getInputListFromText(sampleInput);
        Statement statement = IOUtils.getStatementFromInputList(inputList, Collections.singletonList(blaster), Collections.singletonList(rock));

        assertEquals(Action.Shoot, statement.getAction());
        assertNull(statement.getDirection());
        assertEquals("acting object was wrong", blaster, statement.getActingObject());
        assertEquals("receiving object was wrong", rock, statement.getReceivingObject());
    }

    @Test
    public void testConvertInputListToActionLeftToRight() {
        String sampleInput = "shoot the blaster at the rock";
        GameObject blaster = new GameObject("Blaster", "A laser gun.") {};
        GameObject rock = new GameObject("Rock", "It's Just a rock.") {};

        List<String> inputList = IOUtils.getInputListFromText(sampleInput);
        Statement statement = IOUtils.getStatementFromInputList(inputList, Collections.singletonList(blaster), Collections.singletonList(rock));

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
}
