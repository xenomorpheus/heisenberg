package au.net.hal9000.heisenberg.util;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import au.net.hal9000.heisenberg.crafting.Recipe;
import au.net.hal9000.heisenberg.pceditor.RecipesTable;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

/** Unit tests for RecipesTable. */
public class RecipesTableTest {

  private Map<String, Recipe> recipeDetails;

  @Before
  public void initialize() {
    DemoEnvironment.setup();
    recipeDetails = Configuration.lastConfig().getRecipeDetails();
  }

  /** test basic operations. */
  @Test
  public void testBasicOperations() {
    RecipesTable basicPanel = new RecipesTable(recipeDetails.keySet(), recipeDetails);
    assertNotNull("BasicPanel not null", basicPanel);
  }

  /** Test getRowCount. */
  @Test
  public void testGetRowCount() {

    RecipesTable recipesTable = new RecipesTable(recipeDetails.keySet(), recipeDetails);
    assertNotEquals(0, recipesTable.getRowCount());
  }
}
