package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import au.net.hal9000.heisenberg.item.property.ItemProperty;
import org.junit.Test;

/** Unit tests for the Water class. */
public class WaterTest {

  /** Method testIsHumanoidFood. */
  @Test
  public void testIsHumanoidFood() {
    Water water = new Water();
    assertTrue("is humanoid food", ItemProperty.isHumanoidFood(water));
  }

  /** Method testSplitByWeight. */
  @Test
  public void testSplitByWeight() {
    final float tolerance = 0.0001f;
    final float big = 7.0f;
    final float small = 3.0f;
    Water waterBig = new Water();
    waterBig.setWeightBase(big + small);
    waterBig.setVolumeBase(2.0f * (big + small));
    Water waterSmall = (Water) waterBig.splitByWeight(small);
    assertEquals("big's weight", big, waterBig.totalWeight(), tolerance);
    assertEquals("big's volume", big * 2.0f, waterBig.totalVolume(), tolerance);
    assertEquals("small's weight", small, waterSmall.totalWeight(), tolerance);
    assertEquals("small's volume", small * 2.0f, waterSmall.totalVolume(), tolerance);
  }
}
