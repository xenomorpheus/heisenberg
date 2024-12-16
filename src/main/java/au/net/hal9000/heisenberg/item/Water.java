package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.item.property.SplitByWeight;
import au.net.hal9000.heisenberg.util.ItemSplitByWeight;
import jakarta.persistence.Entity;

/** Some common water. */
@Entity
public class Water extends ItemImpl implements SplitByWeight {
  /** serial version id. */
  private static final long serialVersionUID = 1L;

  /** constructor. */
  public Water() {
    super();
    ItemProperty.setHumanoidFood(this, true);
  }

  // Static

  @Override
  public Item splitByWeight(float newItemWeight) {
    return ItemSplitByWeight.splitByWeight(this, newItemWeight);
  }
}
