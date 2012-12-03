package test.au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.Entity;
import au.net.hal9000.heisenberg.item.Human;
import au.net.hal9000.heisenberg.item.Humanoid;
import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.Shield;
import au.net.hal9000.heisenberg.item.property.HumanoidMount;
import au.net.hal9000.heisenberg.item.property.ItemProperty;

public class EntityTest {

	@Test
	public void testInstanceof() {
		Human human = new Human();
		Item item = (Item) human;
		assertTrue("is Humanoid", item instanceof Humanoid);
		assertTrue("is Entity", item instanceof Entity);
		assertTrue("is Living", ItemProperty.isLiving(item));
		assertFalse("is Cookie", item instanceof Cookie);
		assertFalse("is humanoid mount", item instanceof HumanoidMount);
	}

	@Test
	public void testAdd() {

		// Human
		Human human = new Human("Human"); // Close enough
		Item shield = new Shield();
		try {
			human.add(shield);
		} catch (Exception e) {
			fail("equip failed " + e);
		}
	}

	// TODO clone - On an abstract class ?
	// TODO equals
	// TODO persistence
}
