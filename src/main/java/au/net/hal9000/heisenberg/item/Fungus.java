package au.net.hal9000.heisenberg.item;

// Persistence
import jakarta.persistence.Entity;

/** A Fungus. */
@Entity
public class Fungus extends ItemImpl {

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;

  /** Constructor for Fungus. */
  public Fungus() {
    super();
  }
}
