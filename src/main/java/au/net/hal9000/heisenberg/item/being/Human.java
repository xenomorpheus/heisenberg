package au.net.hal9000.heisenberg.item.being;

import jakarta.persistence.Entity;

/** A common human. */
@Entity
public class Human extends Humanoid {

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;

  /** Constructor for Human. */
  public Human() {
    super();
  }
}
