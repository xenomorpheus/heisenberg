package au.net.hal9000.player.item;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShieldTest {

	@Test
	public void shieldEquip() {
		Shield shield = new Shield();
		Human human = new Human();
		try {
			human.equip(shield);
		} catch (Exception e) {
			fail("Humanoid could not wear shield");
		}
	}
}
