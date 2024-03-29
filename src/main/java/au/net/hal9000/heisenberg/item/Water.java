package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.item.property.SplitByWeight;
import au.net.hal9000.heisenberg.util.ItemSplitByWeight;
import jakarta.persistence.Entity;

/**
 * Some common water.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
public class Water extends ItemImpl implements SplitByWeight {
  /** serial version id. */
  private static final long serialVersionUID = 1L;

  /** constructor. */
  public Water() {
    this("Water", "water");
  }

  /**
   * constructor.
   *
   * @param name name to use.
   * @param pDescription description to use.
   */
  private Water(String name, String pDescription) {
    super(name, pDescription);
    ItemProperty.setHumanoidFood(this, true);
  }

  // Methods

  // Static

  /**
   * {@inheritDoc} * @param newItemWeight float
   *
   * @return Item
   */
  @Override
  public Item splitByWeight(float newItemWeight) {
    return ItemSplitByWeight.splitByWeight(this, newItemWeight);
  }
}
