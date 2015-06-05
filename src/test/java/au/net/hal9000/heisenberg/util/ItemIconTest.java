/**
 * 
 */
package au.net.hal9000.heisenberg.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Arrow;
import au.net.hal9000.heisenberg.item.Human;
import au.net.hal9000.heisenberg.item.Cat;

/**
 * @author bruins
 * 
 * @version $Revision: 1.0 $
 */
public class ItemIconTest {

    /**
     * Method testSetIcon.
     * @throws ConfigurationError
     */
    @Test
    public void testSetIcon() throws ConfigurationError {
        Configuration config = DummyData.getConfig();
        ItemIcon.setIcon(config);
        Human human = new Human();
        assertNotNull(human.getIconOpen());
        Arrow arrow = new Arrow();
        assertNotNull(arrow.getIconOpen());
        Cat cat = new Cat();
        assertNull(cat.getIconOpen());
    }

}
