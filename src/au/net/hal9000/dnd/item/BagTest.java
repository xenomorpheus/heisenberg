package au.net.hal9000.dnd.item;

import static org.junit.Assert.*;
import org.junit.Test;
import au.net.hal9000.dnd.item.exception.*;

public class BagTest {

	
	// Normal item into bag.
	// item's location changes to bag
	@Test
	public void ordinary_add() {
		Cookie cookie = new Cookie();
		// set the location so we can see that it changes
		cookie.setLocation(new Cookie("Cookie2"));
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
		// set the location so we can see that it changes
		Cookie cookie = new Cookie("Cookie");
		sword.setLocation(cookie);
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
		assertEquals("cookie location",cookie,sword.getLocation());
	}


}
