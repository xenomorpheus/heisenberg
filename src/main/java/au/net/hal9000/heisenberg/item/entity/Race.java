package au.net.hal9000.heisenberg.item.entity;

import au.net.hal9000.heisenberg.util.AbilityScore;
import au.net.hal9000.heisenberg.util.PcClass;
import java.util.Map;
import java.util.TreeMap;

/**
 * A Player Character AKA PC. An Entity that may be controlled by a person playing the game.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
public abstract class Race extends EntityItem {

  /** serial version id. */
  private static final long serialVersionUID = 1L;

  /** How skilled in the chosen profession AKA PcClass. */
  private int level = 0;

  /** profession e.g. Soldier, Wizard etc. */
  private PcClass pcClass;

  /** dice to use in combat. */
  private int combatDice;

  /** dice to use in magic. */
  private int magicDice;

  /** dice to use in stealth. */
  private int stealthDice;

  /** dice to use in general. */
  private int generalDice;

  /** Field encumbrance. */
  private int encumbrance;

  /** Field health. */
  private int health;

  /** Field abilityScores. */
  private Map<String, AbilityScore> abilityScores;

  // Constructors

  /**
   * Constructor for Race.
   *
   * @param name String
   */
  protected Race(String name) {
    this(name, "");
  }

  /**
   * Constructor for Race.
   *
   * @param string String
   * @param description String
   */
  protected Race(String string, String description) {
    super(string, description);
  }

  /**
   * Give me a PC that is a {@link PcClass} (e.g. Warrior).
   *
   * @param name String
   * @param pPcClass PcClass
   */
  protected Race(String name, PcClass pcClass) {
    this(name);
    this.pcClass = pcClass;
    init();
  }

  /**
   * Give me a PC that is a {@link PcClass} (e.g. Warrior).
   *
   * @param name common name (e.g. Jo Smith) of the Race.
   * @param description appearance.
   * @param pcClass the profession {@link PcClass} (e.g. Warrior).
   */
  protected Race(String name, String description, PcClass pcClass) {
    this(name, description);
    this.pcClass = pcClass;
    init();
  }

  // Getters and Setters
  /** Get the level. @return the level */
  public final int getLevel() {
    return level;
  }

  /**
   * Set the level.
   *
   * @param level the level to set
   */
  public final void setLevel(final int level) {
    int oldLevel = this.level;
    if (level != oldLevel) {
      this.level = level;
      abilityScoresRecalculate();
    }
  }

  /**
   * Get the PC class,
   *
   * @return the pcClass
   */
  public final PcClass getPcClass() {
    return pcClass;
  }

  /**
   * Set the PC class
   *
   * @param pcClass the pcClass to set
   */
  public final void setPcClass(final PcClass pcClass) {
    this.pcClass = pcClass;
    if (null == pcClass) {
      clearClassBaseFields();
    } else {
      setBasicsFromPcClass();
    }
  }

  /** Get the combat dice. @return the combatDice */
  public final int getCombatDice() {
    return combatDice;
  }

  /** Set the Combat dice. @param combatDice the combatDice to set */
  public final void setCombatDice(final int combatDice) {
    this.combatDice = combatDice;
  }

  /** Get the magic dice. @return the magicDice */
  public final int getMagicDice() {
    return magicDice;
  }

  /**
   * @param magicDice the magicDice to set
   */
  public final void setMagicDice(int magicDice) {
    this.magicDice = magicDice;
  }

  /**
   * @return the stealthDice
   */
  public final int getStealthDice() {
    return stealthDice;
  }

  /**
   * @param stealthDice the stealthDice to set
   */
  public final void setStealthDice(final int stealthDice) {
    this.stealthDice = stealthDice;
  }

  /**
   * @return the generalDice
   */
  public final int getGeneralDice() {
    return generalDice;
  }

  /**
   * @param generalDice the generalDice to set
   */
  public final void setGeneralDice(final int generalDice) {
    this.generalDice = generalDice;
  }

  /**
   * @return the encumbrance.
   */
  public final int getEncumbrance() {
    return encumbrance;
  }

  /**
   * @param encumbrance the encumbrance to set.
   */
  public final void setEncumbrance(final int encumbrance) {
    this.encumbrance = encumbrance;
  }

  /**
   * @return the health.
   */
  public final int getHealth() {
    return health;
  }

  /**
   * @param health the health to set.
   */
  public final void setHealth(final int health) {
    this.health = health;
  }

  /**
   * @return the abilityScores.
   */
  public final Map<String, AbilityScore> getAbilityScores() {
    return abilityScores;
  }

  /**
   * @param abilityScores the abilityScores to set.
   */
  public final void setAbilityScores(Map<String, AbilityScore> abilityScores) {
    this.abilityScores = abilityScores;
  }

  /**
   * @param name name of AbilityScore
   * @return the AbilityScore object
   */
  public AbilityScore getAbilityScore(String name) {
    return abilityScores.get(name);
  }

  /**
   * @param abilityScore the AbilityScore to put.<br>
   *     Any existing AbilityScore with this name will be removed.
   */
  public void setAnAbilityScore(AbilityScore abilityScore) {
    if (null == abilityScores) {
      abilityScores = new TreeMap<String, AbilityScore>();
    }
    abilityScores.put(abilityScore.getName(), abilityScore);
  }

  // Misc

  /** Method init. */
  private void init() {
    if (null == pcClass) {
      clearClassBaseFields();
    } else {
      setBasicsFromPcClass();
      abilityScoresRecalculate();
    }
  }

  /** Warning: will reset all abilityScore objects. */
  private void setBasicsFromPcClass() {
    // dice
    combatDice = pcClass.getCombatDice();
    magicDice = pcClass.getMagicDice();
    stealthDice = pcClass.getStealthDice();
    generalDice = pcClass.getGeneralDice();
    // misc
    encumbrance = pcClass.getEncumbrance();
    setActionPoints(pcClass.getActionPoints());
    health = pcClass.getHealth();
    setMana(pcClass.getMana());

    abilityScoresEnsureExists();

    // TODO don't reach inside object's data structures - keySet()
    Map<String, AbilityScore> pcClassAbilityScores = pcClass.getAbilityScores();
    if (null != pcClassAbilityScores) {
      // TODO do we need to clear existing abilities
      // What about ability scores not from pcClass ? Add a unit test to
      // ensure they are preserved.
      for (String key : pcClassAbilityScores.keySet()) {
        AbilityScore abilityScore = new AbilityScore(key, 0, 0);
        abilityScores.put(key, abilityScore);
      }
      abilityScoresRecalculate();
    }
  }

  /** Ensure that the abilityScores. */
  private void abilityScoresEnsureExists() {
    if (null == abilityScores) {
      abilityScores = new TreeMap<String, AbilityScore>();
    }
  }

  /**
   * recalculate the AbilityScore objects. <br>
   * e.g. when a PC levels.<br>
   * Should be safe to call any time.
   */
  private void abilityScoresRecalculate() {
    // TODO we should be iterating over pcClass's ability key set.
    // We need to make sure that we preserve any ability scores that aren't
    // on the pcClass.
    if (null != pcClass) {
      if (null == abilityScores) {
        abilityScoresEnsureExists();
      }
      for (String key : abilityScores.keySet()) {
        AbilityScore abilityScore = abilityScores.get(key);
        AbilityScore pcClassAbility = pcClass.getAbilityScore(key);
        abilityScore.setValue(
            pcClassAbility.getValue() + (level * pcClassAbility.getMod()) + abilityScore.getMod());
      }
    }
  }

  /** Method clearClassBaseFields. */
  private void clearClassBaseFields() {

    // dice
    combatDice = 0;
    magicDice = 0;
    stealthDice = 0;
    generalDice = 0;
    // misc
    setActionPoints(0);
    setMana(0);
    encumbrance = 0;
    health = 0;
  }

  /**
   * @return Plain text description of the object
   */
  public String detailedDescription() {
    StringBuilder text = new StringBuilder();

    // Inherit description from super first.
    text.append(super.detailedDescription());
    // Only add properties in this class.

    text.append("Level: " + level);
    text.append(System.lineSeparator());
    text.append("Species: " + getSpeciesName());
    text.append(System.lineSeparator());

    if (null != pcClass) {
      text.append("Class: " + pcClass.getId());
      text.append(System.lineSeparator());
    }

    text.append("Combat Dice: D" + combatDice);
    text.append(System.lineSeparator());
    text.append("Magic Dice: D" + magicDice);
    text.append(System.lineSeparator());
    text.append("Stealth Dice: D" + stealthDice);
    text.append(System.lineSeparator());
    text.append("General Dice: D" + generalDice);
    text.append(System.lineSeparator());
    text.append("Health: " + health);
    text.append(System.lineSeparator());
    text.append("Encumbrance: " + encumbrance);
    text.append(System.lineSeparator());

    if (null != abilityScores) {
      text.append("Abilities:");
      text.append(System.lineSeparator());
      for (AbilityScore abilityScore : abilityScores.values()) {
        text.append("  " + abilityScore);
        text.append(System.lineSeparator());
      }
    }
    return text.toString();
  }

  /**
   * Get the name of the race.
   *
   * @return the name of the race.
   */
  public abstract String getSpeciesName();

  /**
   * Shallow copy properties from one object to another.
   *
   * @param pc source
   */
  public void setAllFrom(Race pc) {
    super.setAllFrom(pc);
    setLevel(pc.getLevel());
    setPcClass(pc.getPcClass()); // TODO ensure results are not linked
    setCombatDice(pc.getCombatDice());
    setMagicDice(pc.getMagicDice());
    setStealthDice(pc.getStealthDice());
    setGeneralDice(pc.getGeneralDice());
    setEncumbrance(pc.getEncumbrance());
    setHealth(pc.getHealth());
    setActionPoints(pc.getActionPoints());
    setMana(pc.getMana());
    setAbilityScores(pc.getAbilityScores()); // TODO ensure results are not
    // linked
    setRecipes(pc.getRecipes()); // TODO ensure results are not linked
    setSkills(pc.getSkills()); // TODO ensure results are not linked
  }
}
