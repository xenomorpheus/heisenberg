package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.property.ItemProperty;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * The head of a common humanoid.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class HumanoidHead extends ItemContainerImpl {

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;

  /**
   * Constructor for HumanoidHead.
   *
   * @param pName String
   */
  private HumanoidHead(String pName) {
    super(pName);
    ItemProperty.setLiving(this, true);
  }

  /** Constructor for HumanoidHead. */
  public HumanoidHead() {
    this("Head");
  }
}
