package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.api.LightSource;
import jakarta.persistence.Entity;

/**
 * A simple tallow candle.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
public class Candle extends ItemImpl implements LightSource {

  /** serialisation version. */
  private static final long serialVersionUID = 1L;

  /** default weight. */
  static final float WEIGHT_DEFAULT = 0.02f;

  /** default volume. */
  static final float VOLUME_DEFAULT = 0.02f;

  /** true if candle is lit. */
  private boolean lit = false;

  // TODO time the burn by use of fuel or rounds.

  /** Constructor. */
  public Candle() {
    this("Candle");
  }

  /**
   * Constructor.
   *
   * @param name name to call the item.
   * @param description description of item.
   */
  Candle(final String name, final String description) {
    super(name, description);
    this.setType(1);
  }

  /**
   * Constructor.
   *
   * @param name name to call the item.
   */
  Candle(final String name) {
    this(name, "a simple tallow candle");
  }

  // Getters and Setters
  /**
   * Set the lit/unlit status of this torch.
   *
   * @param lit the lit/unlit status of this torch
   */
  public void setLit(boolean lit) {
    this.lit = lit;
  }

  /** {@inheritDoc} */
  @Override
  public boolean isLit() {
    return lit;
  }

  // Methods
  /**
   * Set the type of the Candle.
   *
   * @param type candle type.
   */
  public void setType(final int type) {
    if (1 == type) {
      this.setVolumeBase(VOLUME_DEFAULT);
      this.setWeightBase(WEIGHT_DEFAULT);
    } else {
      throw new RuntimeException("Invalid Candle type=" + type);
    }
  }

  /** {@inheritDoc} */
  @Override
  public boolean lightWith(Object ignighter) { // NO_UCD (test only)
    if (ignighter instanceof FlintAndTinder) {
      this.setLit(true);
    }
    // OrbOfLight (or sub-class) won't lite it.
    // Candle (or sub-class) and MUST BE LIT.
    else if ((ignighter instanceof Candle) && !(ignighter instanceof OrbOfLight)) {
      Candle candle = (Candle) ignighter;
      if (candle.isLit()) {
        this.setLit(true);
      }
    }
    // Note may already be lit.
    return isLit();
  }

  /** {@inheritDoc} */
  @Override
  public void extinguish() {
    this.setLit(false);
  }

  /** {@inheritDoc} * @return String */
  @Override
  public String detailedDescription() {
    StringBuilder string = new StringBuilder(super.detailedDescription());
    string.append(System.lineSeparator());
    string.append("The " + this.getClass().getSimpleName());

    if (lit) {
      string.append(" is lit.");
    } else {
      string.append(" is extinguished.");
    }
    return string.toString();
  }
}
