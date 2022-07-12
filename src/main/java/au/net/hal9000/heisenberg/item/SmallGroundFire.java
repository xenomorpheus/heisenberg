package au.net.hal9000.heisenberg.item;

import jakarta.persistence.Entity;

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
   * @param name String
   */
  private SmallGroundFire(String name) {
    this(name, "a small ground fire");
  }

  /**
   * Constructor for SmallGroundFire.
   *
   * @param name String
   * @param pDescription String
   */
  private SmallGroundFire(String name, String description) {
    super(name, description);
  }

  // Methods

}
