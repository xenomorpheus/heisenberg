package au.net.hal9000.heisenberg.item.entity;

import static org.junit.Assert.assertTrue;

import au.net.hal9000.heisenberg.item.Biscuit;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import org.junit.Before;
import org.junit.Test;

/** */
public class HalflingTest {

  @Before
  public void initialize() {
    DemoEnvironment.setup();
  }

  /** Method testInstanceof. */
  @Test
  public void testInstanceof() {
    Object halfling = new Halfling();
    assertTrue("is Humanoid", halfling instanceof Humanoid);
    assertTrue("is EntityItem", halfling instanceof EntityItem);
    assertTrue("is item", halfling instanceof Item);
  }

  @Test
  public void halflingEatsABiscuit() throws InvalidTypeException {
    // Setup
    Halfling halfling = new Halfling();
    halfling.getPlayableState().setActionPoints(3);
    ItemProperty.setNourishment(halfling, ItemProperty.HEALTH_METRIC_IDEAL / 2);
    float nourishment_before = ItemProperty.getNourishment(halfling);
    Biscuit biscuit = new Biscuit();
    // Run
    halfling.eat(biscuit);
    // Test
    float nourishment_after = ItemProperty.getNourishment(halfling);
    assertTrue("Eating increases nourishment", nourishment_after > nourishment_before);
  }
}
