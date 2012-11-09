package test.au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Being;
import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.Halfling;
import au.net.hal9000.heisenberg.item.Item;

public class HalflingTest {

	@Test
	public void testInstanceof() {

		Halfling halfling = new Halfling();
		Item halflingItem = (Item) halfling;
		assertTrue("is Halfling", halfling instanceof Halfling);
		assertTrue("is Halflingoid", halfling instanceof Halfling);
		assertTrue("is Being", halfling instanceof Being);
		assertTrue("is item", halfling instanceof Item);
		assertFalse("is Cookie", halflingItem instanceof Cookie);
	}

	@Test
	public void testGetRace() {
		Halfling halfling = new Halfling();
		assertEquals("Halfling", halfling.getName());
	}
}
