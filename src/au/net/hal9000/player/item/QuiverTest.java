package au.net.hal9000.player.item;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuiverTest {

	@Test
	public void quiverAddArrow() {
		Quiver quiver = new Quiver();
		Arrow arrow = new Arrow();
		try {
			quiver.add(arrow);
			quiver.add(new Arrow());
			quiver.add(new Arrow());
			quiver.add(new Arrow());
			quiver.add(new Arrow());
			quiver.add(new Arrow());
		} catch (Exception e) {
			fail(e.toString());
		}
        assertEquals("Arrow location ", quiver, arrow.getLocation());
	}

}
