package au.net.hal9000.dnd;
import static org.junit.Assert.*;

import org.junit.Test;

public class ItemTest {

	@Test
	public void testItemNullParam() {
		Item i = new Item();
		assertEquals("Item() name", "Item", i.getName());
		assertEquals("Item() description", "", i.getDescription());
		assertEquals("Item() weightBase", 0F, i.getWeightBase(), 0.0001F);
		assertEquals("Item() location", null, i.getLocation());
	}

	@Test
	public void testItemWithName() {
		Item i = new Item("The Name");
		assertEquals("Item() name", "The Name", i.getName());
		assertEquals("Item() description", "", i.getDescription());
		assertEquals("Item() weightBase", 0F, i.getWeightBase(), 0.0001F);
		assertEquals("Item() location", null, i.getLocation());
	}

	@Test
	public void testItemWithNameAndDescription() {
		Item i = new Item("The Name", "The Description");
		assertEquals("Item() name", "The Name", i.getName());
		assertEquals("Item() description", "The Description",
				i.getDescription());
		assertEquals("Item() weightBase", 0F, i.getWeightBase(), 0.0001F);
		assertEquals("Item() location", null, i.getLocation());
	}

	@Test
	public void testName() {
		Item i = new Item();
		i.setName("A Name");
		assertEquals("name", "A Name", i.getName());
	}

	@Test
	public void testDescription() {
		Item i = new Item();
		i.setDescription("A Description");
		assertEquals("description", "A Description", i.getDescription());
	}

	@Test
	public void testBaseWeight() {
		Item i = new Item();
		i.setWeightBase(0.123F);
		assertEquals("weightBase", 0.123F, i.getWeightBase(), 0.0001F);
	}

	@Test
	public void testWeight() {
		Item i = new Item();
		i.setWeightBase(0.123F);
		assertEquals("weight", 0.123F, i.getWeight(), 0.0001F);
	}

	@Test
	public void testCost() {
		Item i = new Item();
		i.setCost(new CoinCollection(1, 2, 4, 8));
		assertTrue("Cost", i.getCost().equals(new CoinCollection(1, 2, 4, 8)));
		assertFalse("Cost", i.getCost().equals(new CoinCollection(2, 2, 4, 8)));
		assertFalse("Cost", i.getCost().equals(new CoinCollection(1, 3, 4, 8)));
		assertFalse("Cost", i.getCost().equals(new CoinCollection(1, 2, 5, 8)));
		assertFalse("Cost", i.getCost().equals(new CoinCollection(1, 2, 4, 9)));
	}

	@Test
	public void testLocation() {
		Item ring = new Item("Ring");
		Item hand = new Item("Hand");
		ring.setLocation(hand);
		assertEquals("location", hand, ring.getLocation());
	}

	@Test
	public void testImplementsInterface(){
		Item i = new Item();
		assertFalse("implementsInterface",i.implementsInterface(Magical.class));
	}

	public void testToString(){
	    Item i = new Item();
	    assertEquals("toString","some text",i.toString());
	
	}
	
	
}
