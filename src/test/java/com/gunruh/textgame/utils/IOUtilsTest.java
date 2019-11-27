package com.gunruh.textgame.utils;

import com.gunruh.textgame.objects.Direction;
import org.junit.Test;

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
}
