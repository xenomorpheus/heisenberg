package au.net.hal9000.heisenberg.crafting;

import au.net.hal9000.heisenberg.item.Entity;
import au.net.hal9000.heisenberg.item.property.ItemProperty;

/**
 * As much as possible consider these objects immutable.
 * 
 * @author bruins
 * 
 */
public class ProductEntityProperty extends Product {
    /**
     * The name of the property we will change in the Entity.
     */
    private String propertyName;
    /**
     * The amount the property we will change in the Entity.
     */
    private float propertyDelta;

    /**
     * Constructor
     * 
     * @param id
     *            the short name of the required item class.
     * @param propertyDelta
     *            the amount to change the property.
     */
    public ProductEntityProperty(final String id, final String propertyName,
            float propertyDelta) {
        super(id);
        this.propertyName = propertyName;
        this.propertyDelta = propertyDelta;
    }

    // Getters

    /**
     * @return the Entity property name.
     */
    public final String getPropertyName() {
        return propertyName;
    }

    /**
     * 
     * @return the amount the Entity property will change.
     */
    public float getPropertyDelta() {
        return propertyDelta;
    }

    // misc
    public final String toString() {
        return getDescription();
    }

    /** {@inheritDoc} */
    @Override
    public final String getDescription() {
        return super.getDescription() + " type " + propertyName + ", delta is "
                + propertyDelta;
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
        
        ItemProperty.alterPropertyByName(entity, propertyName, propertyDelta);
        
        return null;
    }

}
