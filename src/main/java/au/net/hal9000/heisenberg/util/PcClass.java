package au.net.hal9000.heisenberg.util;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * A Profession or Class that a PC can have e.g. Soldier, Wizard etc.
 * Consider each instance read-only after creation.
 * Consider each object a refernce data object, which may be shared by many CharacterSheet objects.
 */
public class PcClass implements Serializable, Comparable<PcClass> {

  // It's a good practice to declare a serialVersionUID
  private static final long serialVersionUID = 1L;

  private static final String ls = System.lineSeparator();

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
  private Set<AbilityScore> abilityScores = new TreeSet<>();

  /** Constructor for PcClass. */
  public PcClass() {
    super();
  }

  // Comparable
  @Override
  public int compareTo(PcClass other) {
    return id.compareTo(other.getId());
  }

  // misc

  /**
   * Get the ability scores.
   *
   * @param name name of AbilityScore
   * @return the AbilityScore object
   */
  public AbilityScore getAbilityScore(String name) {
    return abilityScores.stream().filter(a -> a.getName().equals(name)).findFirst().orElse(null);
  }

  /**
   * Set the ability score.
   *
   * @param abilityScore the AbilityScore to put.<br>
   *     Any existing AbilityScore with this name will be removed.
   */
  public void setAbilityScore(@NonNull AbilityScore abilityScore) {
    abilityScores.removeIf(a -> a.getName().equals(abilityScore.getName()));
    abilityScores.add(abilityScore);
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
    StringBuilder text = new StringBuilder(prefix).append("Id: ").append(id).append(ls);
    text.append(prefix).append("Combat Dice: d").append(combatDice).append(ls);
    text.append(prefix).append("Magic Dice: d").append(magicDice).append(ls);
    text.append(prefix).append("Stealth Dice: d").append(stealthDice).append(ls);
    text.append(prefix).append("General Dice: d").append(generalDice).append(ls);
    text.append(prefix).append("Action Points: ").append(actionPoints).append(ls);
    text.append(prefix).append("Health: ").append(health).append(ls);
    text.append(prefix).append("Mana: ").append(mana).append(ls);
    text.append(prefix).append("Species Allow: ").append(speciesAllow).append(ls);
    text.append(prefix).append("Gender Allow: ").append(genderAllow).append(ls);
    text.append(prefix).append("Size Allow:").append(sizeAllow).append(ls);
    text.append(prefix).append("Encumbrance: ").append(encumbrance).append(ls);

    if (abilityScores != null) {
      text.append(prefix).append("Class Abilities:").append(ls);
      for (var score : abilityScores) {
        text.append(prefix).append("  ").append(score).append(ls);
      }
    }
    return text.toString();
  }
}
