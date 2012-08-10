package au.net.hal9000.player.item;

import static org.junit.Assert.*;

import org.junit.Test;

public class CrossbowTest {

	@Test
	public void testSetGetLoadedBolt() {
		Crossbow crossbow = new Crossbow();
		CrossbowBolt bolt = new CrossbowBolt();
		Cookie cookie = new Cookie("Cookie");
		bolt.setLocation(cookie);
		crossbow.setLoadedBolt(bolt);
		CrossbowBolt got = crossbow.getLoadedBolt();
        assertEquals("getLoadedBolt - bolt",bolt, got);
        assertEquals("getLoadedBolt - bolt location", cookie, got.getLocation());
	}

	@Test
	public void testIsSharp() {
		Crossbow crossbow = new Crossbow();
		assertEquals("not sharp",false, crossbow.isSharp());
	}

	@Test
	public void testClone() {
		Crossbow x = new Crossbow();
		Crossbow clone = null;
		try {
			clone = (Crossbow) x.clone(x);
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
