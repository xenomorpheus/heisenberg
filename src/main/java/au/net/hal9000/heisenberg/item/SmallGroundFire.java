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
    super();
    setDescription("a small ground fire");
  }
}
