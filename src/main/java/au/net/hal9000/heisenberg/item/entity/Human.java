package au.net.hal9000.heisenberg.item.entity;

import jakarta.persistence.Entity;

/**
 * A common human.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
public class Human extends Humanoid {

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;

  /** Constructor for Human. */
  public Human() {
    super();
  }
}
