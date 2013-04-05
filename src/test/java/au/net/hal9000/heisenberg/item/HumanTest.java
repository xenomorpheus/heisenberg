package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;
import org.junit.Test;
import au.net.hal9000.heisenberg.item.Entity;
import au.net.hal9000.heisenberg.item.Human;
import au.net.hal9000.heisenberg.item.Item;

public class HumanTest {

	@Test
	public void testInstanceof() {

		Human human = new Human();
		assertTrue("is Human", human instanceof Human);
		assertTrue("is Humanoid", human instanceof Human);
		assertTrue("is Entity", human instanceof Entity);
		assertTrue("is item", human instanceof Item);
	}

	@Test
	public void testGetRace() {
		Human human = new Human();
		assertEquals("Human", human.getName());
	}
}
