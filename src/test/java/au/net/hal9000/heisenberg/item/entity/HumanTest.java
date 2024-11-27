package au.net.hal9000.heisenberg.item.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import au.net.hal9000.heisenberg.item.api.Item;
import org.junit.Test;

/** */
public class HumanTest {

  /** Method testInstanceof. */
  @Test
  public void testInstanceof() {

    Object human = new Human();
    assertTrue("is Human", human instanceof Human);
    assertTrue("is Humanoid", human instanceof Human);
    assertTrue("is EntityItem", human instanceof EntityItem);
    assertTrue("is item", human instanceof Item);
  }

  /** Method testGetName. */
  @Test
  public void testGetName() {
    Human human = new Human();
    assertEquals("Human", human.getName());
  }

  /** Test drink */
  @Test
  public void testDrink() {
    // TODO Human human = new Human();
    // TODO Water water = new Water();
    // human.drink(water);
  }
}
