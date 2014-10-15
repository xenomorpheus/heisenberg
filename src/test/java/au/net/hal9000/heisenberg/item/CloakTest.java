package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;
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
     * 
     * @throws InvalidTypeException
     * 
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    @Test
    public void testIsWearable() throws InvalidTypeException,
            TooHeavyException, TooLargeException {
        Human human = new Human();
        Cloak cloak = new Cloak();
        human.wear(cloak);
    }

}
