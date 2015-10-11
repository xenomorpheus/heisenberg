package au.net.hal9000.heisenberg.worldeditor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.swing.tree.TreePath;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Bag;
import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;

/**
 * Test the ItemPanel.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class ItemTreePanelTest {

    /**
     * Test testItemTreePanel.
     * 
     * @throws ConfigurationError
     */
    @Test
    public void testItemTreePanel() throws ConfigurationError{
        DemoEnvironment.setup();
        Configuration config = Configuration.lastConfig();
        Location location = DemoEnvironment.getDemoWorld();
        ItemTreePanel itemTreePanel = new ItemTreePanel(config, location);
        assertNotNull("Not Null", itemTreePanel);
    }

    @Test
    public void testGetPathToNode(){
        Location world = new Location("World");
        Bag bag = new Bag("bag");
        Cookie cookie = new Cookie();
        world.add(bag);
        bag.add(cookie);
        TreePath expected = new TreePath(new Item[] { world, bag, cookie });
        TreePath got = ItemTreePanel.getPathToNode(cookie);
        assertEquals("path", expected, got);
    }
}
