package au.net.hal9000.player.item;

import static org.junit.Assert.*;

import org.junit.Test;

public class CloakTest {

	@Test
	public void testCloneItem() {
		Cloak x = new Cloak();
		Cloak clone = null;
		try {
			clone = (Cloak) x.clone(new Cloak());
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

	@Test
	public void testIsHumanoidClothing() {
		Cloak cloak = new Cloak();
		assertTrue("is humanoid clothing", cloak.isClothing());
	}

	@Test
	public void testIsWearable() {
		Human human = new Human();
		Cloak cloak = new Cloak();
		try {
			human.equip(cloak);
		} catch (Exception e) {
			fail(e.toString());
		}
	}

}
