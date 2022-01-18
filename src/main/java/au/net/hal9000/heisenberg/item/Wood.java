package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.property.SplitByWeight;
import au.net.hal9000.heisenberg.util.ItemSplitByWeight;
import javax.persistence.Entity;

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
    this("Wood");
  }

  /**
   * Constructor for Wood.
   *
   * @param name String
   */
  public Wood(String name) {
    this(name, "some wood");
  }

  /**
   * Constructor for Wood.
   *
   * @param name String
   * @param pDescription String
   */
  public Wood(String name, String pDescription) {
    super(name, pDescription);
  }

  // Methods

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
