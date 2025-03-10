package au.net.hal9000.heisenberg.crafting;

import static org.junit.Assert.assertEquals;

import au.net.hal9000.heisenberg.units.Skill;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import org.junit.Before;
import org.junit.Test;

/** Testing Recipe class. */
public class RecipeTest {
  /** Field PROCESS_NAME. (value is ""process name"") */
  private static final String PROCESS_NAME = "process name";

  /** Field REQUIRED_ACTION_POINTS. (value is 42) */
  private static final int REQUIRED_ACTION_POINTS = 42;

  /** Field REQUIRED_MANA. (value is 3) */
  private static final int REQUIRED_MANA = 3;

  /** Field REQUIRED_SKILLS. */
  private static final String[] REQUIRED_SKILLS = new String[] {"Skill0", "Skill1", "Skill2"};

  /** Field simple. */
  private Recipe simple;

  /** Field requirementsAll. */
  private Map<String, Requirement> requirementsAll;

  /** Field recipeAll. */
  private Recipe recipeAll;

  /** Method setup. */
  @Before
  public void setUp() {
    simple =
        new Recipe(
            "recipe1",
            "the first recipe",
            null,
            REQUIRED_MANA,
            REQUIRED_ACTION_POINTS,
            null,
            null,
            null);
    requirementsAll = new TreeMap<>();

    // RequirementItem
    RequirementItem flintAndTinder = new RequirementItem("FlintAndTinder");
    requirementsAll.put("FlintAndTinder", flintAndTinder);

    RequirementItem wood = new RequirementItem("Wood", "Wood", true, 3);
    requirementsAll.put("Wood", wood);

    // Product(s)
    List<Product> products = new ArrayList<Product>();
    products.add(new ProductItem("SmallGroundFire"));

    // Skills
    Set<Skill> skills = new TreeSet<Skill>();
    for (int i = REQUIRED_SKILLS.length - 1; i >= 0; i--) {
      skills.add(new Skill(REQUIRED_SKILLS[i]));
    }

    // Build a recipe with the list of required ingredients
    recipeAll =
        new Recipe(
            "recipe1",
            "the first recipe",
            PROCESS_NAME,
            REQUIRED_MANA,
            REQUIRED_ACTION_POINTS,
            requirementsAll,
            skills,
            products);
  }

  /** Method testRecipeIngredients. */
  @Test
  public void testRecipeIngredients() {

    Map<String, Requirement> requirements = new TreeMap<>();

    // RequirementItem
    RequirementItem flintAndTinder =
        new RequirementItem("FlintAndTinder", "FlintAndTinder", false, 0);
    requirements.put("FlintAndTinder", flintAndTinder);

    RequirementItem wood = new RequirementItem("Wood", "Wood", true, 3);
    requirements.put("Wood", wood);

    // Build a recipe with the list of required ingredients
    Recipe recipe = new Recipe("recipe1", "the first recipe", null, 0, 0, requirements, null, null);

    assertEquals("ingredient count", requirements.size(), recipe.getRequirementCount());
    assertEquals("ingredient 0", flintAndTinder, recipe.getRequirement("FlintAndTinder"));
    assertEquals("ingredient 1", wood, recipe.getRequirement("Wood"));
  }

  /** Method testGetId. */
  @Test
  public void testGetId() {
    assertEquals("id", "recipe1", recipeAll.getId());
  }

  /** Method testGetDescription. */
  @Test
  public void testGetDescription() {
    assertEquals("description", "the first recipe", recipeAll.getDescription());
  }

  /** Method testGetProcess. */
  @Test
  public void testGetProcess() {
    assertEquals("process", PROCESS_NAME, recipeAll.getProcess());
  }

  /** Method testGetMana. */
  @Test
  public void testGetMana() {
    assertEquals("mana", REQUIRED_MANA, recipeAll.getMana());
  }

  /** Method testGetActionPoints. */
  @Test
  public void testGetActionPoints() {
    assertEquals("actionPoints", REQUIRED_ACTION_POINTS, recipeAll.getActionPoints());
  }

  /** Method testGetRequirementCount. */
  @Test
  public void testGetRequirementCount() {
    assertEquals("requirement count", requirementsAll.size(), recipeAll.getRequirementCount());
  }

  /** Method testGetRequirements. */
  @Test
  public void testGetRequirements() {}

  /** Method testGetRequirement. */
  @Test
  public void testGetRequirement() {}

  /** Method testGetProducts. */
  @Test
  public void testGetProducts() {}

  /** Method testGetProduct. */
  @Test
  public void testGetProduct() {}

  /** Method testGetProductCount. */
  @Test
  public void testGetProductCount() {
    // Product(s)
    List<Product> products = new ArrayList<Product>();
    products.add(new ProductItem("SmallGroundFire"));

    // Build a recipe with the list of required ingredients
    Recipe recipe =
        new Recipe(
            "recipe1",
            "the first recipe",
            null,
            REQUIRED_MANA,
            REQUIRED_ACTION_POINTS,
            null,
            null,
            products);

    assertEquals("count", 1, recipe.getProductCount());
  }

  /** Method testGetSkillCount. */
  @Test
  public void testGetSkillCount() {
    assertEquals("skill count", REQUIRED_SKILLS.length, recipeAll.getSkillCount());
  }

  /** Method testGetSkills. */
  @Test
  public void testGetSkills() {}

  /** Method testGetNewCooker. */
  @Test
  public void testGetNewCooker() {}

  /** Method testToString. */
  @Test
  public void testToString() {
    assertEquals("recipe1", simple.getId());
  }

  /** Method testDetails. */
  @Test
  public void testDetails() {
    StringBuilder text = new StringBuilder("Id: recipe1" + System.lineSeparator());
    text.append("Description: the first recipe" + System.lineSeparator());
    text.append("Process: null" + System.lineSeparator());
    text.append("Mana:" + REQUIRED_MANA + System.lineSeparator());
    text.append("Action Point(s):" + REQUIRED_ACTION_POINTS + System.lineSeparator());
    assertEquals("simple", text.toString(), simple.details());
  }

  /** Method testDetailsComplex. */
  @Test
  public void testDetailsComplex() {
    StringBuilder text = new StringBuilder("Id: recipe1" + System.lineSeparator());
    text.append("Description: the first recipe" + System.lineSeparator());
    text.append("Process: " + PROCESS_NAME + System.lineSeparator());
    text.append("Mana:" + REQUIRED_MANA + System.lineSeparator());
    text.append("Action Point(s):" + REQUIRED_ACTION_POINTS + System.lineSeparator());
    text.append("Skill(s):" + System.lineSeparator());
    text.append("  0: Skill0" + System.lineSeparator());
    text.append("  1: Skill1" + System.lineSeparator());
    text.append("  2: Skill2" + System.lineSeparator());
    text.append("Requirement(s):" + System.lineSeparator());
    text.append(
        "  FlintAndTinder: Id: FlintAndTinder, consumed, item type FlintAndTinder"
            + System.lineSeparator());
    text.append(
        "  Wood: Id: Wood, consumed, item type Wood, weighing at least 3.0"
            + System.lineSeparator());
    text.append("Product(s):" + System.lineSeparator());
    text.append(
        "  SmallGroundFire: Id: SmallGroundFire, item type of SmallGroundFire, weightBase 0.0"
            + System.lineSeparator());
    assertEquals("recipeAll", text.toString(), recipeAll.details());
  }
}
