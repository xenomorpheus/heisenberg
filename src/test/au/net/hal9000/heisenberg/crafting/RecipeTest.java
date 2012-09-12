package test.au.net.hal9000.heisenberg.crafting;

import static org.junit.Assert.*;

import java.util.Vector;
import java.util.Set;
import java.util.TreeSet;
import org.junit.Test;

import au.net.hal9000.heisenberg.crafting.Ingredient;
import au.net.hal9000.heisenberg.crafting.IngredientItem;
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

		Vector<Ingredient> ingredients = new Vector<Ingredient>();

		// IngredientItem
		IngredientItem flintAndTinder = new IngredientItem(new FlintAndTinder());
		flintAndTinder.setConsumed(false);
		ingredients.add(flintAndTinder);

		IngredientItem wood = new IngredientItem(new Wood());
		wood.setWeightMin(3);
		ingredients.add(wood);

		// Build a recipe with the list of required ingredients
		Recipe recipe = new Recipe("recipe1", "the first recipe", 0, 0,
				ingredients, null, null);

		assertEquals("ingredient count", ingredients.size(),
				recipe.getIngredientsCount());
        assertEquals("ingredient 0",flintAndTinder,recipe.getIngredients(0));
        assertEquals("ingredient 1",wood,recipe.getIngredients(1));
	}

	private void println(String string) {
		// System.out.println(string);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testRecipe() {
		Vector<Ingredient> ingredients = new Vector<Ingredient>();

		// IngredientItem
		IngredientItem flintAndTinder = new IngredientItem(new FlintAndTinder());
		flintAndTinder.setConsumed(false);
		ingredients.add(flintAndTinder);

		IngredientItem wood = new IngredientItem(new Wood());
		wood.setWeightMin(3);
		ingredients.add(wood);

		// Product(s)
		Vector<String> products = new Vector<String>();
		products.add("SmallGroundFire");

		// Skills
		Set<Skill> skills = new TreeSet<Skill>();
		for (int i = REQUIRED_SKILLS.length - 1; i >= 0; i--) {
			skills.add(new Skill(REQUIRED_SKILLS[i]));
		}

		// Build a recipe with the list of required ingredients
		Recipe recipe = new Recipe("recipe1", "the first recipe", 2, 42,
				ingredients, skills, products);

		assertEquals("id", "recipe1", recipe.getId());
		assertEquals("description", "the first recipe", recipe.getDescription());
		assertEquals("mana", 2, recipe.getMana());
		assertEquals("actionPoints", 42, recipe.getActionPoints());
		assertEquals("ingredient count", ingredients.size(), recipe
				.getIngredients().size());
		assertEquals("skill count", REQUIRED_SKILLS.length, recipe.getSkills()
				.size());
		assertEquals("ingredient count", ingredients.size(),
				recipe.getIngredientsCount());

		println(recipe.toString());
	}

}
