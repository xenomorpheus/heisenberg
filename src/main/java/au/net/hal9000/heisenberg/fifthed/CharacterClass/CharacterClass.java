package au.net.hal9000.heisenberg.fifthed.CharacterClass;

import java.util.Set;

import au.net.hal9000.heisenberg.fifthed.Action;
import au.net.hal9000.heisenberg.fifthed.ActionDuration;

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
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Class: %s%n", getName()));
		sb.append(String.format("Level: %d%n", getLevel()));
		return sb.toString();
	}

	Set<Action> getActions(CharacterClass characterClass, ActionDuration actionDuration) {
		return null;
	}
}
