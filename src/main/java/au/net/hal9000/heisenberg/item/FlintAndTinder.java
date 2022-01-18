package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
public class FlintAndTinder extends ItemImpl {

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;

  /** Constructor for FlintAndTinder. */
  public FlintAndTinder() {
    this("FlintAndTinder");
  }

  /**
   * Constructor for FlintAndTinder.
   *
   * @param name String
   */
  private FlintAndTinder(String name) {
    this(name, "some flint and tinder");
  }

  /**
   * Constructor for FlintAndTinder.
   *
   * @param name String
   * @param pDescription String
   */
  private FlintAndTinder(String name, String pDescription) {
    super(name, pDescription);
  }

  // Methods

}
