package au.net.hal9000.heisenberg.item.entity;

import au.net.hal9000.heisenberg.item.api.HumanoidMount;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import jakarta.persistence.Entity;

/**
 * A common horse.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
public class Horse extends EntityItem implements HumanoidMount {

  /** serial version id. */
  private static final long serialVersionUID = 1L;

  /** Constructor. */
  public Horse() {
    this("Horse");
  }

  /**
   * Constructor.
   *
   * @param name name of Item.
   */
  private Horse(String name) {
    super(name);
    ItemProperty.setLiving(this, true);
  }
}
