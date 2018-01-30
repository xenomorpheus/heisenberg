package au.net.hal9000.heisenberg.fifthed;

import au.net.hal9000.heisenberg.fifthed.spell.Spell;

public class ActionSpellCast extends Action {

	private Spell spell;

	public ActionSpellCast(Spell spell) {
		this.spell = spell;
	}

	// Getters and Setters
	/**
	 * @return the spell
	 */
	public Spell getSpell() {
		return spell;
	}

	/**
	 * @param spell
	 *            the spell to set
	 */
	public void setSpell(Spell spell) {
		this.spell = spell;
	}

	@Override
	public String toString() {
		return String.format("%s - %s", super.toString(), spell.getName());
	}
}
