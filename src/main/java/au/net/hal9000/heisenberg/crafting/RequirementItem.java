package au.net.hal9000.heisenberg.crafting;

import au.net.hal9000.heisenberg.item.Item;

/**
 * As much as possible consider this object immutable. The Setters are here only
 * for JPA.
 * 
 * A Recipe may required any number of Item objects. There will be exactly one
 * RequirementItem object for each required Item. In addition for the noting the
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
public class RequirementItem extends Requirement {
    /**
     * The item type.
     */
    private String itemType;
    /**
     * Will the item be consumed as part of the cooking process.<br>
     * default is true.
     */
    private boolean isConsumed = true;
    /**
     * Minimum weight of this item.<br>
     * e.g. 3 weight units of wood.<br>
     * default is 0; <br>
     * TODO Re-factor to IngredientItemProperty
     */
    private float weightMin = 0;

    /**
     * Constructor
     * 
     * @param id the short name of the required item class.
     */
    public RequirementItem(final String id) {
        super();
        this.id = id;
        this.itemType = id;
    }

    // Getters and Setters
    /**
     * Set the itemType
     * @param itemType the new itemType
     */
    public void setType(final String itemType) {
        this.itemType = itemType;
    }

    /**
     * @return the itemType
     */
    public final String getItemType() {
        return itemType;
    }

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
     *            minimum weight required of item.
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
     * @param item
     *            the Item being evaluated.
     * @return true if the Item meets the requirements.
     */
    public final String meetsRequirements(final Item item) {
        
        // Correct Class
        if (!item.instanceOf(itemType)) {
            return "item must be a " + itemType;
        }
        // Correct Weight
        if (item.getWeight() < weightMin) {
            return "item must weigh at least " + weightMin;
        }
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public final String getDescription() {
        String string = "of type " + itemType;
        if (weightMin > 0) {
            string += ", weighing at least " + weightMin;
        }
        if (!isConsumed) {
            string += ", not consumed";
        }
        return string;
    }

}
