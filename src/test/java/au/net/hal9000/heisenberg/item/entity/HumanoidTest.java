package au.net.hal9000.heisenberg.item.entity;

import static org.junit.Assert.assertEquals;

import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.Shield;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import org.junit.Test;

/** */
public class HumanoidTest {
  /** Field WITHIN_MARGIN. (value is 9.0E-5) */
  private static final float WITHIN_MARGIN = 0.00009F;

  /*
   * Most tests will use a Human as a representative of Humanoid.
   */
  /** Method testGetWeight. */
  @Test
  public void testGetWeight() {
    Humanoid human = new Human(); // Close enough for this abstract class
    human.getHead().setWeightBase(1F);
    human.getLeftHand().setWeightBase(2F);
    human.getRightHand().setWeightBase(4F);
    assertEquals("getWeight", human.getWeight(), 7F, WITHIN_MARGIN);
  }

  /** getSpeciesName should return Human. */
  @Test
  public void testGetSpeciesName() {
    Human human = new Human();
    assertEquals("getSpeciesName", "Human", human.getSpeciesName());
  }

  /** Method testWear. */
  @Test
  public void testWear() {
    Humanoid human = new Human(); // Close enough
    Item shield = new Shield();
    human.wear(shield);
  }

  /** Test trying to wear non-clothing. */
  @Test(expected = InvalidTypeException.class)
  public void testAddItem() {
    Humanoid human = new Human();
    Cookie cookie = new Cookie();
    human.wear(cookie);
  }
}
