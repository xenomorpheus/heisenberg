package test.au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Box;
import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.Sword;

public class BoxTest {

	// Normal item into box.
	// item's location changes to box
	@Test
	public void testAddOrdinary() {
		Location world = new Location("World");
		Cookie cookie = new Cookie();
		Sword sword = new Sword();
		// set the location so we can see that it changes
		cookie.setLocation(world);
		sword.setLocation(world);
		Box box = new Box();
		try {
			box.add(cookie);
			box.add(sword);
		} catch (Exception e) {
			fail(e.toString());
		}
		assertEquals("cookie location", box, cookie.getLocation());
		assertEquals("sword location", box, sword.getLocation());
	}
	
	@Test
	public void testClone() {
		Box original = new Box();
		Box clone = null;
		try {
			clone = (Box) original.clone();
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

        // Clones have different IDs
		// While it is typically the case that:
		// x.clone().equals(x)
		// will be true, this is not an absolute requirement.
		// assertTrue("x.clone().equals(x)", clone.equals(original));

		// Class specific tests
		// Make sure the cloning is deep, not shallow.
		// e.g. test the non-mutable, non-primitives

	}	
}
