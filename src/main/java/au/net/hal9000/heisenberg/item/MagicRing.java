package au.net.hal9000.heisenberg.item;

/** MagicRing is a Ring except, it is Magical. */
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import jakarta.persistence.Entity;

/** Magic ring. */
@Entity
public class MagicRing extends Ring {
  /** serial id version. */
  private static final long serialVersionUID = 1L;

  /** Constructor. */
  public MagicRing() {
    super();
    ItemProperty.setMagical(this, true);
  }
}
