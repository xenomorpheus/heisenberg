package au.net.hal9000.heisenberg.crafting;

import au.net.hal9000.heisenberg.item.Entity;
import au.net.hal9000.heisenberg.item.property.ItemProperty;

/**
 * As much as possible consider these objects immutable.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
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
     * Constructor.
     * 
     * @param id
     *            the short name of the required item class.
     * @param propertyDelta
     *            the amount to change the property.
     * @param propertyName
     *            String
     */
    public ProductEntityProperty(final String id, final String propertyName,
            float propertyDelta) {
        super(id);
        this.propertyName = propertyName;
        this.propertyDelta = propertyDelta;
    }

    // Getters

    /**
     * 
     * @return the Entity property name.
     */
    public final String getPropertyName() {
        return propertyName;
    }

    /**
     * 
     * 
     * @return the amount the Entity property will change.
     */
    public float getPropertyDelta() {
        return propertyDelta;
    }

    // misc
    /**
     * Method toString.
     * 
     * @return String
     */
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
        // Need to find a target to apply property change to.
        // A chef is a valid target.
        if (null != cooker.getChef()) {
            return null;
        }
        // A Target has been specified.
        if (null != cooker.findIngredientByName("Target")) {
            return null;
        }
        return "A Target or chef is required";
    }

    /** {@inheritDoc} */
    @Override
    public final String createProduct(final Cooker cooker) {
        Entity entity = cooker.getChef();

        ItemProperty.alterPropertyByName(entity, propertyName, propertyDelta);

        return null;
    }

}
