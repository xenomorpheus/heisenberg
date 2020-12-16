package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/** */
public class CrossbowTest {

  /** Method testSetGetLoadedBolt. */
  @Test
  public void testSetGetLoadedBolt() {
    Crossbow crossbow = new Crossbow();
    CrossbowBolt bolt = new CrossbowBolt();
    Location newLocation = new Location("Ground");
    bolt.setContainer(newLocation);
    crossbow.setLoadedBolt(bolt);
    CrossbowBolt got = crossbow.getLoadedBolt();
    assertEquals("getLoadedBolt - bolt", bolt, got);
    assertEquals("getLoadedBolt - bolt location", newLocation, got.getContainer());
  }
}
