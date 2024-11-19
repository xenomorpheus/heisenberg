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
    super("Horse");
    ItemProperty.setLiving(this, true);
  }

  /**
   * Constructor.
   *
   * @param name name of Item.
   */
  public Horse(String name) {
    this();
    setName(name);
  }
}
