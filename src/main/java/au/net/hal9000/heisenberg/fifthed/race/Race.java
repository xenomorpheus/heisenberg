package au.net.hal9000.heisenberg.fifthed.race;

import java.util.List;

import au.net.hal9000.heisenberg.fifthed.Action;
import au.net.hal9000.heisenberg.fifthed.CombatArena;
import au.net.hal9000.heisenberg.fifthed.TimerRound;

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
	public Race setName(String name) {
		this.name = name;
		return this;
	}

	// Misc
	public String toString() {
		return getName();
	}

	/** work out what actions may be performed in this amount of time */
	public abstract List<Action> getActions(CombatArena arena, TimerRound timer);
}
