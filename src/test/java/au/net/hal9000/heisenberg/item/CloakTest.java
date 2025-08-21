package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertTrue;

import au.net.hal9000.heisenberg.item.being.Human;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import org.junit.Test;

/** */
public class CloakTest {

  /** Method testIsHumanoidClothing. */
  @Test
  public void testIsHumanoidClothing() {
    Cloak cloak = new Cloak();
    assertTrue("is Clothing", ItemProperty.isClothing(cloak));
  }

  /** Method testIsWearable. */
  @Test
  public void testIsWearable() {
    Human human = new Human();
    Cloak cloak = new Cloak();
    human.add(cloak);
  }
}
