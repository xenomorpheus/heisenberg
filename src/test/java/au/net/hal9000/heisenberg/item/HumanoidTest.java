package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;

/**
 */
public class HumanoidTest {
    /**
     * Field WITHIN_MARGIN. (value is 9.0E-5)
     */
    private static final float WITHIN_MARGIN = 0.00009F;

    /*
     * Most tests will use a Human as a representative of Humanoid.
     */
    /**
     * Method testGetWeight.
     */
    @Test
    public void testGetWeight() {
        Human human = new Human(); // Close enough for this abstract class
        human.getHead().setWeightBase(1F);
        human.getLeftHand().setWeightBase(2F);
        human.getRightHand().setWeightBase(4F);
        assertEquals("getWeight", human.getWeight(), 7F, WITHIN_MARGIN);
    }

    /**
     * getRace should return Human.
     */
    @Test
    public void testGetRace() {
        Human human = new Human();
        assertEquals("getRace", "Human", human.getRace());
    }

    /**
     * Method testWear.
     * 

     * @throws InvalidTypeException
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    @Test
    public void testWear() throws InvalidTypeException, 
            TooHeavyException, TooLargeException {
        Human human = new Human(); // Close enough
        Item shield = new Shield();
        human.wear(shield);
    }

    /** Test trying to wear non-clothing.
     * @throws TooLargeException 
     * @throws TooHeavyException 
 
     * @throws InvalidTypeException */
    @Test (expected=InvalidTypeException.class)
    public void testAddItem() throws InvalidTypeException,  TooHeavyException, TooLargeException{
        Human human = new Human();
        Cookie cookie = new Cookie();
        human.wear(cookie);
    }
    
}
