package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.api.HumanoidCoreClothing;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

/** Backpack. */
@Entity
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Backpack extends Bag implements HumanoidCoreClothing {

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;

  /** Constructor for Backpack. */
  public Backpack() {
    super();
    ItemProperty.setClothing(this);
  }
}
