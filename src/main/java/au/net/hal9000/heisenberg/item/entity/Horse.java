package au.net.hal9000.heisenberg.item.entity;

import au.net.hal9000.heisenberg.item.api.HumanoidMount;
import jakarta.persistence.Entity;

/** A common horse. */
@Entity
public class Horse extends EntityItem implements HumanoidMount {

  /** serial version id. */
  private static final long serialVersionUID = 1L;

  /** Constructor. */
  public Horse() {
    super();
  }
}
