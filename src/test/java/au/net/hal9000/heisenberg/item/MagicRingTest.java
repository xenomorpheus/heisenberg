package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;
import org.junit.Test;

import au.net.hal9000.heisenberg.item.MagicRing;
import au.net.hal9000.heisenberg.item.property.ItemProperty;

public class MagicRingTest {

	@Test
	public void testImplementsMagic() {
		MagicRing ring = new MagicRing();
		assertTrue("Implements Magical", ItemProperty.isMagical(ring));
	}

	@Test
	public void testClone() {
		MagicRing original = new MagicRing();
		MagicRing clone = null;
		try {
			clone = (MagicRing) original.clone();
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

        // Clones have differen IDs
		// While it is typically the case that:
		// x.clone().equals(x)
		// will be true, this is not an absolute requirement.
		// assertTrue("x.clone().equals(x)", clone.equals(original));

		// Class specific tests
		// Make sure the cloning is deep, not shallow.
		// e.g. test the non-mutable, non-primitives

	}
}
