package au.net.hal9000.heisenberg.item;

// Persistence
import au.net.hal9000.heisenberg.item.api.HumanoidFood;
// Custom
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import jakarta.persistence.Entity;

/** A common biscuit. */
@Entity
public class Biscuit extends ItemImpl implements HumanoidFood {

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;

  /** Constructor for Biscuit. */
  public Biscuit() {
    super();
    ItemProperty.setHumanoidFood(this, true);
  }
}
