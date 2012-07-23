package au.net.hal9000.player.item;

import static org.junit.Assert.*;

import org.junit.Test;

public class ScabbardTest {

	@Test
	public void testClone() {
		Scabbard x = new Scabbard();
		Scabbard clone = null;
		try {
			clone = (Scabbard) x.clone();
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
	public void testIsHumanoidClothing(){
		Scabbard scabbard = new Scabbard();
		assertTrue("is humanoid clothing", scabbard.isClothing());
	}

	@Test
	public void testIsWearable(){
		Human human = new Human();
		Scabbard scabbard = new Scabbard();
		try {
		human.wear(scabbard);
		}
		catch (Exception e){
			fail (e.toString());
		}
	}

}