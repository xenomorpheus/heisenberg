package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.property.ItemProperty;
import jakarta.persistence.Entity;

/**
 * Is like an item except: May wear at most one magical ring. May wear any number of non-magical
 * rings. Rings may be removed in any order.
 */
@Entity
public class Hand extends Location {

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;

  /** Constructor. */
  public Hand() {
    super();
    ItemProperty.setLiving(this, true);
    setWeightMax(2); // TODO move to config
    setVolumeMax(2); // TODO move to config
  }
}
