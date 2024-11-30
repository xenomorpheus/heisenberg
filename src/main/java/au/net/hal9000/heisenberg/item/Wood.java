package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.property.SplitByWeight;
import au.net.hal9000.heisenberg.util.ItemSplitByWeight;
import jakarta.persistence.Entity;

/**
 * Some common wood.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
public class Wood extends ItemImpl implements SplitByWeight {

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;

  /** Constructor for Wood. */
  public Wood() {
    super();
  }

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
