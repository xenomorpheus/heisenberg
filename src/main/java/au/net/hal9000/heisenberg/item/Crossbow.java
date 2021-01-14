package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.property.ItemVisitor;
import javax.persistence.Entity;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
public class Crossbow extends ItemImpl {

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;
  /** Field loadedBolt. */
  private CrossbowBolt loadedBolt = null;

  /** Constructor for Crossbow. */
  public Crossbow() {
    this("Crossbow");
  }

  /**
   * Constructor for Crossbow.
   *
   * @param name String
   */
  private Crossbow(final String name) {
    this(name, "A crossbow");
  }

  /**
   * Constructor for Crossbow.
   *
   * @param name String
   * @param description String
   */
  private Crossbow(final String name, String description) {
    super(name, description);
  }

  /**
   * Method getLoadedBolt.
   *
   * @return CrossbowBolt
   */
  public CrossbowBolt getLoadedBolt() {
    return loadedBolt;
  }

  /**
   * Method setLoadedBolt.
   *
   * @param bolt CrossbowBolt
   */
  public void setLoadedBolt(final CrossbowBolt bolt) {
    loadedBolt = bolt;
  }

  // bow plus bolt if present.
  /**
   * Method getWeight.
   *
   * @return float
   */
  public float getWeight() {
    float total = super.getWeight();
    if (null != loadedBolt) {
      total += loadedBolt.getWeight();
    }
    return total;
  }

  // Find items that match the criteria
  /**
   * Method visit.
   *
   * @param visitor ItemVisitor
   */
  public void visit(final ItemVisitor visitor) {
    // Search fields defined in this class.
    if (null != loadedBolt) {
      visitor.visit(loadedBolt);
    }
    // Let our super handle the rest.
    super.accept(visitor);
  }
}
