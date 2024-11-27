package au.net.hal9000.heisenberg.item.entity;

import jakarta.persistence.Entity;

/**
 * A common rat.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
public class Rat extends EntityItem {

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;

  /** Constructor for Rat. */
  public Rat() {
    super();
  }
}
