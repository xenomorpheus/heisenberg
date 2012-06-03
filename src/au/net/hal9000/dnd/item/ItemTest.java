package au.net.hal9000.dnd.item;

import static org.junit.Assert.*;

import org.junit.Test;

public class ItemTest {

	@Test
	public void testItemNullParam() {
		Cookie i = new Cookie();
		assertEquals("Item() name", "Cookie", i.getName());
		assertEquals("Item() description", null, i.getDescription());
		assertEquals("Item() weightBase", 0F, i.getWeightBase(), 0.0001F);
		assertEquals("Item() location", null, i.getLocation());
	}

	@Test
	public void testItemWithName() {
		Cookie i = new Cookie("The Name");
		assertEquals("Item() name", "The Name", i.getName());
		assertEquals("Item() description", null, i.getDescription());
		assertEquals("Item() weightBase", 0F, i.getWeightBase(), 0.0001F);
		assertEquals("Item() location", null, i.getLocation());
	}

	@Test
	public void testItemWithNameAndDescription() {
		Cookie i = new Cookie("The Name", "The Description");
		assertEquals("Item() name", "The Name", i.getName());
		assertEquals("Item() description", "The Description",
				i.getDescription());
		assertEquals("Item() weightBase", 0F, i.getWeightBase(), 0.0001F);
		assertEquals("Item() location", null, i.getLocation());
	}

	@Test
	public void testName() {
		Cookie i = new Cookie("A Name");
		assertEquals("name", "A Name", i.getName());
	}

	@Test
	public void testDescription() {
		Cookie i = new Cookie();
		i.setDescription("A Description");
		assertEquals("description", "A Description", i.getDescription());
	}

	@Test
	public void testBaseWeight() {
		Cookie i = new Cookie();
		i.setWeightBase(0.123F);
		assertEquals("weightBase", 0.123F, i.getWeightBase(), 0.0001F);
	}

	@Test
	public void testWeight() {
		Cookie i = new Cookie();
		i.setWeightBase(0.123F);
		assertEquals("weight", 0.123F, i.getWeight(), 0.0001F);
	}

	@Test
	public void testValueBase() {
		Cookie i = new Cookie();
		i.setValueBase(new Currency(1, 2, 4, 8));
		assertTrue("ValueBase",
				i.getValueBase().equals(new Currency(1, 2, 4, 8)));
		assertFalse("ValueBase",
				i.getValueBase().equals(new Currency(2, 2, 4, 8)));
		assertFalse("ValueBase",
				i.getValueBase().equals(new Currency(1, 3, 4, 8)));
		assertFalse("ValueBase",
				i.getValueBase().equals(new Currency(1, 2, 5, 8)));
		assertFalse("ValueBase",
				i.getValueBase().equals(new Currency(1, 2, 4, 9)));
	}

	@Test
	public void testLocation() {
		Cookie cookie = new Cookie();
		Human human = new Human("human");
		cookie.setLocation(human);
		assertEquals("location", human, cookie.getLocation());
	}

	public void testToString() {
		Cookie cookie = new Cookie();
		assertEquals("toString", "some text", cookie.toString());

	}

}
