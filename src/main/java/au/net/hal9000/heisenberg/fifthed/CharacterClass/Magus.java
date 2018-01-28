package au.net.hal9000.heisenberg.fifthed.CharacterClass;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import au.net.hal9000.heisenberg.fifthed.Action;
import au.net.hal9000.heisenberg.fifthed.ActionDuration;
import au.net.hal9000.heisenberg.fifthed.ActionSpellCast;
import au.net.hal9000.heisenberg.fifthed.CombatArena;
import au.net.hal9000.heisenberg.fifthed.TimerRound;
import au.net.hal9000.heisenberg.fifthed.spell.Spell;

public class Magus extends Fighter {

	private Set<Spell> spells = new HashSet<Spell>();
	private Set<MagusArcana> magusArcana = new HashSet<MagusArcana>();

	public Magus() {
		super();
		setName("Magus");
	}

	// Setters and Getters
	public Magus setSpells(Set<Spell> spells) {
		this.spells = spells;
		return this;
	}

	/**
	 * @return the spells
	 */
	public Set<Spell> getSpells() {
		return spells;
	}

	/**
	 * @return the magusArcana
	 */
	public Set<MagusArcana> getMagusArcana() {
		return magusArcana;
	}

	/**
	 * @param magusArcana
	 *            the magusArcana to set
	 */
	public Magus setMagusArcana(Set<MagusArcana> magusArcana) {
		this.magusArcana = magusArcana;
		return this;
	}

	// Misc
	/**
	 * A detailed description.
	 * 
	 * @return
	 */
	public String details(String prefix) {
		StringBuilder sb = new StringBuilder(10);
		sb.append(super.details(prefix));
		if ((spells == null) || (spells.isEmpty())) {
			sb.append(String.format("%sSpells: None%n", prefix));
		} else {
			sb.append(String.format("%sSpells:%n", prefix));
			for (Spell spell : spells) {
				sb.append(String.format("%s  %s%n", prefix, spell.getName()));
			}
		}
		return sb.toString();
	}

	@Override
	/** work out what actions may be performed in this amount of time */
	public List<Action> getActionsCombat(CombatArena arena, TimerRound timer) {
		List<Action> actions = new ArrayList<Action>();
		for (Spell spell : spells) {
			ActionDuration actionDuration = spell.getActionDuration();
			if (timer.isActionDurationAvailable(actionDuration)) {
				actions.add(new ActionSpellCast(spell));
			}
		}

		if (timer.isActionDurationAvailable(ActionDuration.FREE)) {
			// TODO actions.add(new ActionFree());
		}
		// TODO Free Actions, etc.

		actions.addAll(super.getActionsCombat(arena, timer));
		return actions;
	}

}
