package au.net.hal9000.heisenberg.fifthed.spell;

import au.net.hal9000.heisenberg.fifthed.ActionDuration;

/**
 * http://www.d20pfsrd.com/magic/all-spells/b/blade-lash/
 * 
 * Casting Time 1 standard action
Components V, S

EFFECT

Range touch
Target your melee weapon
Duration instantaneous
Saving Throw none; Spell Resistance no

DESCRIPTION

Your weapon elongates and becomes whip-like. As part of casting this spell, you can use this weapon to attempt a trip combat maneuver against one creature within 20 feet, and you gain a +10 bonus on your roll, after which the weapon returns to its previous form.
 * @author bruins
 *
 */
public class BladeLash extends Spell{

	public BladeLash()  {
		super();
		setName("Blade Lash").setRangeTouch(true);
		setCastingTime(ActionDuration.STANDARD);
		componentsAdd(SpellComponent.VERBAL);
		componentsAdd(SpellComponent.SOMATIC);
		setRangeTouch(true);
		setEffectDuration(0);
		setEffectSpellResistance(false);
	}

}
