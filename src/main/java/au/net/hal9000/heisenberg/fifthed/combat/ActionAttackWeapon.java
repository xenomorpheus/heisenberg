package au.net.hal9000.heisenberg.fifthed.combat;

import au.net.hal9000.heisenberg.fifthed.item.Weapon;
import au.net.hal9000.heisenberg.fifthed.playercharacter.PlayerCharacter;

/** Represents an action where a weapon is used to attack an opponent. */
public class ActionAttackWeapon extends Action {

  private Weapon weapon;
  private PlayerCharacter opponent;

  /**
   * Constructs an ActionAttackWeapon with the specified weapon and opponent.
   *
   * @param weapon the weapon used in the attack
   * @param opponent the opponent being attacked
   */
  public ActionAttackWeapon(Weapon weapon, PlayerCharacter opponent) {
    super();
    this.weapon = weapon;
    this.opponent = opponent;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append(super.toString());
    sb.append(": target '")
        .append(opponent.getName())
        .append("' with weapon ")
        .append(weapon.getSummary());
    return sb.toString();
  }
}
