package au.net.hal9000.heisenberg.pceditor;

import static org.junit.Assert.assertNotNull;

import au.net.hal9000.heisenberg.item.entity.Race;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import org.junit.Test;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class DescriptionPaneTest {

  /**
   * test basic operations.
   *
   * @throws ConfigurationError
   */
  @Test
  public void testBasicOperations() throws ConfigurationError {

    Race pc = DemoEnvironment.getRace();
    DescriptionPane window = new DescriptionPane();
    assertNotNull("not Null", window);
    window.setRace(pc);
  }
}
