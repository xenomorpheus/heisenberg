package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Bag;
import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.Sword;
import au.net.hal9000.heisenberg.item.exception.*;

public class BagTest {

    // Normal item into bag.
    // item's location changes to bag
    @Test
    public void testAddOrdinary() {
        Location world = new Location("World");
        Cookie cookie = new Cookie();
        // set the location so we can see that it changes
        cookie.setContainer(world);
        Bag bag = new Bag();
        bag.add(cookie);
        assertEquals("cookie location", bag, cookie.getContainer());
    }

    // Sharp items throw ExceptionInvalidType
    // Item's location remains unchanged.
    @Test
    public void testAddSharpRupture() {
        Sword sword = new Sword();
        Location world = new Location("World");
        sword.setContainer(world);
        Bag bag = new Bag();
        try {
            bag.add(sword);
            fail("Expecting invalid type");
        } catch (ExceptionInvalidType e) {
            // nothing to do
        } catch (ExceptionTooHeavy e) {
            fail("too heavy");
        } catch (ExceptionTooBig e) {
            fail("too big");
        }
        assertEquals("cookie location", world, sword.getContainer());
    }

}
