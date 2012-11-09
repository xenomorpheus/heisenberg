package test.au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;
import org.junit.Test;

import au.net.hal9000.heisenberg.item.Human;
import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.Shield;

public class BeingTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testAdd() {

		// Human
		Human human = new Human("Human");
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
