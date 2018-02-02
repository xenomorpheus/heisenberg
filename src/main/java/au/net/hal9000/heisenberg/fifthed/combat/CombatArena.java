package au.net.hal9000.heisenberg.fifthed.combat;

import java.util.ArrayList;
import java.util.List;

import au.net.hal9000.heisenberg.fifthed.combat.TimerRound;
import au.net.hal9000.heisenberg.fifthed.playerCharacter.PlayerCharacter;

public class CombatArena {
	private PlayerCharacter self;
	private TimerRound timerRound;
	private List<PlayerCharacter> allies = new ArrayList<PlayerCharacter>();
	private List<PlayerCharacter> opponents = new ArrayList<PlayerCharacter>();

	public CombatArena(PlayerCharacter self, TimerRound timerRound) {
		super();
		this.self = self;
		this.timerRound = timerRound;
	}

	public CombatArena(PlayerCharacter self) {
		this(self, new TimerRound());
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

	public CombatArena opponentAdd(PlayerCharacter opponent) {
		opponents.add(opponent);
		return this;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Self: %s%n",self.getSummary()));
		if (!allies.isEmpty()) {
			for (PlayerCharacter pc: allies) {
				sb.append(String.format("Ally: %s%n",pc.getSummary()));
			}
		}
		if (!opponents.isEmpty()) {
			for (PlayerCharacter pc: opponents) {
				sb.append(String.format("Opponent: %s, range %.0f ft%n",pc.getSummary(),self.distance(pc)));
			}
		}
		return sb.toString();
	}

	public TimerRound getTimerRound() {
		return timerRound;
	}
}
