package au.net.hal9000.heisenberg.item;

/** MagicRing is a Ring except, it is Magical. */
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

/**
 * Magic ring.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class MagicRing extends Ring {
  /** serial id version. */
  private static final long serialVersionUID = 1L;

  /** Constructor. */
  public MagicRing() {
    super("MagicRing");
    ItemProperty.setMagical(this, true);
  }

  /**
   * Constructor.
   *
   * @param name name of ring.
   */
  public MagicRing(String name) {
    this();
    setName(name);
  }
}
