package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.property.ItemProperty;
import jakarta.persistence.Entity;

/** A common cloak. */
@Entity
public class Cloak extends ItemImpl {

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;

  /** Constructor for Cloak. */
  public Cloak() {
    super();
    ItemProperty.setClothing(this, true);
  }
}
