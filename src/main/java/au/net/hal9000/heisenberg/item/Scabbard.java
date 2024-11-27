package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

/**
 * An ordinary scabbard (with belt) for holding a sword.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Scabbard extends Box {

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;

  /** Constructor for Scabbard. */
  public Scabbard() {
    super();
    ItemProperty.setClothing(this, true);
  }

  /**
   * {@inheritDoc}
   *
   * @param item Item
   */
  @Override
  public void add(Item item) {
    // We need to accept all Items, not just swords,
    // otherwise our super will accept them for us
    // which is bad.
    // Currently there are no plans to allow low volume items
    // such a coins to be added instead of a sword.
    if (!(item instanceof Sword)) {
      throw new InvalidTypeException("non sword");
    }
    if (size() > 0) {
      throw new TooLargeException("scabbard full");
    }
    super.add(item);
  }
}
