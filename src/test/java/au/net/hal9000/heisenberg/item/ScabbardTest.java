package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;
import au.net.hal9000.heisenberg.item.property.ItemProperty;

/**
 */
public class ScabbardTest {

    /**
     * Method testIsHumanoidClothing.
     */
    @Test
    public void testIsHumanoidClothing() {
        Scabbard scabbard = new Scabbard();
        assertTrue("is humanoid clothing", ItemProperty.isClothing(scabbard));
    }

    /**
     * Method testIsWearable.
     * 
     * 
     * @throws InvalidTypeException
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    @Test
    public void testIsWearable() throws InvalidTypeException,
            TooHeavyException, TooLargeException {
        Human human = new Human();
        Scabbard scabbard = new Scabbard();
        human.wear(scabbard);
    }

    /**
     * Method testAddSword.
     * 
     * 
     * @throws InvalidTypeException
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    @Test
    public void testAddSword() throws InvalidTypeException, TooHeavyException,
            TooLargeException {
        Scabbard scabbard = new Scabbard();
        Sword sword = new Sword();
        scabbard.add(sword);

    }

    // TODO Add test for adding non-sword, should fail.
    // Perhaps consider volume so a penny would fit.

    /**
     * Method testGetIndexOfChild.
     * 
     * 
     * @throws InvalidTypeException
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    @Test
    public void testGetIndexOfChild() throws InvalidTypeException,
            TooHeavyException, TooLargeException {
        Scabbard scabbard = new Scabbard();
        Sword sword = new Sword();
        assertEquals("empty", -1, scabbard.getIndexOfChild(sword));
        scabbard.add(sword);
        assertEquals("has sword", 0, scabbard.getIndexOfChild(sword));
    }

}
