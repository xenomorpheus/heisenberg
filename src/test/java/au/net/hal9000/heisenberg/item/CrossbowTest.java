package au.net.hal9000.heisenberg.item;

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

}
