/** */
package au.net.hal9000.heisenberg.util;

import static org.junit.Assert.assertNotNull;

import au.net.hal9000.heisenberg.TestHelper;
import au.net.hal9000.heisenberg.item.Sword;
import org.junit.Before;
import org.junit.Test;

/** */
public class ItemClassIconTest {

  private ItemClassIcon itemClassIcon = null;

  /** Method setUp. */
  @Before
  public void setUp() {
    TestHelper.setup();
    itemClassIcon = new ItemClassIcon(Configuration.lastConfig());
  }

  @Test
  public void testGetClassIconClosedDefault() {
    assertNotNull(itemClassIcon.getClassIconClosedDefault(new Sword().getSimpleClassName()));
  }

  @Test
  public void testGetClassIconLeafDefault() {
    assertNotNull(itemClassIcon.getClassIconLeafDefault(new Sword().getSimpleClassName()));
  }

  @Test
  public void testGetClassIconOpenDefault() {
    assertNotNull(itemClassIcon.getClassIconOpenDefault(new Sword().getSimpleClassName()));
  }
}
