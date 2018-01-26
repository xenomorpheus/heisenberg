package au.net.hal9000.heisenberg.fifthed.CharacterClass;

import java.util.HashSet;
import java.util.Set;

import au.net.hal9000.heisenberg.fifthed.spell.Spell;

public class Magus extends CharacterClass {

	private Set<Spell> spells = new HashSet<Spell>();

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

	// Misc
	public String toString() {
		return getName();
	}
	/**
	 * A detailed description.
	 * 
	 * @return
	 */
	public String details() {
		return details("");
	}

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

}
