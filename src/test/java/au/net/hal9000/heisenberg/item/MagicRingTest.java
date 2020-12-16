package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertTrue;

import au.net.hal9000.heisenberg.item.property.ItemProperty;
import org.junit.Test;

/** */
public class MagicRingTest {

  /** Method testImplementsMagic. */
  @Test
  public void testImplementsMagic() {
    MagicRing ring = new MagicRing();
    assertTrue("Implements Magical", ItemProperty.isMagical(ring));
  }
}
