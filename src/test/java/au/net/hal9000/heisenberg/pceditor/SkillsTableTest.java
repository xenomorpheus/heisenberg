package au.net.hal9000.heisenberg.pceditor;

// Imports
import static org.junit.Assert.assertNotNull;

import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import org.junit.Test;

/** */
public class SkillsTableTest {

  /**
   * test basic operations.
   *
   * @throws ConfigurationError
   */
  @Test
  public void testBasicOperations() throws ConfigurationError {

    SkillsTable skillsTable = new SkillsTable(DemoEnvironment.getCharacterSheet());
    assertNotNull("not Null", skillsTable);
  }
}
