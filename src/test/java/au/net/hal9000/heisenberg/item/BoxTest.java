package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;

/**
 * Test Box class.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class BoxTest {

    /**
     * Normal item into box.
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
        Sword sword = new Sword();
        // set the location so we can see that it changes
        cookie.setContainer(world);
        sword.setContainer(world);
        Box box = new Box();
        box.add(cookie);
        box.add(sword);

        // item's location changes to box
        assertEquals("cookie location", box, cookie.getContainer());
        assertEquals("sword location", box, sword.getContainer());
    }

}
