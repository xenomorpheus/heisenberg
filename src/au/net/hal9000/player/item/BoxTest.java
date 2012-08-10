package au.net.hal9000.player.item;

import static org.junit.Assert.*;

import org.junit.Test;

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
		Box x = new Box();
		Box clone = null;
		try {
			clone = (Box) x.clone(x);
		} catch (CloneNotSupportedException e) {
			fail(e.toString());
		}

		// x.clone() != x
		// will be true, and that the expression:
		assertTrue("x.clone() != x", clone != x);

		// x.clone().getClass() == x.getClass()
		// will be true, but these are not absolute requirements.
		assertTrue("x.clone().getClass() == x.getClass()",
				clone.getClass() == x.getClass());

		// By convention, the returned object should be obtained by calling
		// super.clone. If a class and all of its superclasses (except
		// Object)
		// obey this convention, it will be the case that
		// x.clone().getClass() == x.getClass().
		// Already tested above.

		// While it is typically the case that:
		// x.clone().equals(x)
		// will be true, this is not an absolute requirement.
		assertTrue("x.clone().equals(x)", clone.equals(x));

		// Class specific tests
		// Make sure the cloning is deep, not shallow.
		// e.g. test the non-mutable, non-primitives

	}	
}
