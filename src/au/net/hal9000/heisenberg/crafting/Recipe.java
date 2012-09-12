package au.net.hal9000.heisenberg.crafting;

import java.util.Vector;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import au.net.hal9000.heisenberg.units.Skill;

/**
 * Recipes describe the process of crafting, spells, cooking etc.
 * 
 * <P>
 * Recipes are comprised of a list of Ingredients ({@link Ingredient}).
 * <P>
 * <P>
 * Recipes produce outcomes, eg. ({@link au.net.hal9000.heisenberg.item.Item}).
 * <P>
 * 
 * <P>
 * In order to perform a Recipe all the requirements must be met.
 * </P>
 * 
 * <h1>Examples:</h1>
 * 
 * <h2>Mundane:</h2>
 * 
 * <h3>Cook a meat pie</h3> Requires:
 * <ul>
 * <li>Possess plan (recipe) for pie,
 * <li>add X pounds four,
 * <li>add X pounds meat,
 * <li>possess skill(s): cooking
 * <li>possess item(s): cooking utensils (not consumed), small fire (not
 * consumed)
 * <li>add Y action points,
 * <li>round-limit = 20 - can work on it.
 * </ul>
 * Produces:
 * <ul>
 * </li> X meat pies
 * </ul>
 * 
 * 
 * <h3>Carpenter build a basic wooden chair</h3> Requires:
 * <ul>
 * <li>Possess plan (recipe) for basic wooden chair,
 * <li>add X pounds of wood (consumed),
 * <li>possess items(s): carpentry tools (not consumed)
 * <li>possess skill(s): carpentry
 * <li>add Y action points,
 * <li>round-limit = 20 - can work on it.
 * </ul>
 * Produces:
 * <ul>
 * <li>a basic wooden chair, and non-consumed items.
 * </ul>
 * 
 * <h3>Build small open fire:</h3> Requires:
 * <ul>
 * <li>Possess plan (recipe) for small open fire,
 * <li>add X pounds of wood (consumed),
 * <li>add flint and tinder,
 * <li>add Y action points,
 * <li>round-limit = 20 - can work on it.
 * </ul>
 * Produces:
 * <ul>
 * <li>a small open fire, and non-consumed items.
 * </ul>
 * 
 * <h2>Spell</h2>
 * 
 * <h3>Wizard casts small light orb</h3> Requires:
 * <ul>
 * <li>Possess spell (recipe) for small light,
 * <li>possess power words(s): as per spell.
 * <li>add X mana
 * <li>add Y action points,
 * <li>round-limit = 1 - Must be done in one round
 * </ul>
 * Produces:
 * <ul>
 * <li>a small orb that produces light for Z rounds.
 * </ul>
 * 
 * <h2>Notes</h2> The following default to being consumed when added: Action
 * points, mana.
 * 
 * <P>
 * Developer Notes: <br>
 * No need for more than one instance of a recipe.<br>
 * Lets try making Recipes immutable and see how it goes Consider moving Recipe
 * to units package.<br>
 * </P>
 * 
 * @author bruins
 */
public class Recipe {

	/**
	 * The identifier for this recipe.
	 */
	private String id = new String();
	/**
	 * Describe the recipe in terms a game player understands.
	 */
	private String description = new String();
	/**
	 * The amount of Mana required for this recipe.
	 */
	private int mana = 0;
	/**
	 * The amount of ActionPoints required for this recipe.
	 */
	private int actionPoints = 0;
	/**
	 * The total items required for this recipe.
	 */
	private Vector<Ingredient> ingredients;
	/**
	 * The {@link Skill} objects required.
	 */
	private Set<Skill> skills = new TreeSet<Skill>();
	/**
	 * What the recipe produces.
	 */
	private Vector<String> products = new Vector<String>();

	/**
	 * 
	 * @param id
	 *            Identifier
	 * @param description
	 *            Description.
	 * @param mana
	 *            amount of mana required
	 * @param actionPoints
	 *            number of actionPoints required.
	 * @param ingredients
	 *            the Ingredient list.
	 * @param skills
	 *            the Skill objects required
	 * @param products
	 *            the results that will be produced.
	 */
	public Recipe(final String id, final String description, final int mana,
			final int actionPoints, final Vector<Ingredient> ingredients,
			final Set<Skill> skills, final Vector<String> products) {
		super();
		this.id = id;
		this.description = description;
		this.mana = mana;
		this.actionPoints = actionPoints;
		this.ingredients = ingredients;
		this.skills = skills;
		this.products = products;
	}

	// getters and setters
	// id
	/**
	 * Get the ID
	 * 
	 * @return The short identifier for this Recipe.
	 */
	public final String getId() {
		return id;
	}

	/**
	 * Set the ID
	 * 
	 * @deprecated only here for JPA.
	 * @param id
	 *            the ID
	 */
	public final void setId(String id) {
		this.id = id;
	}

	// description
	/**
	 * Get the Description
	 * 
	 * @return the description
	 */
	public final String getDescription() {
		return description;
	}

	/**
	 * Set the Description
	 * 
	 * @deprecated only here for JPA.
	 * @param description
	 *            human text description
	 */
	public final void setDescription(String description) {
		this.description = description;
	}

	// mana
	/**
	 * Get the mana
	 * 
	 * @return the amount of mana required.
	 */
	public final int getMana() {
		return mana;
	}

	/**
	 * Set the mana required
	 * 
	 * @deprecated only here for JPA.
	 * @param points
	 *            the amount of mana.
	 */
	public final void setMana(final int points) {
		mana = points;
	}

	// actionPoints
	/**
	 * Get the action points required.
	 * 
	 * @return the action points required.
	 */
	public final int getActionPoints() {
		return actionPoints;
	}

	/**
	 * Set the action points required.
	 * 
	 * @deprecated only here for JPA.
	 * @param points
	 *            the points required.
	 */
	public final void setActionPoints(final int points) {
		actionPoints = points;
	}

	// ingredients
	/**
	 * Get the list of Ingredients.
	 * 
	 * @return the list of Ingredients.
	 */
	public final Vector<Ingredient> getIngredients() {
		return ingredients;
	}

	/**
	 * Return the Ingredient at the specified index.
	 * 
	 * @param index
	 *            the index of the Ingredient requested
	 * @return the Ingredient at this index.
	 */
	public final Ingredient getIngredients(final int index) {
		return ingredients.get(index);
	}

	/**
	 * Return the number of ingredients.
	 * 
	 * @return the number of ingredients.
	 */
	public final int getIngredientsCount() {
		return ingredients.size();
	}

	/**
	 * Set the Ingredients
	 * 
	 * @deprecated only here for JPA.
	 * @param ingredients
	 *            the required Ingredient objects.
	 */
	public final void setIngredients(final Vector<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	/**
	 * Add a list of Ingredient objects.
	 * 
	 * @deprecated only here for JPA.
	 * @param ingredients
	 *            a list of Ingredient objects.
	 */
	public final void ingredientsAddAll(final Vector<Ingredient> ingredients) {
		this.ingredients.addAll(ingredients);
	}

	/**
	 * Add a an Ingredient object.
	 * 
	 * @deprecated only here for JPA.
	 * @param ingredient
	 *            an Ingredient object.
	 */
	public final void ingredientsAdd(final Ingredient ingredient) {
		ingredients.add(ingredient);
	}

	// products
	/**
	 * @return the products that this recipe produces.
	 */
	public final Vector<String> getProducts() {
		return products;
	}

	// skills
	/**
	 * Get the Skill objects.
	 * 
	 * @return a set of Skill objects
	 */
	public final Set<Skill> getSkills() {
		return skills;
	}

	/**
	 * Set the Skill objects.
	 * 
	 * @deprecated only here for JPA.
	 */
	public final void setSkills(final Set<Skill> skills) {
		this.skills = skills;
	}

	/**
	 * Add extra Skills to the list of required ingredients.
	 * 
	 * @deprecated only here for JPA.
	 * @param skills
	 */
	public final void skillsAdd(final Set<Skill> skills) {
		skills.addAll(skills);
	}

	/**
	 * Add extra PowerWords to the list of required ingredients.
	 * 
	 * @deprecated only here for JPA.
	 * @param powerWords
	 *            a Set of PowerWord objects to add.
	 */
	public final void skillsAddAll(final Set<Skill> powerWords) {
		powerWords.addAll(powerWords);
	}

	// Misc Methods
	/**
	 * 
	 * @return A Cooker object for this recipe.
	 */
	public final Cooker getNewCooker() {
		return new Cooker(this);
	}

	/**
	 * @return a description
	 */
	public String toString() {
		return details();
	}

	/**
	 * @return a description
	 */
	public String details() {
		String string = new String("Id: " + id + "\n" + "Description: "
				+ description + "\n" + "Mana:" + mana + "\n"
				+ "Action Point(s):" + actionPoints + "\n");

		if (skills != null) {
			int index = 0;
			string = string.concat("Skill(s):\n");
			for (Iterator<Skill> itr = skills.iterator(); itr.hasNext();) {
				string = string.concat("  " + index + ": "
						+ itr.next().toString() + "\n");
				index++;
			}
		}
		if (ingredients != null) {
			int index = 0;
			string = string.concat("Ingredient(s):\n");
			for (Iterator<Ingredient> itr = ingredients.iterator(); itr
					.hasNext();) {
				string = string.concat("  " + index + ": "
						+ itr.next().getDescription() + "\n");
				index++;
			}
		}
		if (products != null) {
			int index = 0;
			string = string.concat("Product(s):\n");
			for (Iterator<String> itr = products.iterator(); itr.hasNext();) {
				string = string.concat("  " + index + ": " + itr.next() + "\n");
				index++;
			}
		}
		return string;
	}

}
