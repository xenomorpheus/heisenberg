package test.au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;

import java.util.Vector;
import java.util.Iterator;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.*;

public class FactoryTest {

	private static void testBagOfHolding(BagOfHolding item) {
		assertTrue("instanceof Item", item instanceof Item);
		assertEquals("simple class", "BagOfHolding", item.getClass().getSimpleName());
	}

	/**
	 * Create all the products of the recipe.
	 */
	@Test
	public void createItems() {
		Vector<String> classes = new Vector<String>();
		classes.add("Arrow");
		classes.add("Backpack");
		classes.add("Bag");
		classes.add("BagOfHolding");
		classes.add("Box");
		classes.add("Candle");
		classes.add("Cloak");
		classes.add("Cookie");
		classes.add("Crossbow");
		classes.add("Horse");

		// TODO add more
		Iterator<String> itr = classes.iterator();
		while (itr.hasNext()) {
			String type = itr.next();
			Item item = Factory.createItem(type);
			assertTrue("instanceof Item", item instanceof Item);
			assertEquals("simple class", type, item.getClass().getSimpleName());

		}

		testBagOfHolding((BagOfHolding) Factory.createItem("BagOfHolding"));
		testBagOfHolding((BagOfHolding) Factory.createItem("BagOfHolding", null ));
		testBagOfHolding((BagOfHolding) Factory.createItem("BagOfHolding", new Object[]{} ));
		testBagOfHolding((BagOfHolding) Factory.createItem("BagOfHolding", new Object[]{2} ));
		testBagOfHolding((BagOfHolding) Factory.createItem("BagOfHolding", new Object[]{4, "TEST"} ));

		{
			// Check the sub-class
			Item cookie = Factory.createItem("Cookie");
			assertTrue("instanceof", cookie instanceof Cookie);
			Item human = Factory.createItem("Human");
			assertTrue("instanceof", human instanceof Human);
		}

	}
}
