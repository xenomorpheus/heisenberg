package au.net.hal9000.heisenberg.worldeditor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.swing.tree.TreePath;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.DummyData;

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
     * @throws InvalidTypeException
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    @Test
    public void testItemTreePanel() throws ConfigurationError,
            InvalidTypeException, TooHeavyException, TooLargeException {
        Configuration config = DummyData.getConfig();
        Location location = DummyData.getDemoWorld();
        ItemTreePanel itemTreePanel = new ItemTreePanel(config, location);
        assertNotNull("Not Null", itemTreePanel);
    }

    @Test
    public void testGetPathToRoot() throws TooHeavyException,
            TooLargeException, InvalidTypeException {
        Location world = new Location("World");
        Cookie cookie = new Cookie();
        world.add(cookie);
        TreePath expected = new TreePath(new Item[] { cookie, world });
        TreePath got = ItemTreePanel.getPathToRoot(cookie);
        assertEquals("path", expected, got);
    }
}
