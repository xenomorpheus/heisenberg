package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertTrue;

import au.net.hal9000.heisenberg.item.property.ItemProperty;
import org.junit.Test;

/** */
public class HumanoidHeadTest {

  /** Method testLiving. */
  @Test
  public void testLiving() {
    HumanoidHead head = new HumanoidHead();
    assertTrue("living", ItemProperty.isLiving(head));
  }
}
