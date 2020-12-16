package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.property.ItemProperty;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
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
