package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

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
        human.add(cloak);
    }

}
