package au.net.hal9000.heisenberg.fifthed.spell;

import au.net.hal9000.heisenberg.fifthed.characterclass.Spellcaster;
import au.net.hal9000.heisenberg.fifthed.combat.TimerRound;
import au.net.hal9000.heisenberg.fifthed.playercharacter.PlayerCharacter;
import au.net.hal9000.heisenberg.fifthed.playercharacter.PlayerCharacterCondition;
import java.util.Set;

/** The single casting of a specific spell. */
public class Casting {

  private PlayerCharacter self;

  /** e.g. Wizard(3) The Class not the PC. */
  private Spellcaster spellCasterClass;

  private Spell spell;
  private PlayerCharacter target;
  private TimerRound timer;

  public Casting(
      PlayerCharacter self,
      Spellcaster spellCasterClass,
      Spell spell,
      PlayerCharacter target,
      TimerRound timer) {
    this.self = self;
    this.spellCasterClass = spellCasterClass;
    this.spell = spell;
    this.target = target;
    this.timer = timer;
  }

  // We separate the different tests so the spell can override any of them if
  // required.
  /**
   * Determine if there is sufficient time to perform this action.
   *
   * @param arena
   * @param target
   * @return true iff enough time.
   */
  private boolean isDurationValid() {
    return timer.isActionDurationAvailable(spell.getActionDuration());
  }

  /**
   * Determine if we are within range.
   *
   * @param arena
   * @param target
   * @return true iff within range.
   */
  private boolean isRangeValid() {
    double rangeToOpponent = self.distance(target);
    double spellRange = 0;
    if (spell.isRangeTouch()) {
      spellRange = self.getNaturalReach();
    } else {
      int casterLevel = spellCasterClass.getClassLevel();
      // Use a new class - SpellEvocation
      spellRange = spell.getEffectRange(casterLevel);
    }
    return spellRange >= rangeToOpponent;
  }

  /**
   * Check spell components.
   *
   * @return
   */
  private boolean isComponentsValid() {
    Set<PlayerCharacterCondition> conditions = self.getConditions();
    for (SpellComponent component : spell.getSpellComponents()) {
      if (component == SpellComponent.MATERIAL) {
        continue; // Material Components are assumed.
      }
      if (component == SpellComponent.SOMATIC) {
        // At least one free hand
        // Not bound condition, sleeping, etc.

      }
      if (component == SpellComponent.SOMATIC) {
        // TODO more
        if (conditions.contains(PlayerCharacterCondition.HELPLESS)) {
          return false;
        }
      }
      // TODO no silence spell
    }
    return true;
  }

  public boolean isActionValid() {
    return isDurationValid() && isRangeValid() && isComponentsValid();
    // TODO check cover
  }
}
