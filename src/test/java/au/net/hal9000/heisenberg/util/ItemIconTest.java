/**
 * 
 */
package au.net.hal9000.heisenberg.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import au.net.hal9000.heisenberg.item.Arrow;
import au.net.hal9000.heisenberg.item.entity.Cat;
import au.net.hal9000.heisenberg.item.entity.Human;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;

/**
 * @author bruins
 * 
 * @version $Revision: 1.0 $
 */
public class ItemIconTest {

    /**
     * Method setUp.
     */
    @Before
    public void setUp() {
        DemoEnvironment.setup();
    }
    /**
     * Method testSetIcon.
     * @throws ConfigurationError
     */
    @Test
    public void testSetIcon() throws ConfigurationError {
        Configuration config = Configuration.lastConfig();
        ItemIcon.setIcon(config);
        Human human = new Human();
        assertNotNull(human.getItemIcon().getIconOpen());
        Arrow arrow = new Arrow();
        assertNotNull(arrow.getItemIcon().getIconOpen());
        Cat cat = new Cat();
        assertNull(cat.getItemIcon().getIconOpen());
    }

}
