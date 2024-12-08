package au.net.hal9000.heisenberg.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import au.net.hal9000.heisenberg.pceditor.RecipesTable;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import org.junit.Before;
import org.junit.Test;

/** Unit tests for RecipesTable. */
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
    CharacterSheet pc = DemoEnvironment.getCharacterSheet();
    RecipesTable basicPanel = new RecipesTable();
    basicPanel.setCharacterSheet(pc);
    assertNotNull("BasicPanel not null", basicPanel);
  }

  /** Test getRowCount. */
  @Test
  public void testGetRowCount() {

    RecipesTable recipesTable = new RecipesTable();
    assertEquals(0, recipesTable.getRowCount());
  }
}
