package au.net.hal9000.heisenberg.item;

import org.junit.Before;
import org.junit.Test;

import au.net.hal9000.heisenberg.item.entity.Cat;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import junit.framework.TestCase;

/**
 * The class <code>AnimalTest</code> contains tests for the class {@link
 * <code>Animal</code>}.
 * 
 * @author bruins
 * 
 * @version $Revision$
 */
public class AnimalTest extends TestCase {

	@Before
	public void initialize() {
		DemoEnvironment.setup();
	}

	@Test
	public void testConsume(){
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

}
