package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class BagTest {

    /**
     * Normal item into bag.
     * 
     * @throws InvalidTypeException
     * 
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    @Test
    public void testAddOrdinary() throws InvalidTypeException,
            TooHeavyException, TooLargeException {
        Location world = new Location("World");
        Cookie cookie = new Cookie();
        // set the location so we can see that it changes
        cookie.setContainer(world);
        Bag bag = new Bag();
        bag.add(cookie);
        // item's location changes to bag
        assertEquals("cookie location", bag, cookie.getContainer());
    }

    /**
     * Sharp items throw ExceptionInvalidType.
     * 
     * @throws InvalidTypeException
     * 
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    @Test(expected = InvalidTypeException.class)
    public void testAddSharpRupture() throws InvalidTypeException,
            TooHeavyException, TooLargeException {
        Sword sword = new Sword();
        Location world = new Location("World");
        sword.setContainer(world);
        Bag bag = new Bag();
        try {
            bag.add(sword);
        } catch (InvalidTypeException e) {
            // Item's location remains unchanged.
            assertEquals("cookie location", world, sword.getContainer());
            throw e;
        }
    }

}
