package au.net.hal9000.heisenberg.fifthed.race;

import java.util.Set;

import au.net.hal9000.heisenberg.fifthed.Action;
import au.net.hal9000.heisenberg.fifthed.ActionDuration;
import au.net.hal9000.heisenberg.fifthed.CharacterClass.CharacterClass;

public abstract class Race {

	String name = null;

	public Race() {
		super();
	}

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
	public void setName(String name) {
		this.name = name;
	}

	// Misc
	public String toString() {
		return getName();
	}

	Set<Action> getActions(CharacterClass characterClass, ActionDuration actionDuration) {
		return null;
	}
}
