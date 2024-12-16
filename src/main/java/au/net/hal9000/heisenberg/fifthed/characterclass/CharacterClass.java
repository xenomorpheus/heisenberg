package au.net.hal9000.heisenberg.fifthed.characterclass;

import au.net.hal9000.heisenberg.fifthed.combat.Action;
import au.net.hal9000.heisenberg.fifthed.combat.CombatArena;
import java.util.Set;

/**
 * The gaming classes that a PC may have e.g. Wizard at third level. A PlayerCharacter may have
 * multi-classed which means multiple CharacterClass objects.
 */
public abstract class CharacterClass {
  private int classLevel = 0;

  public CharacterClass() {
    super();
  }

  // Getters and Setters

  /**
   * @return the level
   */
  public int getClassLevel() {
    return classLevel;
  }

  /**
   * @param classLevel the level to set
   */
  public void setClassLevel(int classLevel) {
    this.classLevel = classLevel;
  }

  // Misc

  public String toString() {
    return getClass().getSimpleName();
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
    sb.append(
        String.format("%sClass: %s (%d)%n", prefix, getClass().getSimpleName(), getClassLevel()));
    return sb.toString();
  }

  /* Return a set of valid Action objects the PlayerCharacter may perform using the capabilities of this CharacterClass may perform
   * in the area. The assumption is that the caller is the PlayerCharacter.
   */
  public abstract Set<Action> getActionsCombat(CombatArena arena);
}
