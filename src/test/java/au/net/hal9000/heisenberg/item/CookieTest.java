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
    public void testIsHumanoidFood() {
        Cookie cookie = new Cookie();
        assertTrue("is humanoid food", ItemProperty.isHumanoidFood(cookie));
    }

}
