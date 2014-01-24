package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class HalflingTest {

    @Test
    public void testInstanceof() {

        Halfling halfling = new Halfling();
        assertTrue("is Halfling", halfling instanceof Halfling);
        assertTrue("is Halflingoid", halfling instanceof Halfling);
        assertTrue("is Entity", halfling instanceof Entity);
        assertTrue("is item", halfling instanceof Item);
    }

    @Test
    public void testGetRace() {
        Halfling halfling = new Halfling();
        assertEquals("Halfling", halfling.getName());
    }
}
