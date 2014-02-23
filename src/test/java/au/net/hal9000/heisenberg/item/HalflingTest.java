package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


/**
 */
public class HalflingTest {

    /**
     * Method testInstanceof.
     */
    @Test
    public void testInstanceof() {
        Object halfling = new Halfling();
        assertTrue("is Humanoid", halfling instanceof Humanoid);
        assertTrue("is Entity", halfling instanceof Entity);
        assertTrue("is item", halfling instanceof Item);
    }

    /**
     * Method testGetRace.
     */
    @Test
    public void testGetRace() {
        Halfling halfling = new Halfling();
        assertEquals("Halfling", halfling.getName());
    }
}
