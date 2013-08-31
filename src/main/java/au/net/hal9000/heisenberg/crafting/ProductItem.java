package au.net.hal9000.heisenberg.crafting;

import au.net.hal9000.heisenberg.item.Factory;
import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.ItemContainer;

public class ProductItem extends Product {

    /**
     * The item type.
     */
    private String type;

    /**
     * Constructor
     * 
     * @param id
     *            the short name of the product item class.
     */
    public ProductItem(final String id) {
        super(id);
        this.type = id;
    }

    /**
     * Constructor
     * 
     * @param id
     *            Any meaningful name for this requirement. Typically the short
     *            name of the product item class.
     * @param type
     *            the short name of the product item class.
     */
    public ProductItem(final String id, final String type) {
        this(id);
        this.type = type;
    }

    // Getters and Setters
    /**
     * @return the type of the item
     */
    public final String getType() {
        return type;
    }

    /** {@inheritDoc} */
    @Override
    public final String getDescription() {
        return "Id: "+id+", item type of " + type;
    }

    /** {@inheritDoc} */
    @Override
    public final String meetsRequirements(final Cooker cooker) {
        // TODO Complain unless there is a Location to place item.

        Item location = cooker.findIngredientByName("Location");
        if (location == null) {
            return "Missing Location";
        }
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public final String createProduct(final Cooker cooker) {
        ItemContainer newItemLocation = (ItemContainer) cooker
                .findIngredientByName("Location");
        Item item = Factory.createItem(getType());
        newItemLocation.add(item);
        return null;
    }

}
