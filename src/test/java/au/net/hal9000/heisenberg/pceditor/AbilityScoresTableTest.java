package au.net.hal9000.heisenberg.pceditor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import au.net.hal9000.heisenberg.item.entity.Race;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import org.junit.Before;
import org.junit.Test;

/** */
public class AbilityScoresTableTest {

  @Before
  public void initialize() {
    DemoEnvironment.setup();
  }

  /**
   * test basic operations.
   *
   * @throws ConfigurationError
   */
  @Test
  public void testBasicOperations() throws ConfigurationError {
    Race pc = DemoEnvironment.getRace();
    AbilityScoresTable basicPanel = new AbilityScoresTable();
    basicPanel.setRace(pc);
    assertNotNull("BasicPanel not null", basicPanel);
  }

  /** Method testGetRowCount. */
  @Test
  public void testGetRowCount() {

    AbilityScoresTable abilityScoresTable = new AbilityScoresTable();
    assertEquals(0, abilityScoresTable.getRowCount());
  }
}
