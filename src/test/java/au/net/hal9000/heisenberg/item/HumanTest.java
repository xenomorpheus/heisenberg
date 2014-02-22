package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class HumanTest {

	@Test
	public void testInstanceof() {

		Object human = new Human();
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
