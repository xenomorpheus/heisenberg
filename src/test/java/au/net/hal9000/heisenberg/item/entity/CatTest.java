package au.net.hal9000.heisenberg.item.entity;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.Water;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.units.Position;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;

/**
 */
public class CatTest {

	@Before
	public void initialize() {
		DemoEnvironment.setup();
	}

	/**
	 * Method CatMove.
	 */
	@Test
	public void testCatMove(){
		Location dungeon = new Location("Dungeon");
		Cat cat = new Cat("Fluffy", "Black cat");
		cat.move(dungeon, new Position());
		Position pos = new Position();
		assertTrue(pos.equals(cat.getPosition()));
		cat.moveWithinContainer(new Position(10, 10));
		pos = new Position(10, 10);
		assertTrue(pos.equals(cat.getPosition()));
	}

	@Test
	public void testConsumeWater(){
		// Setup
		Cat cat = new Cat();
		cat.setActionPoints(2);
		Water water = new Water();
		water.setWeightBase(1.0f);
		// Set hydration to half of ideal.
		ItemProperty.setHydration(cat, ItemProperty.HEALTH_METRIC_IDEAL / 2.0f);
		float hydrationBefore = ItemProperty.getHydration(cat);
		// Run
		cat.consume(water);
		// Test
		float hydrationAfter = ItemProperty.getHydration(cat);
		assertTrue("Hydration increases", hydrationAfter > hydrationBefore);
	}

	@Test
	public void testConsumeRat(){
		// Setup
		Cat cat = new Cat();
		cat.setActionPoints(2);
		Rat rat = new Rat();
		rat.setWeightBase(1.0f);
		// Set hydration to half of ideal.
		ItemProperty.setNourishment(cat, ItemProperty.HEALTH_METRIC_IDEAL / 2.0f);
		float nourishmentBefore = ItemProperty.getNourishment(cat);
		// Run
		cat.consume(rat);
		// Test
		float nourishmentAfter = ItemProperty.getNourishment(cat);
		assertTrue("Nourishment increases", nourishmentAfter > nourishmentBefore);
	}

}
