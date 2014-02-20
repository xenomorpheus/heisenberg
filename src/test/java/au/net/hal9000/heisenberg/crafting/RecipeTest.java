package au.net.hal9000.heisenberg.crafting;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Vector;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import au.net.hal9000.heisenberg.units.Skill;

public class RecipeTest {
    private static final String PROCESS_NAME = "process name";
    private static final int REQUIRED_ACTION_POINTS = 42;
    private static final int REQUIRED_MANA = 3;
    private static final String[] REQUIRED_SKILLS = new String[] { "Skill0",
            "Skill1", "Skill2" };

    private Recipe simple;
    private List<Requirement> requirementsAll;
    private Recipe recipeAll;

    @Before
    public void setup() {
        simple = new Recipe("recipe1", "the first recipe", null, 2,
                REQUIRED_ACTION_POINTS, null, null, null);
        requirementsAll = new Vector<Requirement>();

        // RequirementItem
        RequirementItem flintAndTinder = new RequirementItem("FlintAndTinder");
        requirementsAll.add(flintAndTinder);

        RequirementItem wood = new RequirementItem("Wood", "Wood", true, 3);
        requirementsAll.add(wood);

        // Product(s)
        List<Product> products = new Vector<Product>();
        products.add(new ProductItem("SmallGroundFire"));

        // Skills
        Set<Skill> skills = new TreeSet<Skill>();
        for (int i = REQUIRED_SKILLS.length - 1; i >= 0; i--) {
            skills.add(new Skill(REQUIRED_SKILLS[i]));
        }

        // Build a recipe with the list of required ingredients
        recipeAll = new Recipe("recipe1", "the first recipe", PROCESS_NAME, 2,
                REQUIRED_ACTION_POINTS, requirementsAll, skills, products);

    }

    @Test
    public void testRecipeIngredients() {

        List<Requirement> requirements = new Vector<Requirement>();

        // RequirementItem
        RequirementItem flintAndTinder = new RequirementItem("FlintAndTinder",
                "FlintAndTinder", false, 0);
        requirements.add(flintAndTinder);

        RequirementItem wood = new RequirementItem("Wood", "Wood", true, 3);
        requirements.add(wood);

        // Build a recipe with the list of required ingredients
        Recipe recipe = new Recipe("recipe1", "the first recipe", null, 0, 0,
                requirements, null, null);

        assertEquals("ingredient count", requirements.size(),
                recipe.getRequirementCount());
        assertEquals("ingredient 0", flintAndTinder, recipe.getRequirement(0));
        assertEquals("ingredient 1", wood, recipe.getRequirement(1));
    }

    @Test
    public void testGetId() {
        assertEquals("id", "recipe1", recipeAll.getId());
    }

    @Test
    public void testGetDescription() {
        assertEquals("description", "the first recipe",
                recipeAll.getDescription());
    }

    @Test
    public void testGetProcess() {
        assertEquals("process", PROCESS_NAME, recipeAll.getProcess());
    }

    @Test
    public void testGetMana() {
        assertEquals("mana", 2, recipeAll.getMana());
    }

    @Test
    public void testGetActionPoints() {
        assertEquals("actionPoints", REQUIRED_ACTION_POINTS,
                recipeAll.getActionPoints());
    }

    @Test
    public void testGetRequirementCount() {
        assertEquals("requirement count", requirementsAll.size(),
                recipeAll.getRequirementCount());
    }

    @Test
    public void testGetRequirements() {
    }

    @Test
    public void testGetRequirement() {
    }

    @Test
    public void testGetProducts() {
    }

    @Test
    public void testGetProduct() {
    }

    @Test
    public void testGetProductCount() {
        // Product(s)
        List<Product> products = new Vector<Product>();
        products.add(new ProductItem("SmallGroundFire"));

        // Build a recipe with the list of required ingredients
        Recipe recipe = new Recipe("recipe1", "the first recipe", null, 2,
                REQUIRED_ACTION_POINTS, null, null, products);

        assertEquals("count", 1, recipe.getProductCount());
    }

    @Test
    public void testGetSkillCount() {
        assertEquals("skill count", REQUIRED_SKILLS.length,
                recipeAll.getSkillCount());
    }

    @Test
    public void testGetSkills() {
    }

    @Test
    public void testGetNewCooker() {
    }

    @Test
    public void testToString() {
        assertEquals("recipe1", simple.getId());
    }

    @Test
    public void testDetails() {
        StringBuilder text = new StringBuilder("Id: recipe1");
        text.append(System.lineSeparator());
        text.append("Description: the first recipe");
        text.append(System.lineSeparator());
        text.append("Process: null");
        text.append(System.lineSeparator());
        text.append("Mana:2");
        text.append(System.lineSeparator());
        text.append("Action Point(s):");
        text.append(REQUIRED_ACTION_POINTS);
        text.append(System.lineSeparator());
        assertEquals("simple", text.toString(), simple.details());
    }

    @Test
    public void testDetailsComplex() {
        StringBuilder text = new StringBuilder("Id: recipe1");
        text.append(System.lineSeparator());
        text.append("Description: the first recipe");
        text.append(System.lineSeparator());
        text.append("Process: ");
        text.append(PROCESS_NAME);
        text.append(System.lineSeparator());
        text.append("Mana:2");
        text.append(System.lineSeparator());
        text.append("Action Point(s):");
        text.append(REQUIRED_ACTION_POINTS);
        text.append(System.lineSeparator());
        text.append("Skill(s):");
        text.append(System.lineSeparator());
        text.append("  0: Skill0");
        text.append(System.lineSeparator());
        text.append("  1: Skill1");
        text.append(System.lineSeparator());
        text.append("  2: Skill2");
        text.append(System.lineSeparator());
        text.append("Requirement(s):");
        text.append(System.lineSeparator());
        text.append("  FlintAndTinder: Id: FlintAndTinder, consumed, item type FlintAndTinder");
        text.append(System.lineSeparator());
        text.append("  Wood: Id: Wood, consumed, item type Wood, weighing at least 3.0");
        text.append(System.lineSeparator());
        text.append("Product(s):");
        text.append(System.lineSeparator());
        text.append("  SmallGroundFire: Id: SmallGroundFire, item type of SmallGroundFire, weightBase 0.0");
        text.append(System.lineSeparator());
        assertEquals("recipeAll", text.toString(), recipeAll.details());
    }
}
