package au.net.hal9000.heisenberg.util;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO Refactor this into two classes:<br>
 * 1) Holder of configuration of a PC Class<br>
 * 2) The actual character sheet class.
 *
 * <p>Ideas: New class CharacterSheet will hold a PC's stats and maybe items.<br>
 * CharacterSheet will hold the following for each ability score:<br>
 * 1) (no extension) - The current value Equals:<br>
 * CharacterClass ability base + (level * CharacterClass ability inc) + Mod <br>
 * 2) "Mod" - Any modifiers the character has chosen.
 */
public class PcClass implements Serializable, Comparable<PcClass> {

  // It's a good practice to declare a serialVersionUID
  private static final long serialVersionUID = 1L;

  // e.g. "Soldier"
  /** Field id. */
  @Getter @Setter
  private String id = null;

  /** combat dice. */
  @Getter @Setter
  private int combatDice = 0;

  /** magic dice. */
  @Getter @Setter
  private int magicDice = 0;

  /** stealth dice. */
  @Getter @Setter
  private int stealthDice = 0;

  /** general dice. */
  @Getter @Setter
  private int generalDice = 0;

  // misc
  /** Field actionPoints. */
  @Getter @Setter
  private int actionPoints = 0;

  /** Field health. */
  @Getter @Setter
  private int health = 0;

  /** Field mana. */
  @Getter @Setter
  private int mana = 0;

  /** Field speciesAllow. */
  @Getter @Setter
  private String speciesAllow;

  /** Field genderAllow. */
  @Getter @Setter
  private String genderAllow = null;

  /** Field sizeAllow. */
  @Getter @Setter
  private String sizeAllow = null;

  /** Field encumbrance. */
  @Getter @Setter
  private int encumbrance = 0;

  /** Field abilityScores. */
  @Getter @Setter
  private Map<String, AbilityScore> abilityScores = new TreeMap<String, AbilityScore>();

  /** Constructor for PcClass. */
  public PcClass() {
    super();
  }

  // misc

  /**
   * Get the ability scores.
   *
   * @param name name of AbilityScore
   * @return the AbilityScore object
   */
  public AbilityScore getAbilityScore(String name) {
    return abilityScores.get(name);
  }

  /**
   * Set the ability score.
   *
   * @param abilityScore the AbilityScore to put.<br>
   *     Any existing AbilityScore with this name will be removed.
   */
  public void setAbilityScore(AbilityScore abilityScore) {
    abilityScores.put(abilityScore.getName(), abilityScore);
  }

  /**
   * The short string.
   *
   * @return the short string.
   */
  public String toString() {
    return id;
  }

  /**
   * Get the detailed description.
   *
   * @return Plain text description of the object
   */
  public String getDetailedDescription() {
    return getDetailedDescription("");
  }

  /**
   * Get the detailed description.
   *
   * @return Plain text description of the object
   */
  public String getDetailedDescription(String prefix) {
    StringBuilder text = new StringBuilder(prefix + "Id: " + id + System.lineSeparator());
    text.append(prefix + "Combat Dice: D" + combatDice + System.lineSeparator());
    text.append(prefix + "Magic Dice: D" + magicDice + System.lineSeparator());
    text.append(prefix + "Stealth Dice: D" + stealthDice + System.lineSeparator());
    text.append(prefix + "General Dice: D" + generalDice + System.lineSeparator());
    text.append(prefix + "Action Points: " + actionPoints + System.lineSeparator());
    text.append(prefix + "Health: " + health + System.lineSeparator());
    text.append(prefix + "Mana: " + mana + System.lineSeparator());
    text.append(prefix + "Species Allow: " + speciesAllow + System.lineSeparator());
    text.append(prefix + "Gender Allow: " + genderAllow + System.lineSeparator());
    text.append(prefix + "Size Allow:" + sizeAllow + System.lineSeparator());
    text.append(prefix + "Encumbrance: " + encumbrance + System.lineSeparator());

    if (null != abilityScores) {
      text.append(prefix + "Abilities:" + System.lineSeparator());
      Iterator<AbilityScore> itr = abilityScores.values().iterator();
      while (itr.hasNext()) {
        text.append(prefix + "  " + itr.next() + System.lineSeparator());
      }
    }
    return text.toString();
  }

  @Override
  public int compareTo(PcClass other) {
    return id.compareTo(other.getId());
  }
}
