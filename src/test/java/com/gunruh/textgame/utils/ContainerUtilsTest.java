package com.gunruh.textgame.utils;

import com.gunruh.textgame.objects.GameObject;
import com.gunruh.textgame.objects.containerObjects.Container;
import com.gunruh.textgame.objects.containerObjects.ContainerIMPL;
import org.junit.Test;
import sun.plugin2.message.GetAppletMessage;

import static org.junit.Assert.*;

public class ContainerUtilsTest {
    @Test
    public void testRecursiveRemove1stLevel() {
        ContainerIMPL box = new ContainerIMPL("Box", "A small box.", 5) {};
        GameObject rock = new GameObject("Rock", "A small rock.") {};
        box.addItem(rock);

        GameObject removedObject = ContainerUtils.recursiveRemove(box, rock);
        assertEquals(removedObject, rock);
    }

    @Test
    public void testRecursiveRemove2ndLevel() {
        ContainerIMPL box = new ContainerIMPL("Box", "A small box.", 5) {};
        ContainerIMPL smallBox = new ContainerIMPL("Small Box", "An even smaller box.", 3) {};
        box.addItem(smallBox);

        GameObject rock = new GameObject("Rock", "A small rock.") {};
        smallBox.addItem(rock);

        GameObject removedObject = ContainerUtils.recursiveRemove(box, rock);
        assertEquals(removedObject, rock);
    }

    @Test
    public void testRecursiveRemove3rdLevel() {
        ContainerIMPL box = new ContainerIMPL("Box", "A small box.", 5) {};
        ContainerIMPL smallBox = new ContainerIMPL("Small Box", "An even smaller box.", 3) {};
        box.addItem(smallBox);

        ContainerIMPL reallySmallBox = new ContainerIMPL("Really Small Box", "The smallest box of them all.", 1) {};
        smallBox.addItem(reallySmallBox);

        GameObject rock = new GameObject("Rock", "A small rock.") {};
        reallySmallBox.addItem(rock);

        GameObject removedObject = ContainerUtils.recursiveRemove(box, rock);
        assertEquals(removedObject, rock);
    }

    @Test
    public void testRecursiveRemove1stLevelAfterContainer() {
        ContainerIMPL box = new ContainerIMPL("Box", "A small box.", 5) {};
        ContainerIMPL smallBox = new ContainerIMPL("Small Box", "An even smaller box.", 3) {};
        box.addItem(smallBox);

        ContainerIMPL reallySmallBox = new ContainerIMPL("Really Small Box", "The smallest box of them all.", 1) {};
        smallBox.addItem(reallySmallBox);

        GameObject rock = new GameObject("Rock", "A small rock.") {};
        box.addItem(rock);

        GameObject removedObject = ContainerUtils.recursiveRemove(box, rock);
        assertEquals(removedObject, rock);
    }

    @Test
    public void testRecursiveRemove3rdLevel_notFound() {
        ContainerIMPL box = new ContainerIMPL("Box", "A small box.", 5) {};
        ContainerIMPL smallBox = new ContainerIMPL("Small Box", "An even smaller box.", 3) {};
        box.addItem(smallBox);

        ContainerIMPL reallySmallBox = new ContainerIMPL("Really Small Box", "The smallest box of them all.", 1) {};
        smallBox.addItem(reallySmallBox);

        GameObject rock = new GameObject("Rock", "A small rock.") {};
        // not adding rock to contents - so won't be found.

        GameObject removedObject = ContainerUtils.recursiveRemove(box, rock);
        assertEquals(removedObject, GameObject.EMPTY_GAME_OBJECT);
    }
}