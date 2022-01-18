package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.api.HumanoidCoreClothing;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * Backpack.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Backpack extends Bag implements HumanoidCoreClothing {

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;

  /** Constructor for Backpack. */
  public Backpack() {
    this("Backpack");
  }

  /**
   * Constructor for Backpack.
   *
   * @param pString String
   */
  public Backpack(final String pString) {
    super(pString);
    ItemProperty.setClothing(this, true);
  }
}
