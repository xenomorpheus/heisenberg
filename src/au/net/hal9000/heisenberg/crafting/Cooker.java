package au.net.hal9000.heisenberg.crafting;

import java.util.Vector;
import java.util.Iterator;
import java.util.Set;

import au.net.hal9000.heisenberg.item.*;
import au.net.hal9000.heisenberg.units.Skill;

/**
 * The Cooker takes the {@link Recipe} and ingredients and build the products
 * <br>
 * <br>
 * <h3>User process for Recipe:</h3><br>
 * <ol>
 * <li> Recipe requirements represented as a list of Ingredient objects.
 * <li> For each requirement load a matching Item into the corresponding position in the Cooker.
 * <li> Item objects that don't meet the requirements will be rejected by the cooker.
 * <li> Set the chef and ensure they have the required actionPoints and mana.
 * <li> Set the newItemLocation if any Item objects will be created.
 * <li> Call cook on the cooker to perform the recipe
 * <li> Any new Item objects will be at the newItemLocation.
 * </ol>
 *  @author bruins
 * 
 */
public class Cooker extends ItemContainer {
	/**
	 * recipe describes the process to make the products.
	 */
	private Recipe recipe = null;
	/**
	 * The Being doing the cooking. Supplies any actionPoints and mana.
	 */
	private Being chef = null;
	/**
	 * Where to put any freshly created Items objects.
	 */
	private Location newItemLocation = null;

	/**
	 * Constructor
	 */
	public Cooker(final Recipe recipe) {
		super("Cooker");
		this.recipe = recipe;
		/**
		 * There may be an exploit in ability to hold items of unlimited weight
		 * or volume. <br>
		 * Mitigation: Sometimes Ingredients are be checked on entry, but not
		 * all methods do. <br>
		 * Mitigation: Location of Cooker still needs to be able to hold the
		 * weight and volume. But currently there is an exploit when adding to a
		 * bag inside a bag.<br>
		 */
		this.setVolumeMax(-1f);
		this.setWeightMax(-1f);
	}

	// Setters and Getters
	public final void setChef(final Being chef) {
		this.chef = chef;
	}

	/**
	 * Add an Item to a particular index in the list of itemsAvailable
	 * 
	 * @param index
	 *            where to add Item
	 * @param item
	 * @param container
	 *            where to take the item from
	 * @return true iff all worked.
	 */
	public final boolean setItemsAvailable(final int index, final Item item,
			final ItemContainer container) {
		// spot to add is empty
		boolean successSoFar = (index >= getContentsCount())
				|| (this.getChild(index) == null);
		// item exists
		successSoFar = successSoFar && item != null;
		// container exists
		successSoFar = successSoFar && container != null;
		// not already in itemsAvailalbe
		successSoFar = successSoFar && (!this.contains(item));
		// fulfils Ingredient requirement
		if (successSoFar) {
			IngredientItem ingredientItem = (IngredientItem) recipe
					.getIngredients(index);
			successSoFar = ingredientItem.meetsRequirements(item);
		}
		// If conditions met then accept the item.
		if (successSoFar) {
			container.transfer(item, this);
		}
		return successSoFar;
	}

	// newItemLocation
	public final Location getNewItemLocation() {
		return newItemLocation;
	}

	public final void setNewItemLocation(final Location newItemLocation) {
		this.newItemLocation = newItemLocation;
	}

	// misc

	// ingredients
	/**
	 * Get the list of Ingredients.
	 * 
	 * @return the list of Ingredients.
	 */
	public final Vector<Ingredient> getIngredients() {
		return recipe.getIngredients();
	}

	/**
	 * Return the Ingredient at the specified index.
	 * 
	 * @param index
	 *            the index of the Ingredient requested
	 * @return the Ingredient at this index.
	 */
	public final Ingredient getIngredients(final int index) {
		return recipe.getIngredients(index);
	}

	/**
	 * Return the number of ingredients.
	 * 
	 * @return the number of ingredients.
	 */
	public final int getIngredientsCount() {
		return recipe.getIngredientsCount();
	}

	/**
	 * @return true when all the requirements for the recipe are met.
	 */
	private final boolean requirementsMet() {
		boolean successSoFar = true;

		// mana
		int manaRequired = recipe.getMana();
		if (manaRequired > 0) {
			if (chef == null) {
				successSoFar = false;
			} else {
				successSoFar = manaRequired <= chef.getMana();
			}
		}

		// actionPoints
		if (successSoFar) {
			int actionPointsRequired = recipe.getActionPoints();
			if (actionPointsRequired > 0) {
				if (chef == null) {
					successSoFar = false;
				} else {
					successSoFar = actionPointsRequired <= chef
							.getActionPoints();
				}
			}
		}

		// skills
		if (successSoFar) {
			Set<Skill> required = recipe.getSkills();
			if ((required != null) && (required.size() > 0)) {
				if (chef == null) {
					successSoFar = false;
				} else {
					Set<Skill> got = chef.getSkills();
					if (required != null) {
						successSoFar = got.containsAll(required);
					}
				}
			}
		}

		// ingredients (Item)
		if (successSoFar) {
			successSoFar = ingredientRequirementsMet();
		}
		// products
		if (successSoFar) {
			// there needs to be a valid location for any created Items.
			Vector<String> products = recipe.getProducts();
			if ((products != null) && (products.size() > 0)
					&& (newItemLocation == null)) {
				successSoFar = false;
			}
		}

		return successSoFar;
	}

	/**
	 * Check that every required Ingredient is matched by an Item.
	 * @return true iff all requirements are met.
	 */
	public boolean ingredientRequirementsMet() {
		boolean successSoFar = true;

		Vector<Ingredient> required = getIngredients();
		if (required != null) {
			// Go in reverse so deleting items doesn't effect positions.
			for (int index = required.size() - 1; index >= 0; index--) {
				Item item = this.getChild(index);
				IngredientItem ingredientItem = (IngredientItem) required
						.get(index);
				if (!ingredientItem.meetsRequirements(item)) {
					successSoFar = false;
					break;
				}
			}
		}
		return successSoFar;
	}

	/**
	 * Remove the ingredients that are consumed.
	 */
	private final void ingredientsConsume() {

		// mana
		int manaRequired = recipe.getMana();
		if (manaRequired > 0) {
			chef.manaAdjust(-1 * manaRequired);
		}
		// actionPoints
		int actionPointsRequired = recipe.getActionPoints();
		if (actionPointsRequired > 0) {
			chef.actionPointsAdjust(-1 * actionPointsRequired);
		}

		Vector<Ingredient> required = recipe.getIngredients();
		if (required != null) {
			Location blackHole = new Location("Black Hole");
			// Go in reverse so deleting items doesn't effect positions.
			for (int index = required.size() - 1; index >= 0; index--) {
				Item item = this.getChild(index);
				IngredientItem ingredientItem = (IngredientItem) required
						.get(index);
				if (ingredientItem.isConsumed()) {
					// unlink from other objects
					this.transfer(item, blackHole);
                    item.setLocation(null);
                    item.beNot();
				}
			}
		}
	}

	/**
	 * Create all the products of the recipe.
	 */
	private void createProducts() {
		// TODO Not final version. Only a demonstration.
		Vector<String> products = recipe.getProducts();
		if (products != null) {
			Iterator<String> itr = products.iterator();
			while (itr.hasNext()) {
				Item item = Factory.createItem(itr.next());
				newItemLocation.add(item);
			}
		}
	}

	/**
	 * We will start with a restriction that all cooking is done at once. e.g.
	 * we wan't allow cooking over multiple rounds.
	 */
	public boolean cook() {
		boolean result = true;
		if (requirementsMet()) {
			ingredientsConsume();
			createProducts();
		} else {
			result = false;
		}
		return result;
	}

	/**
	 * A short description.
	 */
	public String toString() {
		return "Cooker for recipe:" + recipe.getId();
	}

	/**
	 * Remove indexed Item and move to specified ItemContainer.
	 * 
	 * @param index
	 *            index of Item to relocate.
	 * @param container
	 *            destination ItemContainer.
	 * @return true iff successfully moved. TODO consider return type void
	 */
	public boolean clearItemsAvailable(int index, ItemContainer container) {
		if (container == null) {
			throw new IllegalArgumentException("container may not be null");
		}
		Item item = getChild(index);
		this.transfer(item, container);
		return true;
	}

}
