package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import au.net.hal9000.heisenberg.item.property.ItemProperty;
import org.junit.Test;

/** Unit tests for the {@link Wood} class. */
public class WoodTest {

  /** Method testIsHumanoidFood. */
  @Test
  public void testIsHumanoidFood() {
    Wood wood = new Wood();
    assertFalse("is humanoid food", ItemProperty.isHumanoidFood(wood));
  }

  /** Method testSplitByWeight. */
  @Test
  public void testSplitByWeight() {
    final float tolerance = 0.0001f;
    final float big = 7.0f;
    final float small = 3.0f;
    Wood woodBig = new Wood();
    woodBig.setWeightBase(big + small);
    woodBig.setVolumeBase(2.0f * (big + small));
    Wood woodSmall = (Wood) woodBig.splitByWeight(small);
    assertEquals("big's weight", big, woodBig.totalWeight(), tolerance);
    assertEquals("big's volume", big * 2.0f, woodBig.totalVolume(), tolerance);
    assertEquals("small's weight", small, woodSmall.totalWeight(), tolerance);
    assertEquals("small's volume", small * 2.0f, woodSmall.totalVolume(), tolerance);
  }
}
