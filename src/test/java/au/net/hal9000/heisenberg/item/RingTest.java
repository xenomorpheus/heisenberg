package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.units.Currency;
import org.junit.Test;

/** */
public class RingTest {

  /** Method testRingConstructorNoAguments. */
  @Test
  public void testRingConstructorNoAguments() {
    final Ring ring = new Ring();
    assertEquals("description", "small metalic ring", ring.getDescription());
    assertTrue("value", ring.getValueBase().equals(new Currency(0, 5, 0, 0)));
    assertEquals("weightBase", 0.02f, ring.getWeightBase(), 0.001F);
  }

  /** Method testImplementsMagic. */
  @Test
  public void testImplementsMagic() {
    final Ring ring = new Ring();
    assertFalse("Implements Magical", ItemProperty.isMagical(ring));
  }
}
