package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Box;
import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.Sword;

public class BoxTest {

    // Normal item into box.
    // item's location changes to box
    @Test
    public void testAddOrdinary() {
        Location world = new Location("World");
        Cookie cookie = new Cookie();
        Sword sword = new Sword();
        // set the location so we can see that it changes
        cookie.setLocation(world);
        sword.setLocation(world);
        Box box = new Box();
        try {
            box.add(cookie);
            box.add(sword);
        } catch (Exception e) {
            fail(e.toString());
        }
        assertEquals("cookie location", box, cookie.getLocation());
        assertEquals("sword location", box, sword.getLocation());
    }

}
