package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.property.ItemProperty;
import jakarta.persistence.Entity;

/**
 * The head of a common humanoid.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
public class HumanoidHead extends Location {

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;

  /**
   * Constructor for HumanoidHead.
   *
   * @param name String
   */
  private HumanoidHead(String name) {
    super(name);
    ItemProperty.setLiving(this, true);
  }

  /** Constructor for HumanoidHead. */
  public HumanoidHead() {
    this("Head");
  }
}
