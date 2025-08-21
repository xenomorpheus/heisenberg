package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.being.Human;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import org.junit.Before;
import org.junit.Test;

/** Unit tests for the Factory class, which is responsible for creating various items. */
public class FactoryTest {
  /** Field config. */
  private Configuration config;

  /**
   * Initializes the test environment by setting up the demo environment and retrieving the last
   * configuration.
   */
  @Before
  public void initialize() {
    DemoEnvironment.setup();
    config = Configuration.lastConfig();
  }

  /**
   * Method testBagOfHolding.
   *
   * @param object Object
   */
  private static void testBagOfHolding(Object object) {
    assertTrue("instanceof Item", object instanceof Item);
    assertEquals("simple class", "BagOfHolding", object.getClass().getSimpleName());
  }

  /** Create all the products of the recipe. */
  @Test
  public void createItemsAll() {
    for (String type : config.getItemClassIds()) {
      Object item = Factory.createItem(type);
      assertTrue("instanceof Item", item instanceof Item);
      assertEquals("simple class", type, item.getClass().getSimpleName());
    }
  }

  /** Create item with params passed to createItem. */
  @Test
  public void createItemWithParams() {
    testBagOfHolding((BagOfHolding) Factory.createItem("BagOfHolding"));
    testBagOfHolding((BagOfHolding) Factory.createItem("BagOfHolding", null));
    testBagOfHolding((BagOfHolding) Factory.createItem("BagOfHolding", new Object[] {}));
    testBagOfHolding((BagOfHolding) Factory.createItem("BagOfHolding", new Object[] {2}));
    testBagOfHolding((BagOfHolding) Factory.createItem("BagOfHolding", new Object[] {4, "TEST"}));
  }

  /** Create Item objects and test the instanceof. */
  @Test
  public void createItemTestInstance() {

    // Check the sub-class
    Item biscuit = Factory.createItem("Biscuit");
    assertTrue("instanceof", biscuit instanceof Biscuit);
    Item human = Factory.createItem("Human");
    assertTrue("instanceof", human instanceof Human);
  }
}
