package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.entity.Human;
import au.net.hal9000.heisenberg.item.property.ItemProperty;

/**
 */
public class CloakTest {

    /**
     * Method testIsHumanoidClothing.
     */
    @Test
    public void testIsHumanoidClothing() {
        Cloak cloak = new Cloak();
        assertTrue("is Clothing", ItemProperty.isClothing(cloak));
    }

    /**
     * Method testIsWearable.
     */
    @Test
    public void testIsWearable(){
        Human human = new Human();
        Cloak cloak = new Cloak();
        human.wear(cloak);
    }

}
