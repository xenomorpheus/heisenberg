package au.net.hal9000.player.item;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuiverTest {

	@Test
	public void quiverAddArrow() {
		Quiver quiver = new Quiver();
		Arrow arrow = new Arrow();
		try {
			quiver.add(arrow);
			quiver.add(new Arrow());
			quiver.add(new Arrow());
			quiver.add(new Arrow());
			quiver.add(new Arrow());
			quiver.add(new Arrow());
		} catch (Exception e) {
			fail(e.toString());
		}
        assertEquals("Arrow location ", quiver, arrow.getLocation());
	}
	@Test
	public void testClone() {
		Quiver original = new Quiver();
		Quiver clone = null;
		try {
			clone = (Quiver) original.clone();
		} catch (CloneNotSupportedException e) {
			fail(e.toString());
		}

		// x.clone() != x
		// will be true, and that the expression:
		assertTrue("original.clone() != original", clone != original);

		// original.clone().getClass() == original.getClass()
		// will be true, but these are not absolute requirements.
		assertTrue("original.clone().getClass() == original.getClass()",
				clone.getClass() == original.getClass());

		// By convention, the returned object should be obtained by calling
		// super.clone. If a class and all of its superclasses (except
		// Object)
		// obey this convention, it will be the case that
		// original.clone().getClass() == original.getClass().
		// Already tested above.

		// While it is typically the case that:
		// original.clone().equals(original)
		// will be true, this is not an absolute requirement.
		assertTrue("original.clone().equals(original)", clone.equals(original));

		// Class specific tests
		// Make sure the cloning is deep, not shallow.
		// e.g. test the non-mutable, non-primitives

	}
}
