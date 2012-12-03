package test.au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;
import org.junit.Test;

import au.net.hal9000.heisenberg.item.Crossbow;
import au.net.hal9000.heisenberg.item.CrossbowBolt;
import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.property.Sharp;

public class CrossbowTest {

    @Test
    public void testSetGetLoadedBolt() {
        Crossbow crossbow = new Crossbow();
        CrossbowBolt bolt = new CrossbowBolt();
        Location newLocation = new Location("Ground");
        bolt.setLocation(newLocation);
        crossbow.setLoadedBolt(bolt);
        CrossbowBolt got = crossbow.getLoadedBolt();
        assertEquals("getLoadedBolt - bolt", bolt, got);
        assertEquals("getLoadedBolt - bolt location", newLocation, got.getLocation());
    }

    @Test
    public void testSharp() {
        final Crossbow crossbow = new Crossbow();
        assertFalse("not sharp", crossbow instanceof Sharp);
    }

    @Test
    public void testClone() {
        Crossbow original = new Crossbow();
        Crossbow clone = null;
        try {
            clone = (Crossbow) original.clone();
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

        // While it is typically the case that:
        // x.clone().equals(x)
        // will be true, this is not an absolute requirement.
        // assertTrue("x.clone().equals(x)", clone.equals(original));

        // Class specific tests
        // Make sure the cloning is deep, not shallow.
        // e.g. test the non-mutable, non-primitives

    }

}
