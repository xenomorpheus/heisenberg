package au.net.hal9000.dnd.item;

import static org.junit.Assert.*;

import org.junit.Test;

public class CrossbowTest {

	@Test
	public void testSetGetLoadedBolt() {
		Crossbow crossbow = new Crossbow();
		CrossbowBolt bolt = new CrossbowBolt();
		Location ground = new Location("Ground");
		bolt.setLocation(ground);
		crossbow.setLoadedBolt(bolt);
		CrossbowBolt got = crossbow.getLoadedBolt();
        assertEquals("getLoadedBolt - bolt",bolt, got);
        assertEquals("getLoadedBolt - bolt location", ground, got.getLocation());
	}

	@Test
	public void testIsSharp() {
		Crossbow crossbow = new Crossbow();
		assertEquals("not sharp",false, crossbow.isSharp());
	}


}
