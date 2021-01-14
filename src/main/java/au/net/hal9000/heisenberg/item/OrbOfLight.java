package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.property.ItemProperty;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

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
    this("Orb Of Light");
  }

  /**
   * Constructor.
   *
   * @param name name of the orb.
   */
  private OrbOfLight(String name) {
    this(name, "orb of light");
  }

  /**
   * Constructor.
   *
   * @param name name of the orb.
   * @param description the description.
   */
  OrbOfLight(String name, String description) {
    super(name, description);
    ItemProperty.setMagical(this, true);
  }
}
