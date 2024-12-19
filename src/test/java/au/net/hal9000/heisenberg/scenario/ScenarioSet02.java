package au.net.hal9000.heisenberg.scenario;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import au.net.hal9000.heisenberg.item.Sword;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.api.Sharp;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import org.junit.Test;

/** */
public class ScenarioSet02 {

  /** Example of making an existing sword Magical. */
  @Test
  public void makeMagical() {
    Item sword = new Sword();
    assertTrue("is sword", sword instanceof Sword);
    assertTrue("is sharp", sword instanceof Sharp);
    assertFalse("is magical", ItemProperty.isMagical(sword));
    // Add code to make magical
    ItemProperty.setMagical(sword);
    // Test the results.
    assertTrue("is sword", sword instanceof Sword);
    assertTrue("is sharp", sword instanceof Sharp);
    assertTrue("is magical", ItemProperty.isMagical(sword));
  }
}
