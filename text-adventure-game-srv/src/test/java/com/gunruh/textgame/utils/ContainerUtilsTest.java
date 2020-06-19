package com.gunruh.textgame.utils;

import com.gunruh.textgame.Game;
import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.characters.Captain;
import com.gunruh.textgame.objects.containerObjects.ContainerObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ContainerUtilsTest {
    private Game game;

    @Before
    public void beforeEachTest() {
        game = Game.createNewGame();
    }

    @Test
    public void testRecursiveRemove1stLevel() {
        ContainerObject box = new ContainerObject("Box", "A small box.", 5) {};
        GameObject rock = new GameObject(game, "Rock", "A small rock.") {};
        box.addItem(rock);

        GameObject removedObject = ContainerUtils.recursiveRemove(box, rock);
        assertEquals(removedObject, rock);
    }

    @Test
    public void testRecursiveRemove2ndLevel() {
        ContainerObject box = new ContainerObject("Box", "A small box.", 5) {};
        ContainerObject smallBox = new ContainerObject("Small Box", "An even smaller box.", 3) {};
        box.addItem(smallBox);

        GameObject rock = new GameObject(game, "Rock", "A small rock.") {};
        smallBox.addItem(rock);

        GameObject removedObject = ContainerUtils.recursiveRemove(box, rock);
        assertEquals(removedObject, rock);
    }

    @Test
    public void testRecursiveRemove3rdLevel() {
        ContainerObject box = new ContainerObject("Box", "A small box.", 5) {};
        ContainerObject smallBox = new ContainerObject("Small Box", "An even smaller box.", 3) {};
        box.addItem(smallBox);

        ContainerObject reallySmallBox = new ContainerObject("Really Small Box", "The smallest box of them all.", 1) {};
        smallBox.addItem(reallySmallBox);

        GameObject rock = new GameObject(game, "Rock", "A small rock.") {};
        reallySmallBox.addItem(rock);

        GameObject removedObject = ContainerUtils.recursiveRemove(box, rock);
        assertEquals(removedObject, rock);
    }

    @Test
    public void testRecursiveRemove1stLevelAfterContainer() {
        ContainerObject box = new ContainerObject("Box", "A small box.", 5) {};
        ContainerObject smallBox = new ContainerObject("Small Box", "An even smaller box.", 3) {};
        box.addItem(smallBox);

        ContainerObject reallySmallBox = new ContainerObject("Really Small Box", "The smallest box of them all.", 1) {};
        smallBox.addItem(reallySmallBox);

        GameObject rock = new GameObject(game, "Rock", "A small rock.") {};
        box.addItem(rock);

        GameObject removedObject = ContainerUtils.recursiveRemove(box, rock);
        assertEquals(removedObject, rock);
    }

    @Test
    public void testRecursiveRemove3rdLevel_notFound() {
        ContainerObject box = new ContainerObject("Box", "A small box.", 5) {};
        ContainerObject smallBox = new ContainerObject("Small Box", "An even smaller box.", 3) {};
        box.addItem(smallBox);

        ContainerObject reallySmallBox = new ContainerObject("Really Small Box", "The smallest box of them all.", 1) {};
        smallBox.addItem(reallySmallBox);

        GameObject rock = new GameObject(game, "Rock", "A small rock.") {};
        // not adding rock to contents - so won't be found.

        GameObject removedObject = ContainerUtils.recursiveRemove(box, rock);
        assertEquals(removedObject, GameObject.EMPTY_GAME_OBJECT);
    }

    @Test
    public void testRecursiveRemove3rdLevel_Container() {
        ContainerObject box = new ContainerObject("Box", "A small box.", 5) {};
        ContainerObject smallBox = new ContainerObject("Small Box", "An even smaller box.", 3) {};
        box.addItem(smallBox);

        ContainerObject reallySmallBox = new ContainerObject("Really Small Box", "The smallest box of them all.", 1) {};
        smallBox.addItem(reallySmallBox);

        GameObject rock = new GameObject(game, "Rock", "A small rock.") {};
        reallySmallBox.addItem(rock);

        GameObject removedObject = ContainerUtils.recursiveRemove(box, reallySmallBox);
        assertEquals(removedObject, reallySmallBox);
    }

    @Test
    public void testContainsInstanceOf() {
        ContainerObject box = new ContainerObject(game, "box", "test box", 3) {};

        Captain captainInstance = new Captain(game);
        box.addItem(captainInstance);

        assertTrue(box.containsInstanceOf(Captain.class));
    }
}