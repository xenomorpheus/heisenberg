package au.net.hal9000.heisenberg.fifthed.characterClass;

import au.net.hal9000.heisenberg.fifthed.combat.Action;
import au.net.hal9000.heisenberg.fifthed.combat.CombatArena;
import au.net.hal9000.heisenberg.fifthed.playercharacter.PlayerCharacter;

import java.util.Set;

public abstract class CharacterClass {
  private String name = null;
  private int classLevel = 0;
  private PlayerCharacter playerCharacter = null;

  public CharacterClass(String name, int level, PlayerCharacter playerCharacter) {
    super();
    this.name = name;
    this.classLevel = level;
    this.playerCharacter = playerCharacter;
    playerCharacter.classesAdd(this);
  }

  // Getters and Setters

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public CharacterClass setName(String name) {
    this.name = name;
    return this;
  }

  /**
   * @return the level
   */
  public int getClassLevel() {
    return classLevel;
  }

  /**
   * @param classLevel the level to set
   */
  public CharacterClass setClassLevel(int classLevel) {
    this.classLevel = classLevel;
    return this;
  }

  /** A link back the the PlayerCharacter from the Class */
  public CharacterClass setPlayerCharacter(PlayerCharacter playerCharacter) {
    this.playerCharacter = playerCharacter;
    return this;
  }

  public PlayerCharacter getPlayerCharacter() {
    return playerCharacter;
  }

  // Misc

  public String toString() {
    return getName();
  }

  /**
   * Detailed description.
   *
   * @return
   */
  public String details() {
    return details("");
  }

  /**
   * Detailed description. Each line may be given a prefix, e.g. for padding.
   *
   * @return
   */
  public String details(String prefix) {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("%sClass: %s (%d)%n", prefix, getName(), getClassLevel()));
    return sb.toString();
  }

  public abstract Set<Action> getActionsCombat(CombatArena arena);
}
