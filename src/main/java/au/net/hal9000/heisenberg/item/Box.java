package au.net.hal9000.heisenberg.item;

import jakarta.persistence.Entity;

/**
 * A simple box container.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
public class Box extends Location {

  /** serial version id. */
  private static final long serialVersionUID = 1L;

  /** Constructor. */
  public Box() {
    super();
  }
}
