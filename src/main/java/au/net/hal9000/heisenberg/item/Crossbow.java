package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.property.ItemVisitor;
import jakarta.persistence.Entity;

/** A Crossbow. */
@Entity
public class Crossbow extends ItemImpl {

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;

  /** Field loadedBolt. */
  private CrossbowBolt loadedBolt = null;

  /** Constructor for Crossbow. */
  public Crossbow() {
    super();
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
  public float totalWeight() {
    float total = super.totalWeight();
    if (null != loadedBolt) {
      total += loadedBolt.totalWeight();
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
