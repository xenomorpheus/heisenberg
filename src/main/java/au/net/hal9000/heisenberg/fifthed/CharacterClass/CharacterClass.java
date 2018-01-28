package au.net.hal9000.heisenberg.fifthed.CharacterClass;

import java.util.List;

import au.net.hal9000.heisenberg.fifthed.Action;
import au.net.hal9000.heisenberg.fifthed.CombatArena;
import au.net.hal9000.heisenberg.fifthed.TimerRound;

public abstract class CharacterClass {
	String name = null;
	int level = 0;

	// Getters and Setters

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public CharacterClass setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public CharacterClass setLevel(int level) {
		this.level = level;
		return this;
	}

	// Misc
	
	public String toString() {
		return getName();
	}

	/**
	 * Detailed description.
	 * @return
	 */
	public String details() {
		return details("");
	}

	/**
	 * Detailed description. Each line may be given a prefix, e.g. for padding.
	 * @return
	 */
	public String details(String prefix) {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%sClass: %s%n", prefix, getName()));
		sb.append(String.format("%sLevel: %d%n", prefix, getLevel()));
		return sb.toString();
	}

	public abstract List<Action> getActionsCombat(CombatArena arena, TimerRound timer);
}
