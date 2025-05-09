package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.api.LightSource;
import jakarta.persistence.Entity;

/** A simple tallow candle. */
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
    super();
    setDescription("a simple tallow candle");
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
      setVolumeBase(VOLUME_DEFAULT);
      setWeightBase(WEIGHT_DEFAULT);
    } else {
      throw new RuntimeException("Invalid Candle type=" + type);
    }
  }

  @Override
  public boolean lightWith(Object igniter) { // NO_UCD (test only)
    if (igniter instanceof FlintAndTinder) {
      setLit(true);
    }
    // OrbOfLight (or sub-class) won't lite it.
    // Candle (or sub-class) and MUST BE LIT.
    else if ((igniter instanceof Candle) && !(igniter instanceof OrbOfLight)) {
      Candle candle = (Candle) igniter;
      if (candle.isLit()) {
        setLit(true);
      }
    }
    // Note may already be lit.
    return isLit();
  }

  @Override
  public void extinguish() {
    setLit(false);
  }

  @Override
  public String detailedDescription() {
    StringBuilder string = new StringBuilder(super.detailedDescription() + System.lineSeparator());
    string.append("The " + getClass().getSimpleName());

    if (lit) {
      string.append(" is lit.");
    } else {
      string.append(" is extinguished.");
    }
    return string.toString();
  }
}
