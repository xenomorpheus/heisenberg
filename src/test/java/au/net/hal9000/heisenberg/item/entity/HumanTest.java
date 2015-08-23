package au.net.hal9000.heisenberg.item.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.entity.Entity;
import au.net.hal9000.heisenberg.item.entity.Human;

/**
 */
public class HumanTest {

    /**
     * Method testInstanceof.
     */
    @Test
    public void testInstanceof() {

        Object human = new Human();
        assertTrue("is Human", human instanceof Human);
        assertTrue("is Humanoid", human instanceof Human);
        assertTrue("is Entity", human instanceof Entity);
        assertTrue("is item", human instanceof Item);
    }

    /**
     * Method testGetRace.
     */
    @Test
    public void testGetRace() {
        Human human = new Human();
        assertEquals("Human", human.getName());
    }

    /**
     * Test drink
     */
    @Test
    public void testDrink() {
        // TODO Human human = new Human();
        // TODO Water water = new Water();
        // human.drink(water);
    }
}
