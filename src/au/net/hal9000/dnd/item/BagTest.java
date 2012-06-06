package au.net.hal9000.dnd.item;

import static org.junit.Assert.*;

import org.junit.Test;

import au.net.hal9000.dnd.item.exception.ExceptionInvalidType;
import au.net.hal9000.dnd.item.exception.ExceptionTooBig;
import au.net.hal9000.dnd.item.exception.ExceptionTooHeavy;

public class BagTest {

	
	// Normal item into bag.
	// item's location changes to bag
	@Test
	public void ordinary_add() {
		Cookie cookie = new Cookie();
		cookie.setLocation(new Location("Ground"));
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
		assertEquals("cookie location",bag,cookie.getLocation());
	}
	
	// Sharp items throw ExceptionInvalidType
	// Item's location remains unchanged.
	@Test
	public void sharp_add_rupture() {
		Sword sword = new Sword();
		Location ground = new Location("Ground");
		sword.setLocation(ground);
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
		assertEquals("cookie location",ground,sword.getLocation());
	}


}
