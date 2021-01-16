package au.net.hal9000.heisenberg.util;

import static org.junit.Assert.assertNotNull;

import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.entity.Race;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import org.junit.Test;

/** */
public class DummyDataTest {

  /**
   * Method testElf.
   *
   * @throws ConfigurationError
   */
  @Test
  public void testElf() throws ConfigurationError {
    Race pc = DemoEnvironment.getRace();
    assertNotNull(pc);
  }

  /** Method testGetDemoWorld. */
  @Test
  public void testGetDemoWorld() {
    Location loc = DemoEnvironment.getDemoWorld();
    assertNotNull(loc);
  }
}
