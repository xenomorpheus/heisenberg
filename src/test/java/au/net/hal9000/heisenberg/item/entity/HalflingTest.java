package au.net.hal9000.heisenberg.item.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.entity.Entity;
import au.net.hal9000.heisenberg.item.entity.Humanoid;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;

/**
 */
public class HalflingTest {

    @Before
    public void initialize() {
        DemoEnvironment.setup();
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
        ItemProperty.setNourishment(halfling, ItemProperty.HEALTH_METRIC_IDEAL / 2);
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
