package au.net.hal9000.heisenberg.item;

// Persistence
import au.net.hal9000.heisenberg.item.api.HumanoidFood;
// Custom
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import javax.persistence.Entity;

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
    this("Cookie");
  }

  /**
   * Constructor for Cookie.
   *
   * @param pName String
   */
  public Cookie(String pName) {
    super(pName);
    ItemProperty.setHumanoidFood(this, true);
  }

  /**
   * Constructor for Cookie.
   *
   * @param pName String
   * @param pDescription String
   */
  Cookie(String pName, String pDescription) {
    super(pName, pDescription);
  }

  // Methods

}
