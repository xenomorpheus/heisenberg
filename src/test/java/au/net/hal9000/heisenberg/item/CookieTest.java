package au.net.hal9000.heisenberg.item;

/*
 * Note a lot of unit tests for cookie are in
 * Item as Item is an abstract class
 * and needs an item to do tests.
 * 
 */

import static org.junit.Assert.*;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.property.ItemProperty;

public class CookieTest {
    @Test
    public void testEquals() {
        Cookie first = new Cookie();
        Cookie second = new Cookie();
        assertTrue("equals true for self", first.equals(first));
        assertFalse("equals false for other", first.equals(second));
    }

    @Test
    public void testClone() {
        Cookie original = new Cookie();
        Cookie clone = null;
        try {
            clone = (Cookie) original.clone();
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

        // Clones have different IDs
        // While it is typically the case that:
        // x.clone().equals(x)
        // will be true, this is not an absolute requirement.
        // assertTrue("x.clone().equals(x)", clone.equals(original));

        // Class specific tests
        // Make sure the cloning is deep, not shallow.
        // e.g. test the non-mutable, non-primitives

    }

    @Test
    public void testIsHumanoidFood() {
        Cookie cookie = new Cookie();
        assertTrue("is humanoid food", ItemProperty.isHumanoidFood(cookie));
    }


}
