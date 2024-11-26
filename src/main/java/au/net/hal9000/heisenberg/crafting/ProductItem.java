package au.net.hal9000.heisenberg.crafting;

import au.net.hal9000.heisenberg.item.Factory;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.api.ItemContainer;

/**
 * The results of the crafting process. e.g. Item objects.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class ProductItem extends Product {

  /** The item type. */
  private String type;

  /** the weightBase of the new Item. */
  private float weightBase;

  /**
   * Constructor.
   *
   * @param id Any meaningful name for this requirement. Typically the short name of the product
   *     item class.
   * @param type the short name of the product item class.
   * @param weightBase the weightBase of the created Item.
   */
  public ProductItem(final String id, final String type, final float weightBase) {
    super(id);
    this.type = type;
    this.weightBase = weightBase;
  }

  /**
   * Constructor.
   *
   * @param id the short name of the product item class.
   */
  public ProductItem(final String id) {
    this(id, id, 0);
  }

  // Getters
  /**
   * @return the type of the item
   */
  public final String getType() {
    return type;
  }

  /**
   * @return the required weightBase for the new item.
   */
  public float getWeightBase() {
    return weightBase;
  }

  /** {@inheritDoc} */
  @Override
  public final String getDescription() {
    return "Id: " + getId() + ", item type of " + type + ", weightBase " + weightBase;
  }

  /** {@inheritDoc} */
  @Override
  public final String meetsRequirements(final Cooker cooker) {
    // TODO Complain unless there is a Location to place item.

    Item location = cooker.findIngredientByName("Location");
    if (null == location) {
      return "Missing Location";
    }
    return null;
  }

  /** {@inheritDoc} */
  @Override
  public final void createProduct(final Cooker cooker) {
    ItemContainer newItemLocation = (ItemContainer) cooker.findIngredientByName("Location");
    Item item = Factory.createItem(getType());
    newItemLocation.add(item);
  }
}
