package au.net.hal9000.heisenberg.crafting;

import static org.junit.Assert.*;

import java.util.Vector;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import au.net.hal9000.heisenberg.crafting.Requirement;
import au.net.hal9000.heisenberg.crafting.RequirementItem;
import au.net.hal9000.heisenberg.crafting.Recipe;
import au.net.hal9000.heisenberg.units.Skill;

public class RecipeTest {
    static int REQUIRED_ACTION_POINTS = 42;
    static int REQUIRED_MANA = 3;
    static String[] REQUIRED_SKILLS = new String[] { "Skill0", "Skill1",
            "Skill2" };

    @Test
    public void testRecipeIngredients() {

        Vector<Requirement> requirements = new Vector<Requirement>();

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
    public void testRecipe() {
        Vector<Requirement> requirements = new Vector<Requirement>();

        // RequirementItem
        RequirementItem flintAndTinder = new RequirementItem("FlintAndTinder");
        requirements.add(flintAndTinder);

        RequirementItem wood = new RequirementItem("Wood", "Wood", true, 3);
        requirements.add(wood);

        // Product(s)
        Vector<Product> products = new Vector<Product>();
        products.add(new ProductItem("SmallGroundFire"));

        // Skills
        Set<Skill> skills = new TreeSet<Skill>();
        for (int i = REQUIRED_SKILLS.length - 1; i >= 0; i--) {
            skills.add(new Skill(REQUIRED_SKILLS[i]));
        }

        // Build a recipe with the list of required ingredients
        Recipe recipe = new Recipe("recipe1", "the first recipe", null, 2, 42,
                requirements, skills, products);

        assertEquals("id", "recipe1", recipe.getId());
        assertEquals("description", "the first recipe", recipe.getDescription());
        assertEquals("mana", 2, recipe.getMana());
        assertEquals("actionPoints", 42, recipe.getActionPoints());
        assertEquals("ingredient count", requirements.size(),
                recipe.getRequirementCount());
        assertEquals("skill count", REQUIRED_SKILLS.length,
                recipe.getSkillCount());
        assertEquals("ingredient count", requirements.size(),
                recipe.getRequirementCount());

    }

    @Test
    public void testGetId() {
    }

    @Test
    public void testGetDescription() {
    }

    @Test
    public void testGetProcess() {
    }

    @Test
    public void testSetProcess() {
    }

    @Test
    public void testGetMana() {
    }

    @Test
    public void testGetActionPoints() {
    }

    @Test
    public void testGetRequirementCount() {
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
        Vector<Product> products = new Vector<Product>();
        products.add(new ProductItem("SmallGroundFire"));

        // Build a recipe with the list of required ingredients
        Recipe recipe = new Recipe("recipe1", "the first recipe", null, 2, 42,
                null, null, products);
        
        assertEquals("count", 1, recipe.getProductCount());
    }

    @Test
    public void testGetSkillCount() {
    }

    @Test
    public void testGetSkills() {
    }

    @Test
    public void testGetNewCooker() {
    }

    @Test
    public void testToString() {
    }

    @Test
    public void testDetails() {
    }

}
