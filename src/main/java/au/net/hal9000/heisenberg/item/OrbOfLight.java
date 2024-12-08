package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.property.ItemProperty;
import jakarta.persistence.Entity;

/** A magical Orb of light. */
@Entity
public class OrbOfLight extends Candle {

  /** serial id. */
  private static final long serialVersionUID = 1L;

  /** Constructor. */
  public OrbOfLight() {
    super();
    ItemProperty.setMagical(this, true);
  }
}
