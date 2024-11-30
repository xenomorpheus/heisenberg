package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import jakarta.persistence.Entity;

/**
 * A common quiver for holding arrows.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
public class Quiver extends Location {

  /** serial version. */
  private static final long serialVersionUID = 1L;

  /** Quiver volume default. 20 times volume of an arrow. */
  private static final float VOLUME_MAX_DFT = 20;

  /** Constructor. */
  public Quiver() {
    super("Quiver", "Quiver");
    setVolumeMax(VOLUME_MAX_DFT);
    ItemProperty.setClothing(this, true);
  }

  /**
   * Constructor.
   *
   * @param name name to use.
   */
  public Quiver(final String name) {
    this();
    setName(name);
  }

  /**
   * Add arrows to the quiver.
   *
   * @param item arrow Item to add to Quiver.
   */
  public void add(final Item item) {
    if (item instanceof Arrow) {
      super.add((Arrow) item);
    } else {
      throw new InvalidTypeException("Only Arrows accepted");
    }
  }
}
