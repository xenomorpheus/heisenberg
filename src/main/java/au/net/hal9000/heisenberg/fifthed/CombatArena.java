package au.net.hal9000.heisenberg.fifthed;

import java.util.List;

public class CombatArena {
	private PlayerCharacter self = null;
	private List<PlayerCharacter> allies = null;
	private List<PlayerCharacter> opponents = null;

	public CombatArena() {
		super();
	}

	/**
	 * @return the self
	 */
	public PlayerCharacter getSelf() {
		return self;
	}

	/**
	 * @param self
	 *            the self to set
	 */
	public CombatArena setSelf(PlayerCharacter self) {
		this.self = self;
		return this;
	}

	/**
	 * @return the alies
	 */
	public List<PlayerCharacter> getAllies() {
		return allies;
	}

	/**
	 * @param alies
	 *            the alies to set
	 */
	public CombatArena setAllies(List<PlayerCharacter> alies) {
		this.allies = alies;
		return this;
	}

	/**
	 * @return the enemies
	 */
	public List<PlayerCharacter> getOpponents() {
		return opponents;
	}

	/**
	 * @param opponents
	 *            the enemies to set
	 */
	public CombatArena setOpponents(List<PlayerCharacter> opponents) {
		this.opponents = opponents;
		return this;
	}

}
