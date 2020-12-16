package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.item.property.SplitByWeight;
import au.net.hal9000.heisenberg.util.ItemSplitByWeight;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Some common water.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
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
   * @param pName name to use.
   * @param pDescription description to use.
   */
  private Water(String pName, String pDescription) {
    super(pName, pDescription);
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
