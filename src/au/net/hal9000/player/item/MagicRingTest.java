package au.net.hal9000.player.item;

import static org.junit.Assert.*;
import org.junit.Test;

public class MagicRingTest {

	@Test
	public void testPlus() {
        MagicRing r = new MagicRing();
        r.setPlus(3);
        assertEquals("plus", 3, r.getPlus());
	}

	@Test
	public void testImplementsMagic() {
		MagicRing r = new MagicRing();
		assertTrue("Implements Magical", r.isMagical());
	}
	
}
