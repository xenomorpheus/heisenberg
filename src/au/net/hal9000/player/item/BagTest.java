package au.net.hal9000.player.item;

import static org.junit.Assert.*;
import org.junit.Test;

import au.net.hal9000.player.item.exception.*;

public class BagTest {

	// Normal item into bag.
	// item's location changes to bag
	@Test
	public void testAddOrdinary() {
		Location world = new Location("World");
		Cookie cookie = new Cookie();
		// set the location so we can see that it changes
		cookie.setLocation(world);
		Bag bag = new Bag();
		try {
			bag.add(cookie);
		} catch (ExceptionInvalidType e) {
			fail("invalid type");
		} catch (ExceptionTooHeavy e) {
			fail("too heavy");
		} catch (ExceptionTooBig e) {
			fail("too big");
		}
		assertEquals("cookie location", bag, cookie.getLocation());
	}

	// Sharp items throw ExceptionInvalidType
	// Item's location remains unchanged.
	@Test
	public void testAddSharpRupture() {
		Sword sword = new Sword();
		Location world = new Location("World");
        sword.setLocation(world);
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
		assertEquals("cookie location", world, sword.getLocation());
	}

}
