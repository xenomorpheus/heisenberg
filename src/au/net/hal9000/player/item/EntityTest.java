package au.net.hal9000.player.item;

import static org.junit.Assert.*;
import org.junit.Test;

public class EntityTest {

	@Test
	public void testEquip() {

		// Human
		Human human = new Human("Human");
		ItemImpl shield = new Shield();
		try {
			human.equip(shield);
		} catch (Exception e) {
			fail("equip failed " + e);
		}
	}

}
