package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.entity.Cat;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import junit.framework.TestCase;
import org.junit.Test;

/** The class <code>AnimalTest</code> contains tests for the class {@link <code>Animal</code>}. */
public class AnimalTest extends TestCase {

  @Test
  public void testDrink() {
    // Setup
    Cat cat = new Cat();
    cat.getPlayableState().setActionPoints(2);
    Water water = new Water();
    water.setWeightBase(1.0f);
    // Set hydration to half of ideal.
    ItemProperty.setHydration(cat, ItemProperty.HEALTH_METRIC_IDEAL / 2.0f);
    float hydrationBefore = ItemProperty.getHydration(cat);
    // Run
    cat.drink(water);
    // Test
    float hydrationAfter = ItemProperty.getHydration(cat);
    assertTrue("Hydration increases", hydrationAfter > hydrationBefore);
  }
}
