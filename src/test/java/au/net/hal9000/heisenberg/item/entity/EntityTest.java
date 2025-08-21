package au.net.hal9000.heisenberg.item.entity;

import static org.junit.Assert.assertTrue;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.being.Cat;
import au.net.hal9000.heisenberg.item.being.Being;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import org.junit.Before;
import org.junit.Test;

/** */
public class EntityTest {

  @Before
  public void initialize() {
    DemoEnvironment.setup();
  }

  /** Method testInstanceof. */
  @Test
  public void testInstanceof() {
    Item cat = new Cat();
    assertTrue("is Being", cat instanceof Being);
    assertTrue("is Living", ItemProperty.isLiving(cat));
  }
}
