package au.net.hal9000.player.item;

/*
 * Note a lot of unit tests for cookie are in
 * Item as Item is an abstract class
 * and needs an item to do tests.
 * 
 */


import static org.junit.Assert.*;

import org.junit.Test;


public class CookieTest {

	@Test
	public void testClone() {
		Cookie x = new Cookie();
		Cookie clone = null;
		try {
			clone = (Cookie) x.clone();
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
	public void testIsHumanoidFood(){
		Cookie cookie = new Cookie();
		assertTrue("is humanoid food", cookie.isHumanoidFood());
	}

}
