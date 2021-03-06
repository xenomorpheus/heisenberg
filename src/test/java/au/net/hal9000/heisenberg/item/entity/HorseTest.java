package au.net.hal9000.heisenberg.item.entity;

import static org.junit.Assert.assertTrue;

import au.net.hal9000.heisenberg.item.api.HumanoidMount;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import org.junit.Test;

/** */
public class HorseTest {

  /** Method testInstanceof. */
  @Test
  public void testInstanceof() {
    Item horse = new Horse();
    assertTrue("is humanoid mount", horse instanceof HumanoidMount);
    assertTrue("is EntityItem", horse instanceof EntityItem);
    assertTrue("is Living", ItemProperty.isLiving(horse));
  }
}
