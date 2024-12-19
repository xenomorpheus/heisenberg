package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.api.HumanoidArmClothing;
import au.net.hal9000.heisenberg.item.property.ItemProperty;
import jakarta.persistence.Entity;

/**
 * Shield.
 *
 * <p>1) Reduces the chance that a attack will land.<br>
 * 2) Reduces the effect of an attack.<br>
 * 3) How can a shield get damaged?<br>
 * 4) Will there be armour types ? Brace, shield, body, magical ...<br>
 * TODO perhaps create a protection Interface.
 */
@Entity
public class Shield extends ItemImpl implements HumanoidArmClothing {

  // ToHitModifier toHitModifier = new ToHitModifier();
  // DamageModifier damageModifier = new DamageModifier();

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;

  // Constructor(s)
  /** Constructor for Shield. */
  public Shield() {
    super();
    ItemProperty.setClothing(this);
  }
}
