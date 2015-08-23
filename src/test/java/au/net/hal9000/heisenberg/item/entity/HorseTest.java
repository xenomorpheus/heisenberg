package au.net.hal9000.heisenberg.item.entity;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.entity.Entity;
import au.net.hal9000.heisenberg.item.entity.Horse;
import au.net.hal9000.heisenberg.item.property.HumanoidMount;
import au.net.hal9000.heisenberg.item.property.ItemProperty;

/**
 */
public class HorseTest {

    /**
     * Method testInstanceof.
     */
    @Test
    public void testInstanceof() {
        Item horse = new Horse();
        assertTrue("is humanoid mount", horse instanceof HumanoidMount);
        assertTrue("is Entity", horse instanceof Entity);
        assertTrue("is Living", ItemProperty.isLiving(horse));
    }

}
