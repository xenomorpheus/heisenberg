package au.net.hal9000.heisenberg.item.entity;

import au.net.hal9000.heisenberg.item.api.HumanoidMount;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * A common horse.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Horse extends au.net.hal9000.heisenberg.item.entity.Entity implements HumanoidMount {

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
