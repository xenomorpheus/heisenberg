package au.net.hal9000.heisenberg.crafting;

import au.net.hal9000.heisenberg.item.Entity;
import au.net.hal9000.heisenberg.item.property.ItemProperty;

/**
 * As much as possible consider this object immutable.
 * 
 * @author bruins
 * 
 */
public class ProductProperty extends Product {
    /**
     * The name of the property we will change in the Entity.
     */
    private String propertyName;

    /**
     * Constructor
     * 
     * @param id
     *            the short name of the required item class.
     */
    public ProductProperty(final String id, final String propertyName) {
        super(id);
        this.propertyName = propertyName;
    }

    // Getters and Setters

    /**
     * @return the property name.
     */
    public final String getPropertyName() {
        return propertyName;
    }

    public final String toString() {
        return getDescription();
    }

    /** {@inheritDoc} */
    @Override
    public final String getDescription() {
        String string = "of type " + propertyName;
        return string;
    }

    /** {@inheritDoc} */
    @Override
    String meetsRequirements(Cooker cooker) {
        // TODO Auto-generated method stub
        return "TODO check for target/chef";
    }

    /** {@inheritDoc} */
    @Override
    public final String createProduct(final Cooker cooker) {
        Entity entity = cooker.getChef();
        int nourishment = ItemProperty.getNourishment(entity);
        ItemProperty.setNourishment(entity, nourishment + 10);
        return null;
    }

}
