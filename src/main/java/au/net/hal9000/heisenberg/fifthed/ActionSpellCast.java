package au.net.hal9000.heisenberg.fifthed;

import au.net.hal9000.heisenberg.fifthed.spell.Spell;

public class ActionSpellCast extends Action {

	private Spell spell;
	private PlayerCharacter playerCharacter = null;

	public ActionSpellCast(Spell spell) {
		this.spell = spell;
	}
	public ActionSpellCast(Spell spell, PlayerCharacter playerCharacter) {
		this(spell);
		this.playerCharacter  = playerCharacter;
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
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		if (playerCharacter != null) {
			sb.append(": target '").append(playerCharacter.getName()).append("' with ");
		}
		else {
			sb.append(' ');
		}
		sb.append(spell.getName());
		return sb.toString();

	}
}
