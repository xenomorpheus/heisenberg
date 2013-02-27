package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;
import org.junit.Test;

import au.net.hal9000.heisenberg.item.Ring;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.units.Currency;


public class RingTest {

    @Test
    public void testRingConstructorNoAguments() {
        final Ring ring = new Ring();
        assertEquals("description", "small metalic ring", ring.getDescription());
        assertTrue("value", ring.getValueBase()
                .equals(new Currency(0, 5, 0, 0)));
        assertEquals("weightBase", 0.02f, ring.getWeightBase(), 0.001F);
    }

    @Test
    public void testImplementsMagic() {
        final Ring ring = new Ring();
        assertFalse("Implements Magical", ItemProperty.isMagical(ring));
    }

    @Test
    public void testClone() {
        final Ring original = new Ring();
        Ring clone = null;
        try {
            clone = (Ring) original.clone();
        } catch (CloneNotSupportedException e) {
            fail(e.toString());
        }

        // x.clone() != x
        // will be true, and that the expression:
        assertNotSame("x.clone() != x", clone, original);

        // x.clone().getClass() == x.getClass()
        // will be true, but these are not absolute requirements.
        assertSame("x.clone().getClass() == x.getClass()", clone.getClass(),
                original.getClass());

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
        // assertEquals("x.clone().equals(x)", clone, original);

        // Class specific tests
        // Make sure the cloning is deep, not shallow.
        // e.g. test the non-mutable, non-primitives

    }
}
