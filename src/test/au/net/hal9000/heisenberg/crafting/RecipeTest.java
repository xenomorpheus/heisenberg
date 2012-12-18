package test.au.net.hal9000.heisenberg.crafting;

import static org.junit.Assert.*;

import java.util.Vector;
import java.util.Set;
import java.util.TreeSet;
import org.junit.Test;

import au.net.hal9000.heisenberg.crafting.Requirement;
import au.net.hal9000.heisenberg.crafting.RequirementItem;
import au.net.hal9000.heisenberg.crafting.Recipe;
import au.net.hal9000.heisenberg.item.*;
import au.net.hal9000.heisenberg.units.Skill;

public class RecipeTest {
	static int REQUIRED_ACTION_POINTS = 42;
	static int REQUIRED_MANA = 3;
	static String[] REQUIRED_SKILLS = new String[] { "Skill0", "Skill1",
			"Skill2" };

	@SuppressWarnings("deprecation")
	@Test
	public void testRecipeIngredients() {

		Vector<Requirement> requirements = new Vector<Requirement>();

		// RequirementItem
		RequirementItem flintAndTinder = new RequirementItem(new FlintAndTinder());
		flintAndTinder.setConsumed(false);
		requirements.add(flintAndTinder);

		RequirementItem wood = new RequirementItem(new Wood());
		wood.setWeightMin(3);
		requirements.add(wood);

		// Build a recipe with the list of required ingredients
		Recipe recipe = new Recipe("recipe1", "the first recipe", null, 0,
				0, requirements, null, null, null);

		assertEquals("ingredient count", requirements.size(),
				recipe.getRequirementsCount());
        assertEquals("ingredient 0",flintAndTinder,recipe.getRequirement(0));
        assertEquals("ingredient 1",wood,recipe.getRequirement(1));
	}

	private void println(String string) {
		// System.out.println(string);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testRecipe() {
		Vector<Requirement> requirements = new Vector<Requirement>();

		// RequirementItem
		RequirementItem flintAndTinder = new RequirementItem(new FlintAndTinder());
		flintAndTinder.setConsumed(false);
		requirements.add(flintAndTinder);

		RequirementItem wood = new RequirementItem(new Wood());
		wood.setWeightMin(3);
		requirements.add(wood);

		// Product(s)
		Vector<String> products = new Vector<String>();
		products.add("SmallGroundFire");

		// Skills
		Set<Skill> skills = new TreeSet<Skill>();
		for (int i = REQUIRED_SKILLS.length - 1; i >= 0; i--) {
			skills.add(new Skill(REQUIRED_SKILLS[i]));
		}

		// Build a recipe with the list of required ingredients
		Recipe recipe = new Recipe("recipe1", "the first recipe", null, 2,
				42, requirements, null, skills, products);

		assertEquals("id", "recipe1", recipe.getId());
		assertEquals("description", "the first recipe", recipe.getDescription());
		assertEquals("mana", 2, recipe.getMana());
		assertEquals("actionPoints", 42, recipe.getActionPoints());
		assertEquals("ingredient count", requirements.size(), recipe
				.getRequirements().size());
		assertEquals("skill count", REQUIRED_SKILLS.length, recipe.getSkills()
				.size());
		assertEquals("ingredient count", requirements.size(),
				recipe.getRequirementsCount());

		println(recipe.toString());
	}

}
