package au.net.hal9000.heisenberg.item;

// Persistence
import jakarta.persistence.Entity;

/**
 * A Fungus.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
public class Fungus extends ItemImpl {

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;

  /** Constructor for Fungus. */
  public Fungus() {
    this("Fungus");
  }

  /**
   * Constructor for Fungus.
   *
   * @param name String
   */
  public Fungus(String name) {
    this(name, "Some fugus");
  }

  /**
   * Constructor for Fungus.
   *
   * @param name String
   * @param description String
   */
  public Fungus(String name, String description) {
    super(name, description);
  }

  // Methods

}
