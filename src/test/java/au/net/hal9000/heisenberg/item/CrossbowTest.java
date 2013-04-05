package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;
import org.junit.Test;

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
        assertEquals("getLoadedBolt - bolt location", newLocation,
                got.getLocation());
    }

}
