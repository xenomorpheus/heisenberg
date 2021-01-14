package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.property.ItemProperty;
import javax.persistence.Entity;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
public class Cloak extends ItemImpl {

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;

  /** Constructor for Cloak. */
  public Cloak() {
    this("Cloak");
  }

  /**
   * Constructor for Cloak.
   *
   * @param pString String
   */
  private Cloak(String pString) {
    super(pString);
    ItemProperty.setClothing(this, true);
  }
}
