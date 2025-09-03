package au.net.hal9000.heisenberg.pceditor;

// Use the GridBagConstraints to determine how the component
import static org.junit.Assert.assertNotNull;

import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import org.junit.Test;

/** */
public class BasicPanelTest {

  /**
   * test basic operations.
   *
   * @throws ConfigurationError
   */
  @Test
  public void testBasicOperations() throws ConfigurationError {

    BasicPanel basicPanel = new BasicPanel(DemoEnvironment.getCharacterSheet());
    assertNotNull("not Null", basicPanel);
  }
}
