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
     * The simple class name of the Item object.
     */
    private String itemType;
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
     * @param id
     *            the short name of the required item class.
     */
    public RequirementItem(final String id, String itemType, boolean isConsumed) {
        super(id, isConsumed);
        this.itemType = itemType;
    }
    /**
     * Constructor
     * 
     * @param id
     *            the short name of the required item class.
     */
    public RequirementItem(final String id) {
        this(id,id, true, 0);        
    }

    /**
     * Constructor
     * 
     * @param id
     *            the short name of the required item class.
     */
    public RequirementItem(final String id, String itemType,
            boolean isConsumed, float weightMin) {
        this(id, itemType, isConsumed);
        this.weightMin = weightMin;
    }

    // Setters

    /**
     * @return the itemType
     */
    public final String getItemType() {
        return itemType;
    }

    public float getWeightMin() {
        return weightMin;
    }

    public final String toString() {
        return getDescription();
    }

    /**
     * Does the Item meet the requirements?
     * 
     * @param item
     *            the Item being evaluated.
     * @return null if the Item meets the requirements.
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
        StringBuilder string = new StringBuilder();
        string.append(super.getDescription());
        string.append(", item type " + itemType);
        if (weightMin > 0) {
            string.append(", weighing at least " + weightMin);
        }
        return string.toString();
    }

}
