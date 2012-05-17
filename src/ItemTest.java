import static org.junit.Assert.*;

import org.junit.Test;

public class ItemTest {

	@Test
	public void testItemNullParam() {
		Item i = new Item();
		assertEquals("Item() name", "", i.getName());
		assertEquals("Item() description", "", i.getDescription());
		assertEquals("Item() baseWeight", 0F, i.getBaseWeight(), 0.0001F);
		assertEquals("Item() location", null, i.getLocation());
		assertEquals("Item() alive", false, i.getAlive());
		assertEquals("Item() extraDimensional", false, i.getExtraDimensional());
		assertEquals("Item() magical", false, i.getMagical());
	}

	@Test
	public void testItemWithName() {
		Item i = new Item("The Name");
		assertEquals("Item() name", "The Name", i.getName());
		assertEquals("Item() description", "", i.getDescription());
		assertEquals("Item() baseWeight", 0F, i.getBaseWeight(), 0.0001F);
		assertEquals("Item() location", null, i.getLocation());
		assertEquals("Item() alive", false, i.getAlive());
		assertEquals("Item() extraDimensional", false, i.getExtraDimensional());
		assertEquals("Item() magical", false, i.getMagical());
	}

	@Test
	public void testItemWithNameAndDescription() {
		Item i = new Item("The Name", "The Description");
		assertEquals("Item() name", "The Name", i.getName());
		assertEquals("Item() description", "The Description",
				i.getDescription());
		assertEquals("Item() baseWeight", 0F, i.getBaseWeight(), 0.0001F);
		assertEquals("Item() location", null, i.getLocation());
		assertEquals("Item() alive", false, i.getAlive());
		assertEquals("Item() extraDimensional", false, i.getExtraDimensional());
		assertEquals("Item() magical", false, i.getMagical());
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
		i.setBaseWeight(0.123F);
		assertEquals("baseWeight", 0.123F, i.getBaseWeight(), 0.0001F);
	}
	@Test
	public void testWeight() {
		Item i = new Item();
		i.setBaseWeight(0.123F);
		assertEquals("weight", 0.123F, i.getWeight(), 0.0001F);
	}

	@Test
	public void testValue() {
		Item i = new Item();
		i.setValue(new CoinCollection(1,2,4,8));
	    CoinCollection cc = new CoinCollection(1,2,4,8);
		assertTrue("value",  cc.equals(i.getValue()));
	}

	@Test
	public void testLocation() {
		Item ring = new Item("Ring");
		Item hand = new Item("Hand");
		ring.setLocation(hand);
		assertEquals("location", hand, ring.getLocation());
	}

	@Test
	public void testAlive() {
		Item i = new Item();
		assertEquals("alive", false, i.getAlive());
	}

	@Test
	public void testExtraDimensional() {
		Item i = new Item();
		assertEquals("extraDimensional", false, i.getExtraDimensional());
	}

	@Test
	public void testMagical() {
		Item i = new Item();
		assertEquals("magical", false, i.getMagical());
	}

	// equals
	
}
