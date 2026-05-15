package au.net.hal9000.heisenberg.pceditor;

// Imports
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.ConfigurationError;

/** */
public class SkillsTableTest {

  /**
   * test basic operations.
   *
   * @throws ConfigurationError
   */
  @Test
  public void testBasicOperations() throws ConfigurationError {
    var config = Configuration.lastConfig();
    var skillDetails = config.getSkillDetails();

    SkillsTable skillsTable = new SkillsTable(skillDetails.keySet(), skillDetails);
    assertNotNull("not Null", skillsTable);
  }
}
