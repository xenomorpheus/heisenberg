package au.net.hal9000.heisenberg.crafting;

import static org.junit.Assert.*;

import java.util.TreeMap;
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

    @SuppressWarnings("deprecation")
    @Test
    public void testRecipeIngredients() {

        TreeMap<String,Requirement> requirements = new TreeMap<String,Requirement>();

        // RequirementItem
        RequirementItem flintAndTinder = new RequirementItem("FlintAndTinder");
        flintAndTinder.setConsumed(false);
        requirements.put(flintAndTinder.getId(), flintAndTinder);

        RequirementItem wood = new RequirementItem("Wood");
        wood.setWeightMin(3);
        requirements.put(wood.getId(), wood);

        // Build a recipe with the list of required ingredients
        Recipe recipe = new Recipe("recipe1", "the first recipe", null, 0, 0,
                requirements, null, null, null);

        assertEquals("ingredient count", requirements.size(),
                recipe.getRequirementCount());
        assertEquals("ingredient 0", flintAndTinder, recipe.getRequirement(flintAndTinder.getId()));
        assertEquals("ingredient 1", wood, recipe.getRequirement(wood.getId()));
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testRecipe() {
        TreeMap<String,Requirement> requirements = new TreeMap<String,Requirement>();

        // RequirementItem
        RequirementItem flintAndTinder = new RequirementItem("FlintAndTinder");
        flintAndTinder.setConsumed(false);
        requirements.put(flintAndTinder.getId(), flintAndTinder);

        RequirementItem wood = new RequirementItem("Wood");
        wood.setWeightMin(3);
        requirements.put(wood.getId(),wood);

        // Product(s)
        Vector<String> products = new Vector<String>();
        products.add("SmallGroundFire");

        // Skills
        Set<Skill> skills = new TreeSet<Skill>();
        for (int i = REQUIRED_SKILLS.length - 1; i >= 0; i--) {
            skills.add(new Skill(REQUIRED_SKILLS[i]));
        }

        // Build a recipe with the list of required ingredients
        Recipe recipe = new Recipe("recipe1", "the first recipe", null, 2, 42,
                requirements, null, skills, products);

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

}
