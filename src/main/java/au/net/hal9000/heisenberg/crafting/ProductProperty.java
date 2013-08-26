package au.net.hal9000.heisenberg.crafting;


/**
 * As much as possible consider this object immutable. The Setters are here only
 * for JPA.
 * 
 * A Recipe may required any number of Item objects. There will be exactly one
 * ProductProperty object for each required Item. In addition for the noting the
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
public class ProductProperty extends Product {
    /**
     * The item type.
     */
    private String itemType;
    /**
     * Will the item be consumed as part of the cooking process.<br>
     * default is true.
     */

   
    /**
     * Constructor
     * 
     * @param id
     *            the short name of the required item class.
     */
    public ProductProperty(final String id, String itemType,
            boolean isConsumed, float weightMin) {
        super(id);
        this.itemType = itemType;
    }

    /**
     * Constructor
     * 
     * @param id
     *            the short name of the required item class.
     */
    public ProductProperty(final String id) {
        super(id);
        this.itemType = id;
    }

    // Getters and Setters
    /**
     * Set the itemType
     * 
     * @param itemType
     *            the new itemType
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



    public final String toString() {
        return getDescription();
    }

    /** {@inheritDoc} */
    @Override
    public final String getDescription() {
        String string = "of type " + itemType;
        return string;
    }
}
