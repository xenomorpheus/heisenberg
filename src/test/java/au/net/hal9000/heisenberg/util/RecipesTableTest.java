package au.net.hal9000.heisenberg.util;

import static org.junit.Assert.assertNotEquals;
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
    RecipesTable basicPanel = new RecipesTable(DemoEnvironment.getCharacterSheet());
    assertNotNull("BasicPanel not null", basicPanel);
  }

  /** Test getRowCount. 
   * @throws ConfigurationError */
  @Test
  public void testGetRowCount() throws ConfigurationError {

    RecipesTable recipesTable = new RecipesTable(DemoEnvironment.getCharacterSheet());
    assertNotEquals(0, recipesTable.getRowCount());
  }
}
