package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.api.Sharp;
import jakarta.persistence.Entity;

/**
 * A common sword.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
public class Sword extends ItemImpl implements Sharp {

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;

  /** Constructor for Sword. */
  public Sword() {
    this("Sword");
  }

  /**
   * Constructor for Sword.
   *
   * @param string String
   */
  private Sword(final String string) {
    super(string);
  }
}
