package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;
import au.net.hal9000.heisenberg.item.property.ItemProperty;

/**
 */
public class ShieldTest {

    /**
     * Method shieldAdd.
     * 
     * 
     * @throws InvalidTypeException
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    @Test
    public void testShieldAdd() throws InvalidTypeException, TooHeavyException,
            TooLargeException {
        Shield shield = new Shield();
        Human human = new Human();
        human.wear(shield);
    }

    /**
     * clothing test.
     */
    @Test
    public void testClothing() {
        Item shield = new Shield();
        assertTrue("isClothing", ItemProperty.isClothing(shield));

    }

}
