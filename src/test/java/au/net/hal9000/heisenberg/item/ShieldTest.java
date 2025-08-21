package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertTrue;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.being.Human;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import org.junit.Test;

/** */
public class ShieldTest {

  /** Method shieldAdd. */
  @Test
  public void testShieldAdd() {
    Shield shield = new Shield();
    Human human = new Human();
    human.add(shield);
  }

  /** clothing test. */
  @Test
  public void testClothing() {
    Item shield = new Shield();
    assertTrue("isClothing", ItemProperty.isClothing(shield));
  }
}
