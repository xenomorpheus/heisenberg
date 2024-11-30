package au.net.hal9000.heisenberg.item;

// Persistence
import au.net.hal9000.heisenberg.item.api.HumanoidFood;
// Custom
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import jakarta.persistence.Entity;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
public class Cookie extends ItemImpl implements HumanoidFood {

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;

  /** Constructor for Cookie. */
  public Cookie() {
    super();
    ItemProperty.setHumanoidFood(this, true);
  }

  /**
   * Constructor for Cookie.
   *
   * @param name String
   */
  public Cookie(String name) {
    this();
    setName(name);
  }

  /**
   * Constructor for Cookie.
   *
   * @param name String
   * @param description String
   */
  Cookie(String name, String description) {
    this();
    setName(name);
    setDescription(description);
  }

  // Methods

}
