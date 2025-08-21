package au.net.hal9000.heisenberg.item.being;

import jakarta.persistence.Entity;

/** A short humanoid. */
@Entity
public class Halfling extends Humanoid {

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;

  /** Constructor for Halfling. */
  public Halfling() {
    super();
  }
}
