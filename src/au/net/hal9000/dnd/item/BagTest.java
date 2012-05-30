package au.net.hal9000.dnd.item;

import static org.junit.Assert.*;

import org.junit.Test;

public class BagTest {

	
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
