package au.net.hal9000.player.item;

import static org.junit.Assert.*;

import org.junit.Test;

import au.net.hal9000.player.units.Currency;

public class RingTest {

	@Test
	public void testRingConstructorNoAguments() {
		Ring r = new Ring();
		assertEquals("description", "small metalic ring", r.getDescription());
		assertTrue("value", r.getValueBase().equals(new Currency(0, 5, 0, 0)));
		assertEquals("weightBase", 0.02f, r.getWeightBase(),  0.001F);
	}

	@Test
	public void testImplementsMagic() {
		Ring r = new Ring();
		assertFalse("Implements Magical", r.isMagical());
	}
        @Test
        public void testClone() {
                Ring x = new Ring();
                Ring clone = null;
                try {
                        clone = (Ring) x.clone();
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
