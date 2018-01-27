package au.net.hal9000.heisenberg.fifthed;

import java.util.List;

public class CombatArena {
	private PlayerCharacter self = null;
	private List<PlayerCharacter> alies = null;
	private List<PlayerCharacter> enemies = null;
	
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
	 * @param self the self to set
	 */
	public CombatArena setSelf(PlayerCharacter self) {
		this.self = self;
		return this;
	}

	/**
	 * @return the alies
	 */
	public List<PlayerCharacter> getAlies() {
		return alies;
	}

	/**
	 * @param alies the alies to set
	 */
	public CombatArena setAlies(List<PlayerCharacter> alies) {
		this.alies = alies;
		return this;
	}

	/**
	 * @return the enemies
	 */
	public List<PlayerCharacter> getEnemies() {
		return enemies;
	}

	/**
	 * @param enemies the enemies to set
	 */
	public CombatArena setEnemies(List<PlayerCharacter> enemies) {
		this.enemies = enemies;
		return this;
	}



}
