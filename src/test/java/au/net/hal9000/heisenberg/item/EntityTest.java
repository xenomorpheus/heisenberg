package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Entity;
import au.net.hal9000.heisenberg.item.Human;
import au.net.hal9000.heisenberg.item.Humanoid;
import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.Shield;
import au.net.hal9000.heisenberg.item.property.ItemProperty;

public class EntityTest {

    @Test
    public void testGender() {
        final String expected = "some name";
        Human human = new Human();
        human.setGender(expected);
        assertEquals(expected, human.getGender());
    }

    @Test
    public void testSize() {
        final String expected = "some name";
        Human human = new Human();
        human.setSize(expected);
        assertEquals(expected, human.getSize());
    }

    @Test
    public void testInstanceof() {
        Human human = new Human();
        assertTrue("is Humanoid", human instanceof Humanoid);
        assertTrue("is Entity", human instanceof Entity);
        assertTrue("is Living", ItemProperty.isLiving(human));
    }

    @Test
    public void testAdd() {

        // Human
        Human human = new Human("Human"); // Close enough
        Item shield = new Shield();
        try {
            human.add(shield);
        } catch (Exception e) {
            fail("equip failed " + e);
        }
    }

    // TODO clone - On an abstract class ?
    // TODO equals
    // TODO persistence
}
