package test.au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;
import org.junit.Test;

import au.net.hal9000.heisenberg.item.Being;
import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.Human;
import au.net.hal9000.heisenberg.item.Item;

public class HumanTest {

	@Test
	public void testInstanceof() {

		Human human = new Human();
		Item humanItem = (Item) human;
		assertTrue("is Human", human instanceof Human);
		assertTrue("is Humanoid", human instanceof Human);
		assertTrue("is Being", human instanceof Being);
		assertTrue("is item", human instanceof Item);
		assertFalse("is Cookie", humanItem instanceof Cookie);
	}

	@Test
	public void testGetRace() {
		Human human = new Human();
		assertEquals("Human", human.getName());
	}
}
