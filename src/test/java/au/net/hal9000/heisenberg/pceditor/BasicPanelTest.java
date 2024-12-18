package au.net.hal9000.heisenberg.pceditor;

// Use the GridBagConstraints to determine how the component
import static org.junit.Assert.assertNotNull;

import au.net.hal9000.heisenberg.util.CharacterSheet;
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

    CharacterSheet pc = DemoEnvironment.getCharacterSheet();
    BasicPanel basicPanel = new BasicPanel();
    assertNotNull("not Null", basicPanel);
    basicPanel.setCharacterSheet(pc);
  }
}
