package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.api.Sharp;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

/**
 * A common Bog for holding multiple Item objects.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Bag extends Box {

  /** serial version. */
  private static final long serialVersionUID = 1L;

  /** maximum default weight this bag can hold. */
  private static final float WEIGHT_MAX_DEFAULT = 100; // TODO move to config

  /** maximum default volume this bag can hold. */
  private static final float VOLUME_MAX_DEFAULT = 100; // TODO move to config

  /** Constructor. */
  public Bag() {
    super();
    setDescription("A common cloth sack about 2 feet by 4 feet in size.");
    setWeightMax(WEIGHT_MAX_DEFAULT);
    setVolumeMax(VOLUME_MAX_DEFAULT);
  }

  // Methods

  // TODO finish rupture
  /** Method rupture. */
  public void rupture() {
    // System.out.println("Ordinary rupture");
  }

  /**
   * {@inheritDoc}
   *
   * @param item Item
   */
  @Override
  public void add(final Item item) {
    // Look for sharp items. Wrapped sharp items are safe.
    if (item instanceof Sharp) {
      rupture();
      throw new InvalidTypeException("Sharp");
    }
    super.add(item);
  }
}
