package au.net.hal9000.heisenberg.crafting;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.property.ItemProperty;

/** As much as possible consider these objects immutable. */
public class ProductEntityProperty extends Product {
  /** The name of the property we will change in the Entity. */
  private String propertyName;

  /** The amount the property we will change in the Entity. */
  private float propertyDelta;

  /**
   * Constructor.
   *
   * @param id the short name of the required item class.
   * @param propertyDelta the amount to change the property.
   * @param propertyName String
   */
  public ProductEntityProperty(final String id, final String propertyName, float propertyDelta) {
    super(id);
    this.propertyName = propertyName;
    this.propertyDelta = propertyDelta;
  }

  // Getters

  /**
   * Entity property name.
   *
   * @return the Entity property name.
   */
  public final String getPropertyName() {
    return propertyName;
  }

  /**
   * The amount the Entity property will change.
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

  @Override
  public final String getDescription() {
    return super.getDescription() + " type " + propertyName + ", delta is " + propertyDelta;
  }

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

  @Override
  public final void createProduct(final Cooker cooker) {
    Item item = cooker.getChef();
    ItemProperty.alterPropertyByName(item, propertyName, propertyDelta);
  }
}
