package au.net.hal9000.heisenberg.crafting;

import au.net.hal9000.heisenberg.item.Factory;
import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.ItemContainer;
import au.net.hal9000.heisenberg.item.exception.CantWearException;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class ProductItem extends Product {

    /**
     * The item type.
     */
    private String type;

    /**
     * the weightBase of the new Item.
     */
    private float weightBase;

    /**
     * Constructor.
     * 
     * @param id
     *            Any meaningful name for this requirement. Typically the short
     *            name of the product item class.
     * @param type
     *            the short name of the product item class.
     * @param weightBase
     *            the weightBase of the created Item.
     */
    public ProductItem(final String id, final String type,
            final float weightBase) {
        super(id);
        this.type = type;
        this.weightBase = weightBase;
    }

    /**
     * Constructor.
     * 
     * @param id
     *            the short name of the product item class.
     */
    public ProductItem(final String id) {
        this(id, id, 0);
    }

    // Getters
    /**
     * 
     * @return the type of the item
     */
    public final String getType() {
        return type;
    }

    /**
     * 
     * 
     * @return the required weightBase for the new item.
     */
    public float getWeightBase() {
        return weightBase;
    }

    /**
     * {@inheritDoc} * @return String
     */
    @Override
    public final String getDescription() {
        return "Id: " + getId() + ", item type of " + type + ", weightBase "
                + weightBase;
    }

    /**
     * {@inheritDoc} * @param cooker Cooker
     * 
     * @return String
     */
    @Override
    public final String meetsRequirements(final Cooker cooker) {
        // TODO Complain unless there is a Location to place item.

        Item location = cooker.findIngredientByName("Location");
        if (null == location) {
            return "Missing Location";
        }
        return null;
    }

    /**
     * {@inheritDoc} * @param cooker Cooker
     * 
     * @return String
     * @throws InvalidTypeException
     * @throws TooLargeException
     * @throws TooHeavyException
     * @throws CantWearException 
     */
    @Override
    public final String createProduct(final Cooker cooker)
            throws InvalidTypeException, TooHeavyException, TooLargeException, CantWearException {
        ItemContainer newItemLocation = (ItemContainer) cooker
                .findIngredientByName("Location");
        Item item = Factory.createItem(getType());
        newItemLocation.add(item);
        return null;
    }

}
