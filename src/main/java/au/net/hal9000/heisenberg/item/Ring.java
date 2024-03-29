package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.units.Currency;
import jakarta.persistence.Entity;

/**
 * A ring is an Item except: <br>
 * Default description is "small metallic ring". <br>
 * Default value is 5gp ? Default weight ?
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
public class Ring extends ItemImpl {

  /** serial id. */
  private static final long serialVersionUID = 1L;

  /** default value. */
  private static final int DEFAULT_VALUE_GP = 5;

  /** default weight. */
  private static final float DEFAULT_WEIGHT = 0.02f;

  /** Constructor. */
  public Ring() {
    super("Ring");
    this.setDefaults();
  }

  /**
   * Constructor.
   *
   * @param name name of Item.
   */
  Ring(final String name) {
    super(name);
    this.setDefaults();
  }

  /** Set the default values on this ring. */
  private void setDefaults() {
    this.setDescription("small metalic ring");
    this.setValueBase(new Currency(0, DEFAULT_VALUE_GP, 0, 0));
    this.setWeightBase(DEFAULT_WEIGHT);
  }
}
