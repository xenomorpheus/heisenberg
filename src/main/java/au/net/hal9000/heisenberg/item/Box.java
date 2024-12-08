package au.net.hal9000.heisenberg.item;

import jakarta.persistence.Entity;

/** A simple box container. */
@Entity
public class Box extends Location {

  /** serial version id. */
  private static final long serialVersionUID = 1L;

  /** Constructor. */
  public Box() {
    super();
  }
}
