package test.au.net.hal9000.heisenberg.item;

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
	// IItem's location remains unchanged.
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

	@Test
	public void testClone() {
		Bag original = new Bag();
		Bag clone = null;
		try {
			clone = (Bag) original.clone();
		} catch (CloneNotSupportedException e) {
			fail(e.toString());
		}

		// x.clone() != x
		// will be true, and that the expression:
		assertTrue("x.clone() != x", clone != original);

		// x.clone().getClass() == x.getClass()
		// will be true, but these are not absolute requirements.
		assertTrue("x.clone().getClass() == x.getClass()",
				clone.getClass() == original.getClass());

		// By convention, the returned object should be obtained by calling
		// super.clone. If a class and all of its superclasses (except
		// Object)
		// obey this convention, it will be the case that
		// x.clone().getClass() == x.getClass().
		// Already tested above.

		// While it is typically the case that:
		// x.clone().equals(x)
		// will be true, this is not an absolute requirement.
		assertTrue("x.clone().equals(x)", clone.equals(original));

		// Class specific tests
		// Make sure the cloning is deep, not shallow.
		// e.g. test the non-mutable, non-primitives

	}
}
