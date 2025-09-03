package au.net.hal9000.heisenberg.pceditor;

import static org.junit.Assert.assertNotNull;

import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import org.junit.Test;

/** */
public class DescriptionPaneTest {

  /**
   * test basic operations.
   *
   * @throws ConfigurationError
   */
  @Test
  public void testBasicOperations() throws ConfigurationError {

    DescriptionPane window = new DescriptionPane(DemoEnvironment.getCharacterSheet());
    assertNotNull("not Null", window);
  }
}
