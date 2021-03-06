package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;

/**
 * A small ground fire.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
public class SmallGroundFire extends ItemImpl {

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;

  /** Constructor for SmallGroundFire. */
  public SmallGroundFire() {
    this("SmallGroundFire");
  }

  /**
   * Constructor for SmallGroundFire.
   *
   * @param pName String
   */
  private SmallGroundFire(String pName) {
    this(pName, "a small ground fire");
  }

  /**
   * Constructor for SmallGroundFire.
   *
   * @param pName String
   * @param pDescription String
   */
  private SmallGroundFire(String pName, String pDescription) {
    super(pName, pDescription);
  }

  // Methods

}
