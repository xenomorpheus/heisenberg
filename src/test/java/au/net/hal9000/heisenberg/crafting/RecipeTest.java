package au.net.hal9000.heisenberg.crafting;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import au.net.hal9000.heisenberg.units.Skill;

/**
 */
public class RecipeTest {
    /**
     * Field PROCESS_NAME.
     * (value is ""process name"")
     */
    private static final String PROCESS_NAME = "process name";
    /**
     * Field REQUIRED_ACTION_POINTS.
     * (value is 42)
     */
    private static final int REQUIRED_ACTION_POINTS = 42;
    /**
     * Field REQUIRED_MANA.
     * (value is 3)
     */
    private static final int REQUIRED_MANA = 3;
    /**
     * Field REQUIRED_SKILLS.
     */
    private static final String[] REQUIRED_SKILLS = new String[] { "Skill0",
            "Skill1", "Skill2" };

    /**
     * Field simple.
     */
    private Recipe simple;
    /**
     * Field requirementsAll.
     */
    private List<Requirement> requirementsAll;
    /**
     * Field recipeAll.
     */
    private Recipe recipeAll;

    /**
     * Method setup.
     */
    @Before
    public void setUp() {
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

    /**
     * Method testRecipeIngredients.
     */
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

    /**
     * Method testGetId.
     */
    @Test
    public void testGetId() {
        assertEquals("id", "recipe1", recipeAll.getId());
    }

    /**
     * Method testGetDescription.
     */
    @Test
    public void testGetDescription() {
        assertEquals("description", "the first recipe",
                recipeAll.getDescription());
    }

    /**
     * Method testGetProcess.
     */
    @Test
    public void testGetProcess() {
        assertEquals("process", PROCESS_NAME, recipeAll.getProcess());
    }

    /**
     * Method testGetMana.
     */
    @Test
    public void testGetMana() {
        assertEquals("mana", 2, recipeAll.getMana());
    }

    /**
     * Method testGetActionPoints.
     */
    @Test
    public void testGetActionPoints() {
        assertEquals("actionPoints", REQUIRED_ACTION_POINTS,
                recipeAll.getActionPoints());
    }

    /**
     * Method testGetRequirementCount.
     */
    @Test
    public void testGetRequirementCount() {
        assertEquals("requirement count", requirementsAll.size(),
                recipeAll.getRequirementCount());
    }

    /**
     * Method testGetRequirements.
     */
    @Test
    public void testGetRequirements() {
    }

    /**
     * Method testGetRequirement.
     */
    @Test
    public void testGetRequirement() {
    }

    /**
     * Method testGetProducts.
     */
    @Test
    public void testGetProducts() {
    }

    /**
     * Method testGetProduct.
     */
    @Test
    public void testGetProduct() {
    }

    /**
     * Method testGetProductCount.
     */
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

    /**
     * Method testGetSkillCount.
     */
    @Test
    public void testGetSkillCount() {
        assertEquals("skill count", REQUIRED_SKILLS.length,
                recipeAll.getSkillCount());
    }

    /**
     * Method testGetSkills.
     */
    @Test
    public void testGetSkills() {
    }

    /**
     * Method testGetNewCooker.
     */
    @Test
    public void testGetNewCooker() {
    }

    /**
     * Method testToString.
     */
    @Test
    public void testToString() {
        assertEquals("recipe1", simple.getId());
    }

    /**
     * Method testDetails.
     */
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

    /**
     * Method testDetailsComplex.
     */
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
