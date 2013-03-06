package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;
import org.junit.Test;

import au.net.hal9000.heisenberg.item.Cloak;
import au.net.hal9000.heisenberg.item.Human;
import au.net.hal9000.heisenberg.item.property.ItemProperty;

public class CloakTest {

    @Test
    public void testIsHumanoidClothing() {
        Cloak cloak = new Cloak();
        assertTrue("is Clothing", ItemProperty.isClothing(cloak));
    }

    @Test
    public void testIsWearable() {
        Human human = new Human();
        Cloak cloak = new Cloak();
        try {
            human.add(cloak);
        } catch (Exception e) {
            fail(e.toString());
        }
    }

}
