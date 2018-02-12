package au.net.hal9000.heisenberg.fifthed.spell;

import java.util.Set;

import au.net.hal9000.heisenberg.fifthed.characterClass.Spellcaster;
import au.net.hal9000.heisenberg.fifthed.combat.TimerRound;
import au.net.hal9000.heisenberg.fifthed.playerCharacter.PlayerCharacter;
import au.net.hal9000.heisenberg.fifthed.playerCharacter.PlayerCharacterCondition;

/**
 * The act of casting one single spell on one occasion.
 * 
 * @author bruins
 *
 */
public class Spellcast {

	private PlayerCharacter self;
	private Spellcaster spellcaster;
	private Spell spell;
	private PlayerCharacter target;
	private TimerRound timer;

	public Spellcast(Spellcaster spellcaster, Spell spell, PlayerCharacter target, TimerRound timer) {
		this.self = spellcaster.getPlayerCharacter();
		this.spellcaster = spellcaster;
		this.spell = spell;
		this.target = target;
		this.timer = timer;

	}

	// We separate the different tests so the spell can override any of them if
	// required.
	/**
	 * Is there sufficient time to perform this action?
	 * 
	 * @param arena
	 * @param target
	 * @return true iff enough time.
	 */
	private boolean isDurationValid() {
		return timer.isActionDurationAvailable(spell.getActionDuration());
	}

	/**
	 * Are we within range?
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
			int casterLevel = spellcaster.getClassLevel();
			// Use a new class - SpellEvocation
			spellRange = spell.getEffectRange(casterLevel);
		}
		return spellRange >= rangeToOpponent;
	}

	/**
	 * Check spell components
	 * 
	 * @return
	 */
	private boolean isComponentsValid() {
		Set<PlayerCharacterCondition> conditions = self.getConditions();
		for (SpellComponent component : spell.getSpellComponents()) {
			if (component == SpellComponent.MATERIAL)
				continue; // Material Components are assumed.
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
