package au.net.hal9000.heisenberg.fifthed.combat;

import au.net.hal9000.heisenberg.fifthed.playercharacter.PlayerCharacter;
import au.net.hal9000.heisenberg.fifthed.spell.Spell;
import lombok.Getter;
import lombok.Setter;

/** Represents an action where a spell is cast in the combat system. */
public class ActionSpellCast extends Action {

  @Setter
  @Getter
  private Spell spell;
 
  private PlayerCharacter playerCharacter = null;

  /**
   * Constructs an ActionSpellCast with the specified spell.
   *
   * @param spell the spell to be cast
   */
  public ActionSpellCast(Spell spell) {
    this.spell = spell;
  }

  /**
   * Constructs an ActionSpellCast with the specified spell and player character.
   *
   * @param spell the spell to be cast
   * @param playerCharacter the player character casting the spell
   */
  public ActionSpellCast(Spell spell, PlayerCharacter playerCharacter) {
    this(spell);
    this.playerCharacter = playerCharacter;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString());
    if (playerCharacter != null) {
      sb.append(": target '").append(playerCharacter.getName()).append("' with ");
    } else {
      sb.append(' ');
    }
    sb.append(spell.getName());
    return sb.toString();
  }
}
