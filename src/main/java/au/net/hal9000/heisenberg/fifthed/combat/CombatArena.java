package au.net.hal9000.heisenberg.fifthed.combat;

import au.net.hal9000.heisenberg.fifthed.playercharacter.PlayerCharacter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/** Represents a combat arena where battles take place. */
public final class CombatArena {
  private PlayerCharacter playerCharacter = null;
  private TimerRound timerRound = new TimerRound();
  private List<PlayerCharacter> allies = new ArrayList<PlayerCharacter>();
  private List<PlayerCharacter> opponents = new ArrayList<PlayerCharacter>();

  /**
   * Constructs a CombatArena with the specified player character.
   *
   * @param playerCharacter the player character participating in the combat arena
   */
  public CombatArena(PlayerCharacter playerCharacter) {
    super();
    this.playerCharacter = playerCharacter;
  }

  public PlayerCharacter getPlayerCharacter() {
    return playerCharacter;
  }

  /**
   * @return the allies
   */
  public List<PlayerCharacter> getAllies() {
    return allies;
  }

  /**
   * @param allies the companions
   */
  public CombatArena setAllies(List<PlayerCharacter> allies) {
    this.allies = allies;
    return this;
  }

  /**
   * @return the enemies
   */
  public List<PlayerCharacter> getOpponents() {
    return opponents;
  }

  /**
   * @param opponents the enemies to set
   */
  public CombatArena setOpponents(List<PlayerCharacter> opponents) {
    this.opponents = opponents;
    return this;
  }

  /**
   * Adds an opponent to the combat arena.
   *
   * @param opponent the player character to add as an opponent
   * @return the updated CombatArena instance
   */
  public CombatArena opponentAdd(PlayerCharacter opponent) {
    opponents.add(opponent);
    return this;
  }

  /** Return the set of valid Action objects that the PlayerCharacter may preform. */
  public Set<Action> getActionsCombat() {
    return playerCharacter.getActionsCombat(this);
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("Self: %s%n", playerCharacter.getSummary()));
    if (!allies.isEmpty()) {
      for (PlayerCharacter ally : allies) {
        sb.append(String.format("Ally: %s%n", ally.getSummary()));
      }
    }
    if (!opponents.isEmpty()) {
      for (PlayerCharacter opponent : opponents) {
        sb.append(
            String.format(
                "Opponent: %s, range %.0f ft%n",
                opponent.getSummary(), playerCharacter.distance(opponent)));
      }
    }
    return sb.toString();
  }

  public TimerRound getTimerRound() {
    return timerRound;
  }
}
