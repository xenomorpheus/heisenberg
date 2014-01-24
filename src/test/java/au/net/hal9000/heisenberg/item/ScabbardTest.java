package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.property.ItemProperty;

public class ScabbardTest {

    @Test
    public void testIsHumanoidClothing() {
        Scabbard scabbard = new Scabbard();
        assertTrue("is humanoid clothing", ItemProperty.isClothing(scabbard));
    }

    @Test
    public void testIsWearable() {
        Human human = new Human();
        Scabbard scabbard = new Scabbard();
        human.add(scabbard);
    }

    @Test
    public void testAddSword() {
        Scabbard scabbard = new Scabbard();
        Sword sword = new Sword();
        try {
            scabbard.add(sword);
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    // TODO Add test for adding non-sword, should fail.
    // Perhaps consider volume so a penny would fit.

    @Test
    public void testGetIndexOfChild() {
        Scabbard scabbard = new Scabbard();
        Sword sword = new Sword();
        assertEquals("empty", -1, scabbard.getIndexOfChild(sword));
        scabbard.add(sword);
        assertEquals("has sword", 0, scabbard.getIndexOfChild(sword));
    }

}
