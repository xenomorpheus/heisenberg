package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.property.ItemVisitor;
import jakarta.persistence.Entity;
import lombok.Setter;
import lombok.Getter;

/** A Crossbow. */
@Entity
public class Crossbow extends ItemImpl {

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;

  /** Field loadedBolt. */
  @Setter @Getter
  private CrossbowBolt loadedBolt = null;

  /** Constructor for Crossbow. */
  public Crossbow() {
    super();
  }

  // bow plus bolt if present.
  /**
   * Method getWeight.
   *
   * @return float
   */
  public float totalWeight() {
    float total = super.totalWeight();
    if (loadedBolt != null) {
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
    if (loadedBolt != null) {
      visitor.visit(loadedBolt);
    }
    // Let our super handle the rest.
    super.accept(visitor);
  }
}
