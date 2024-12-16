package au.net.hal9000.heisenberg.util;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

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
  private String id = null;

  /** combat dice. */
  private int combatDice = 0;

  /** magic dice. */
  private int magicDice = 0;

  /** stealth dice. */
  private int stealthDice = 0;

  /** general dice. */
  private int generalDice = 0;

  // misc
  /** Field actionPoints. */
  private int actionPoints = 0;

  /** Field health. */
  private int health = 0;

  /** Field mana. */
  private int mana = 0;

  /** Field speciesAllow. */
  private String speciesAllow;

  /** Field genderAllow. */
  private String genderAllow = null;

  /** Field sizeAllow. */
  private String sizeAllow = null;

  /** Field encumbrance. */
  private int encumbrance = 0;

  /** Field abilityScores. */
  private Map<String, AbilityScore> abilityScores = new TreeMap<String, AbilityScore>();

  /** Constructor for PcClass. */
  public PcClass() {
    super();
  }

  /**
   * Get id.
   *
   * @return the id
   */
  public final String getId() {
    return id;
  }

  /**
   * Set the id.
   *
   * @param id the id to set
   */
  public final void setId(String id) {
    this.id = id;
  }

  /**
   * Get the combat dice.
   *
   * @return the combatDice
   */
  public final int getCombatDice() {
    return combatDice;
  }

  /**
   * Set the combatDice.
   *
   * @param combatDice the combatDice to set
   */
  public final void setCombatDice(int combatDice) {
    this.combatDice = combatDice;
  }

  /**
   * Get the MagicDice.
   *
   * @return the magicDice
   */
  public final int getMagicDice() {
    return magicDice;
  }

  /**
   * Set the magic dice.
   *
   * @param magicDice the magicDice to set
   */
  public final void setMagicDice(int magicDice) {
    this.magicDice = magicDice;
  }

  /**
   * Get the stealth dice.
   *
   * @return the stealthDice
   */
  public final int getStealthDice() {
    return stealthDice;
  }

  /**
   * Set the stealth Dice.
   *
   * @param stealthDice the stealthDice to set
   */
  public final void setStealthDice(int stealthDice) {
    this.stealthDice = stealthDice;
  }

  /**
   * Get the general dice.
   *
   * @return the generalDice
   */
  public final int getGeneralDice() {
    return generalDice;
  }

  /**
   * Set the general dice.
   *
   * @param generalDice the generalDice to set
   */
  public final void setGeneralDice(int generalDice) {
    this.generalDice = generalDice;
  }

  /**
   * Get the action points.
   *
   * @return the actionPoints
   */
  public final int getActionPoints() {
    return actionPoints;
  }

  /**
   * Set the action points.
   *
   * @param actionPoints the actionPoints to set
   */
  public final void setActionPoints(int actionPoints) {
    this.actionPoints = actionPoints;
  }

  /**
   * Get the health.
   *
   * @return the health
   */
  public final int getHealth() {
    return health;
  }

  /**
   * Set the health.
   *
   * @param health the health to set
   */
  public final void setHealth(int health) {
    this.health = health;
  }

  /**
   * Get the mana.
   *
   * @return the mana
   */
  public final int getMana() {
    return mana;
  }

  /**
   * Set the mana.
   *
   * @param mana the mana to set
   */
  public final void setMana(int mana) {
    this.mana = mana;
  }

  /**
   * Get the species allow.
   *
   * @return the raceAllow
   */
  public final String getSpeciesAllow() {
    return speciesAllow;
  }

  /**
   * Set the species allow.
   *
   * @param speciesAllow the speciesAllow to set
   */
  public final void setSpeciesAllow(String speciesAllow) {
    this.speciesAllow = speciesAllow;
  }

  /**
   * Get the gender allow.
   *
   * @return the genderAllow
   */
  public final String getGenderAllow() {
    return genderAllow;
  }

  /**
   * Set the gender allow.
   *
   * @param genderAllow the genderAllow to set
   */
  public final void setGenderAllow(String genderAllow) {
    this.genderAllow = genderAllow;
  }

  /**
   * Get the size allow.
   *
   * @return the sizeAllow
   */
  public final String getSizeAllow() {
    return sizeAllow;
  }

  /**
   * Set the size allow.
   *
   * @param sizeAllow the sizeAllow to set
   */
  public final void setSizeAllow(String sizeAllow) {
    this.sizeAllow = sizeAllow;
  }

  /**
   * Get the encumbrance.
   *
   * @return the encumbrance
   */
  public final int getEncumbrance() {
    return encumbrance;
  }

  /**
   * Set the encumbrance.
   *
   * @param encumbrance the encumbrance to set
   */
  public final void setEncumbrance(int encumbrance) {
    this.encumbrance = encumbrance;
  }

  /**
   * Get ability scores.
   *
   * @return the abilityScores
   */
  public final Map<String, AbilityScore> getAbilityScores() {
    return abilityScores;
  }

  /**
   * Set the ability scores.
   *
   * @param abilityScores the abilityScores to set
   */
  public final void setAbilityScores(Map<String, AbilityScore> abilityScores) {
    this.abilityScores = abilityScores;
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
