package au.net.hal9000.heisenberg.util;

import au.net.hal9000.heisenberg.units.Skill;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/** A Character Sheet of PC/NPC. */
public class CharacterSheet implements Serializable {

  private static final String ls = System.lineSeparator();

  // It's a good practice to declare a serialVersionUID
  private static final long serialVersionUID = 1L;

  @Getter @Setter @NonNull
  private String name = null;

  @Getter @Setter @NonNull
  private String description = null;

  /** How skilled in the chosen profession AKA PcClass. Multi-classing not supported. */
  @Getter
  private int level = 0;

  /** profession e.g. Soldier, Wizard etc. */
  @Getter @NonNull
  private PcClass pcClass = null;

  /** The species. Previously known as race. */
  @Getter @Setter @NonNull
  private String species = null;

  @Getter @Setter @NonNull
  private String gender = null;

  @Getter @Setter @NonNull
  private String size = null;

  @Getter @Setter @NonNull
  private Set<Skill> skills = new TreeSet<>();

  @Getter @Setter @NonNull
  private Set<String> recipes = new TreeSet<>();

  /** Field abilityScores. */
  @Getter @Setter @NonNull
  private Map<String, AbilityScore> abilityScores = new TreeMap<>();

  /** Constructor */
  public CharacterSheet() {
    super();
  }

  // Getters and Setters

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
   * Set the PC class
   *
   * @param pcClass the pcClass to set
   */
  public final void setPcClass(final PcClass pcClass) {
    this.pcClass = pcClass;
    // TODO we should recalculate the ability scores here.
    // We need to make sure that we preserve any ability scores that aren't
    // on the pcClass.
    if (pcClass != null) {
      for (String key : pcClass.getAbilityScores().keySet()) {
        if (!abilityScores.containsKey(key)) {
          abilityScores.put(key, pcClass.getAbilityScores().get(key));
        }
      }
    }
    // TODO other class based attributes.
  }

  // Skills
  /**
   * The Entity object has learnt a new Skill.
   *
   * @param skill The freshly learnt Skill.
   */
  public final void skillsAdd(final Skill skill) {
    skills.add(skill);
  }

  // Recipe objects

  /**
   * Add to the of Recipe objects this Entity object knows.
   *
   * @param recipeId a Recipe id
   */
  public void recipeAdd(String recipeId) {
    recipes.add(recipeId);
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
    abilityScores.put(abilityScore.getName(), abilityScore);
  }

  // Misc

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
      for (String key : abilityScores.keySet()) {
        AbilityScore abilityScore = abilityScores.get(key);
        AbilityScore pcClassAbility = pcClass.getAbilityScore(key);
        abilityScore.setValue(
            pcClassAbility.getValue() + (level * pcClassAbility.getMod()) + abilityScore.getMod());
      }
    }
  }

  /**
   * @return Plain text description of the object
   */
  public String detailedDescription() {
    StringBuilder text = new StringBuilder();

    text.append("Name: ");
    if (name == null) {
      text.append("<none>" + ls);
    } else {
      text.append(name + ls);
    }
    if (null != description) {
      text.append("Description: " + description + ls);
    }
    text.append("Level: " + level + ls);
    text.append("Species: " + species + ls);
    if (null != gender) {
      text.append("Gender: " + gender + ls);
    }
    if (null != size) {
      text.append("Size: " + size + ls);
    }
    final Set<Skill> skills = getSkills();
    if (null != skills && !skills.isEmpty()) {
      text.append("Skills:" + ls);
      for (Skill skill : skills) {
        text.append("  " + skill + ls);
      }
    }
    final Set<String> recipes = getRecipes();
    if (null != recipes && !recipes.isEmpty()) {
      text.append("Recipes:" + ls);
      for (String recipeId : recipes) {
        text.append("  " + recipeId + ls);
      }
    }
    if (null != abilityScores) {
      text.append("Abilities:" + ls);
      for (AbilityScore abilityScore : abilityScores.values()) {
        text.append("  " + abilityScore + ls);
      }
    }
    if (null != pcClass) {
      text.append("Class: " + pcClass.getId() + ls);
      text.append(pcClass.getDetailedDescription("    ") + ls);
    }
    return text.toString();
  }
}
