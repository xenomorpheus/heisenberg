package au.net.hal9000.heisenberg.pceditor;

// Use the GridBagConstraints to determine how the component
import static org.junit.Assert.assertNotNull;

import au.net.hal9000.heisenberg.item.entity.Race;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import org.junit.Test;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class BasicPanelTest {

  /**
   * test basic operations.
   *
   * @throws ConfigurationError
   */
  @Test
  public void testBasicOperations() throws ConfigurationError {

    Race pc = DemoEnvironment.getRace();
    BasicPanel basicPanel = new BasicPanel();
    assertNotNull("not Null", basicPanel);
    basicPanel.setRace(pc);
  }
}
