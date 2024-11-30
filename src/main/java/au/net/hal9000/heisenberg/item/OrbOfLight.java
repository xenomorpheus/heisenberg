package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.property.ItemProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

/**
 * A magical Orb of light.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
class OrbOfLight extends Candle {

  /** serial id. */
  private static final long serialVersionUID = 1L;

  /** Constructor. */
  public OrbOfLight() {
    super();
    ItemProperty.setMagical(this, true);
  }
}
