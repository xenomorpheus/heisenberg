package au.net.hal9000.heisenberg.crafting;

public class ProductItem extends Product {

    /**
     * The item type.
     */
    private String itemType;

    /**
     * Constructor
     * 
     * @param id
     *            the short name of the product item class.
     */
    public ProductItem(final String id) {
        super(id);
        this.itemType = id;
    }

    /**
     * Constructor
     * 
     * @param id
     *            Any meaningful name for this requirement. Typically the short
     *            name of the product item class.
     * @param itemType
     *            the short name of the product item class.
     */
    public ProductItem(final String id, final String itemType) {
        this(id);
        this.itemType = itemType;
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
    
    
    
    /** {@inheritDoc} */
    @Override
    public final String getDescription() {
        String string = "of type " + itemType;
        return string;
    }

}
