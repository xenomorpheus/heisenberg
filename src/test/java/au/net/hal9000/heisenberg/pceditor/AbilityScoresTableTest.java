package au.net.hal9000.heisenberg.pceditor;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import org.junit.Before;
import org.junit.Test;

/** Unit tests for the AbilityScoresTable class. */
public class AbilityScoresTableTest {

  /** Sets up the demo environment before each test. */
  @Before
  public void initialize() {
    DemoEnvironment.setup();
  }

  /**
   * test basic operations.
   *
   * @throws ConfigurationError if there is an error in the configuration setup.
   */
  @Test
  public void testBasicOperations() throws ConfigurationError {
    AbilityScoresTable basicPanel = new AbilityScoresTable(DemoEnvironment.getCharacterSheet());
    assertNotNull("BasicPanel not null", basicPanel);
  }

  /** Method testGetRowCount. 
   * @throws ConfigurationError */
  @Test
  public void testGetRowCount() throws ConfigurationError {

    AbilityScoresTable abilityScoresTable = new AbilityScoresTable(DemoEnvironment.getCharacterSheet());
    assertNotEquals(0, abilityScoresTable.getRowCount());
  }
}
