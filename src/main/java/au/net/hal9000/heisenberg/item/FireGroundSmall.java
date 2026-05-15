package au.net.hal9000.heisenberg.item;

import jakarta.persistence.Entity;

/** A small ground fire. */
@Entity
public class FireGroundSmall extends ItemImpl {

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;

  /** Constructor for FireGroundSmall. */
  public FireGroundSmall() {
    super();
    setDescription("a small ground fire");
  }
}
