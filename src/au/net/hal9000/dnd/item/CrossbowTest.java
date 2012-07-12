package au.net.hal9000.dnd.item;

import static org.junit.Assert.*;

import org.junit.Test;

public class CrossbowTest {

	@Test
	public void testSetGetLoadedBolt() {
		Crossbow crossbow = new Crossbow();
		CrossbowBolt bolt = new CrossbowBolt();
		Cookie cookie = new Cookie("Cookie");
		bolt.setLocation(cookie);
		crossbow.setLoadedBolt(bolt);
		CrossbowBolt got = crossbow.getLoadedBolt();
        assertEquals("getLoadedBolt - bolt",bolt, got);
        assertEquals("getLoadedBolt - bolt location", cookie, got.getLocation());
	}

	@Test
	public void testIsSharp() {
		Crossbow crossbow = new Crossbow();
		assertEquals("not sharp",false, crossbow.isSharp());
	}


}
