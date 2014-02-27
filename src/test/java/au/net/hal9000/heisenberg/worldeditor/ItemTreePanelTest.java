package au.net.hal9000.heisenberg.worldeditor;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Location;

import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.DummyData;

/**
 * Test the ItemPanel. * @author bruins
 * 
 * @version $Revision: 1.0 $
 */
public class ItemTreePanelTest {

    /**
     * Test itemTreePanel.
     * 
     * 
     * @throws ConfigurationError

     * @throws InvalidTypeException
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    @Test
    public void itemTreePanel() throws ConfigurationError,
            InvalidTypeException,  TooHeavyException,
            TooLargeException {
        Configuration config = DummyData.config();
        Location location = DummyData.getDemoWorld();
        ItemTreePanel itemTreePanel = new ItemTreePanel(config, location);
        assertNotNull("Not Null", itemTreePanel);
    }
}
