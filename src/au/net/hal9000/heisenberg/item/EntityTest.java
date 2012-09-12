package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;

import org.junit.Test;

public class EntityTest {

	@Test
	public void testEquip() {

		// Human
		Human human = new Human("Human");
		Item shield = new Shield();
		try {
			human.equip(shield);
		} catch (Exception e) {
			fail("equip failed " + e);
		}
	}
	
	// TODO clone - On an abstract class ?
	// TODO equals
	// TODO persistence
}
