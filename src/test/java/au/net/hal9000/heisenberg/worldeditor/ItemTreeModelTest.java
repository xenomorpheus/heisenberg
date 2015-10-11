package au.net.hal9000.heisenberg.worldeditor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Arrow;
import au.net.hal9000.heisenberg.item.Box;
import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.api.ItemContainer;

public class ItemTreeModelTest {

    @Test
    public void testItemTreeModel() {
        // leaf
        Item root = new Cookie();
        TreeModel tm = new ItemTreeModel(root);
        assertNotNull(tm);
        // non-leaf
        root = new Box();
        tm = new ItemTreeModel(root);
        assertNotNull(tm);
    }

    @Test
    public void testGetRoot() {
        Item root = new Cookie();
        TreeModel tm = new ItemTreeModel(root);
        Item got = (Item) tm.getRoot();
        assertTrue(root.equals(got));
    }

    @Test
    public void testIsLeaf(){
        ItemContainer box = new Box();
        Item cookie = new Cookie();
        box.add(cookie);
        TreeModel tm = new ItemTreeModel(box);
        assertTrue("cookie is a leaf node", tm.isLeaf(cookie));
        assertFalse("box is NOT a leaf node", tm.isLeaf(box));
    }

    @Test
    public void testGetChildCount(){
        ItemContainer box = new Box();
        Item cookie = new Cookie();
        TreeModel tm = new ItemTreeModel(box);
        assertEquals("empty container", 0, tm.getChildCount(box));
        box.add(cookie);
        assertEquals("1 item in container", 1, tm.getChildCount(box));
    }

    @Test
    public void testGetChild(){
        ItemContainer box = new Box();
        Item cookie = new Cookie();
        Item arrow = new Arrow();
        TreeModel tm = new ItemTreeModel(box);
        box.add(cookie);
        box.add(arrow);
        assertTrue("cookie at pos 0", cookie.equals((Item) tm.getChild(box, 0)));
        assertTrue("arrow at pos 1", arrow.equals((Item) tm.getChild(box, 1)));
    }

    @Test
    public void testGetIndexOfChild(){
        ItemContainer box = new Box();
        Item cookie = new Cookie();
        Item arrow = new Arrow();
        TreeModel tm = new ItemTreeModel(box);
        box.add(cookie);
        box.add(arrow);
        assertEquals("cookie at pos 0", 0, tm.getIndexOfChild(box, cookie));
        assertEquals("arrow at pos 1", 1, tm.getIndexOfChild(box, arrow));
    }

    @Test
    public void testValueForPathChanged(){
        ItemContainer box = new Box();
        Item cookie = new Cookie();
        Item arrow = new Arrow();
        box.add(cookie);
        box.add(arrow);
        TreeModel tm = new ItemTreeModel(box);
        TreePath path = null; // TODO Get path from Root ?
        Item newValue = null; // TODO New Item ?
        // TODO tm.valueForPathChanged(path, newValue);
        // TODO fail("TEST not yet implemented");
    }

}
