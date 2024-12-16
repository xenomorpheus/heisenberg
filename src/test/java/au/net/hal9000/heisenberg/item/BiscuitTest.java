package au.net.hal9000.heisenberg.item;

/*
 * Note a lot of unit tests for biscuit are in
 * Item as Item is an abstract class
 * and needs an item to do tests.
 *
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import au.net.hal9000.heisenberg.item.property.ItemProperty;
import org.junit.Test;

/** */
public class BiscuitTest {
  /** Method testEquals. */
  @Test
  public void testEquals() {
    Biscuit first = new Biscuit();
    Biscuit second = new Biscuit();
    assertTrue("equals true for self", first.equals(first));
    assertFalse("equals false for other", first.equals(second));
  }

  /** Method testIsHumanoidFood. */
  @Test
  public void testIsHumanoidFood() {
    Biscuit biscuit = new Biscuit();
    assertTrue("is humanoid food", ItemProperty.isHumanoidFood(biscuit));
  }

  /** Method testGetSimpleClassName. */
  @Test
  public void testGetSimpleClassName() {
    Biscuit biscuit = new Biscuit();
    assertEquals("getSimpleClassName", "Biscuit", biscuit.getSimpleClassName());
  }
}
