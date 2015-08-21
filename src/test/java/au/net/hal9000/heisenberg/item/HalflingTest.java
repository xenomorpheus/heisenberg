package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.util.TestEnvironment;

/**
 */
public class HalflingTest {

    @Before
    public void initialize() {
        TestEnvironment.setup();
    }

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

    @Test
    public void halflingEatsACookie() throws InvalidTypeException {
        // Setup
        Halfling halfling = new Halfling();
        halfling.setActionPoints(3);
        ItemProperty.setNourishment(halfling, Entity.HEALTH_METRIC_IDEAL / 2);
        float nourishment_before = ItemProperty.getNourishment(halfling);
        Cookie cookie = new Cookie();
        // Run
        halfling.eat(cookie);
        // Test
        float nourishment_after = ItemProperty.getNourishment(halfling);
        assertTrue("Eating increases nouritshment",
                nourishment_after > nourishment_before);

    }

}
