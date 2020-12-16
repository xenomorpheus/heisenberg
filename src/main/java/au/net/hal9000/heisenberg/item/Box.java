package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * A simple box container.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Box extends ItemContainerImpl {

  /** serial version id. */
  private static final long serialVersionUID = 1L;

  /** Constructor. */
  public Box() {
    this("Box");
  }

  /**
   * Constructor with name.
   *
   * @param name name for item.
   */
  public Box(final String name) {
    this(name, "A box");
  }

  /**
   * Constructor with name.
   *
   * @param name name for item.
   * @param description description of item.
   */
  Box(String name, String description) {
    super(name, description);
  }
}
