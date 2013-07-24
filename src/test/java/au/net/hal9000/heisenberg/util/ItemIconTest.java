/**
 * 
 */
package au.net.hal9000.heisenberg.util;

import static org.junit.Assert.*;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Human;

/**
 * @author bruins
 * 
 */
public class ItemIconTest {

    @Test
    public void testSetIcon() throws ConfigurationError {
        Configuration config = DummyData.config();
        ItemIcon.setIcon(config);
        Human human = new Human();
        assertNotNull(human.getIconOpen());
    }

}
