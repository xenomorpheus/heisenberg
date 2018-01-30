package au.net.hal9000.heisenberg.fifthed.spell;

import au.net.hal9000.heisenberg.fifthed.ActionDuration;

/*
 * http://www.d20pfsrd.com/magic/all-spells/f/fireball/
 * Casting Time 1 standard action
Components V, S, M (a ball of bat guano and sulfur)

EFFECT

Range long (400 ft. + 40 ft./level)
Area 20-ft.-radius spread
Duration instantaneous
Saving Throw Reflex half; Spell Resistance yes

 */
public class Fireball extends Spell {

	public Fireball() {
		super();
		setName("Fireball");
		setCastingTime(ActionDuration.STANDARD);
		componentsAdd(SpellComponent.VERBAL);
		componentsAdd(SpellComponent.SOMATIC);
		componentsAdd(SpellComponent.MATERIAL);
		setEffectRangeBase(400);
		setEffectRangeLevelMultiplier(40);
		setEffectAreaType(EffectArea.RADIUS);
		setEffectAreaFeet(20);
		setEffectDuration(0);
		setEffectSavingThrowsAdd(SpellSavingThrows.REFLEX_HALF);
		setEffectSpellResistance(true);
	}

}
