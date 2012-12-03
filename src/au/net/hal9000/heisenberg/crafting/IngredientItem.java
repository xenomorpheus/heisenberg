package au.net.hal9000.heisenberg.crafting;

import au.net.hal9000.heisenberg.item.IItem;
import au.net.hal9000.heisenberg.item.Item;

/**
 * As much as possible consider this object immutable. The Setters are here only
 * for JPA.
 * 
 * A Recipe may required any number of Item objects. There will be exactly one
 * IngredientItem object for each required Item. In addition for the noting the
 * type of Item, this object will also note if it is consumed, minimum weight
 * etc.
 * <ul>
 * <li>Item - Wood with a minimum of 3 weight units.
 * <li>Item - FlintAndTinder, not consumed.
 * <ul>
 * 
 * 
 * 
 * 
 * @author bruins
 * 
 */
public class IngredientItem extends Ingredient {
	/**
	 * The ingredient item.
	 */
	// TODO should this just be the simple class name ?
	private Item item;
	/**
	 * Will the item be consumed as part of the cooking process.<br>
	 * default is true.
	 */
	private boolean isConsumed = true;
	/**
	 * Minimum weight of this item.<br>
	 * e.g. 3 weight units of wood.<br>
	 * default is 0; <br>
	 * TODO Refactor to IngredientItemPoroperty
	 */
	private float weightMin = 0;

	/**
	 * Constuctor
	 * 
	 * @param item
	 */
	public IngredientItem(final Item item) {
		super();
		this.item = item;
	}

	// Getters and Setters
	public boolean isConsumed() {
		return isConsumed;
	}

	/**
	 * @deprecated only here for JPA.
	 * @param isConsumed
	 *            true if the Item will be consumed.
	 */
	public void setConsumed(boolean isConsumed) {
		this.isConsumed = isConsumed;
	}

	public float getWeightMin() {
		return weightMin;
	}

	/**
	 * @deprecated only here for JPA.
	 * @param weightMin
	 *            minimum weight requried of item.
	 */
	public void setWeightMin(float weightMin) {
		this.weightMin = weightMin;
	}

	public final String toString() {
		return getDescription();
	}

	/**
	 * Does the Item meet the requirements?
	 * 
	 * @param item2
	 *            the Item being evaluated.
	 * @return true if the Item meets the requirements.
	 */
	public final boolean meetsRequirements(IItem item2) {
		// Correct Class
		boolean successSoFar = item.getClass().isInstance(item2);
		// Correct Weight
		successSoFar = successSoFar && item2.getWeight() >= weightMin;
		return successSoFar;
	}

	/** {@inheritDoc} */
	@Override
	String getDescription() {
		String string = "Item: " + this.item.toString();
		if (weightMin > 0) {
			string += ", weightMin=" + weightMin;
		}
		if (!isConsumed) {
			string += ", consumed=" + isConsumed;
		}
		return string;
	}

}
