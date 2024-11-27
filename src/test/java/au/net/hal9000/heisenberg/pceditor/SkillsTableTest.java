package au.net.hal9000.heisenberg.pceditor;

// Imports
import static org.junit.Assert.assertNotNull;

import au.net.hal9000.heisenberg.util.CharacterSheet;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import org.junit.Test;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class SkillsTableTest {

  /**
   * test basic operations.
   *
   * @throws ConfigurationError
   */
  @Test
  public void testBasicOperations() throws ConfigurationError {

    CharacterSheet pc = DemoEnvironment.getCharacterSheet();

    SkillsTable skillsTable = new SkillsTable();
    assertNotNull("not Null", skillsTable);
    skillsTable.setCharacterSheet(pc);
  }
}
