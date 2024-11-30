package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import au.net.hal9000.heisenberg.item.property.ItemProperty;
import org.junit.Test;

/** */
public class OrbOfLightTest {

  /** Test Constructor. */
  @Test
  public void testName() {
    OrbOfLight ool = new OrbOfLight();
    assertNotNull(ool);
  }

  /** Method testMagical. */
  @Test
  public void testMagical() {
    OrbOfLight ool = new OrbOfLight();
    assertTrue("Implements Magical", ItemProperty.isMagical(ool));
  }
}
