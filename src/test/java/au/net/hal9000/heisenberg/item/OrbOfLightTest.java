package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import au.net.hal9000.heisenberg.item.property.ItemProperty;
import org.junit.Test;

/** */
public class OrbOfLightTest {

  // Name and Description
  /** Method testName. */
  @Test
  public void testName() {
    final String defaultName = "Orb Of Light";
    final String defaultDescription = "orb of light";
    OrbOfLight ool = new OrbOfLight();
    assertEquals(defaultName, ool.getName());
    assertEquals(defaultDescription, ool.getDescription());
    // custom
    final String expectedName = "my ool";
    final String expectedDescription = "the description";
    OrbOfLight custom = new OrbOfLight(expectedName, expectedDescription);
    assertEquals(expectedName, custom.getName());
    assertEquals(expectedDescription, custom.getDescription());
  }

  /** Method testMagical. */
  @Test
  public void testMagical() {
    OrbOfLight ool = new OrbOfLight();
    assertTrue("Implements Magical", ItemProperty.isMagical(ool));
  }
}
