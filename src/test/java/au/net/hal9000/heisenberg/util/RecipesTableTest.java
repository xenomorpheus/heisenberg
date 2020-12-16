package au.net.hal9000.heisenberg.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import au.net.hal9000.heisenberg.item.entity.PcRace;
import au.net.hal9000.heisenberg.pceditor.RecipesTable;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for RecipesTable.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class RecipesTableTest {
  @Before
  public void setupClass() {
    DemoEnvironment.setup();
  }

  /**
   * test basic operations.
   *
   * @throws ConfigurationError
   */
  @Test
  public void testBasicOperations() throws ConfigurationError {
    PcRace pc = DemoEnvironment.getPcRace();
    RecipesTable basicPanel = new RecipesTable();
    basicPanel.setPcRace(pc);
    assertNotNull("BasicPanel not null", basicPanel);
  }

  /** Test getRowCount. */
  @Test
  public void testGetRowCount() {

    RecipesTable recipesTable = new RecipesTable();
    assertEquals(0, recipesTable.getRowCount());
  }
}
