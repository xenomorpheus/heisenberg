package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.api.HumanoidArmClothing;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.units.Currency;
import jakarta.persistence.Entity;

/**
 * A ring is an Item except: <br>
 * Default description is "small metallic ring". <br>
 * Default value is 5gp ? Default weight ?
 */
@Entity
public class Ring extends ItemImpl implements HumanoidArmClothing {

  /** serial id. */
  private static final long serialVersionUID = 1L;

  /** default value. */
  private static final int DEFAULT_VALUE_GP = 5;

  /** default weight. */
  private static final float DEFAULT_WEIGHT = 0.02f;

  /** Constructor. */
  public Ring() {
    super();
    ItemProperty.setClothing(this, true);
    setDescription("small metalic ring");
    setValueBase(new Currency(0, DEFAULT_VALUE_GP, 0, 0));
    setWeightBase(DEFAULT_WEIGHT);
  }
}
