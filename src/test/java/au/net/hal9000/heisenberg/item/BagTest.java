package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;

import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import org.junit.Test;

/** */
public class BagTest {

  /** Normal item into bag. */
  @Test
  public void testAddOrdinary() {
    Location world = new Location();
    Cookie cookie = new Cookie();
    // set the location so we can see that it changes
    cookie.setContainer(world);
    Bag bag = new Bag();
    bag.add(cookie);
    // item's location changes to bag
    assertEquals("cookie location", bag, cookie.getContainer());
  }

  /** Sharp items throw ExceptionInvalidType. */
  @Test(expected = InvalidTypeException.class)
  public void testAddSharpRupture() {
    Sword sword = new Sword();
    Location world = new Location();
    sword.setContainer(world);
    Bag bag = new Bag();
    try {
      bag.add(sword);
    } catch (InvalidTypeException e) {
      // Item's location remains unchanged.
      assertEquals("cookie location", world, sword.getContainer());
      throw e;
    }
  }
}
