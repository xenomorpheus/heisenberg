package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/** Test Box class. */
public class BoxTest {

  /** Normal item into box. */
  @Test
  public void testAddOrdinary() {
    Location world = new Location();
    Biscuit biscuit = new Biscuit();
    Sword sword = new Sword();
    // set the location so we can see that it changes
    biscuit.setContainer(world);
    sword.setContainer(world);
    Box box = new Box();
    box.add(biscuit);
    box.add(sword);

    // item's location changes to box
    assertEquals("biscuit location", box, biscuit.getContainer());
    assertEquals("sword location", box, sword.getContainer());
  }
}
