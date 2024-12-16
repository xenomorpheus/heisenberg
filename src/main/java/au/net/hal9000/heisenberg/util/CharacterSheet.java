package au.net.hal9000.heisenberg.util;

import au.net.hal9000.heisenberg.units.Skill;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/** A Character Sheet of PC/NPC. */
public class CharacterSheet implements Serializable {

  private static final String ls = System.lineSeparator();

  // It's a good practice to declare a serialVersionUID
  private static final long serialVersionUID = 1L;

  private String name = null;

  private String description = null;

  /** How skilled in the chosen profession AKA PcClass. Multi-classing not supported. */
  private int level = 0;

  /** profession e.g. Soldier, Wizard etc. */
  private PcClass pcClass = null;

  /** The species. Previously known as race. */
  private String species = null;

  private String gender = null;

  private String size = null;

  private Set<Skill> skills = new TreeSet<>();

  private Set<String> recipes = new TreeSet<>();

  /** Field abilityScores. */
  private Map<String, AbilityScore> abilityScores = new TreeMap<>();

  /** Constructor */
  public CharacterSheet() {
    super();
  }

  // Getters and Setters

  public String getName() {
    return name;
  }

  public void setName(String name) {
    if (null == name) {
      this.name = new String();
    }
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    if (null == description) {
      this.description = new String();
    }
    this.description = description;
  }

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
  }

  /**
   * Get the name of the species.
   *
   * @return the name of the species.
   */
  public String getSpecies() {
    return species;
  }

  public void setSpecies(String species) {
    if (null == species) {
      this.species = new String();
    }
    this.species = species;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    if (null == gender) {
      this.gender = new String();
    }
    this.gender = gender;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  // Skills
  /**
   * Get the Skill objects.
   *
   * @return a set of Skill objects
   */
  public final Set<Skill> getSkills() {
    return skills;
  }

  /**
   * Set the skills of this entity.
   *
   * @param skills skills to set.
   */
  public final void setSkills(final Set<Skill> skills) {
    this.skills = skills;
  }

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
   * Get the Recipe objects this Entity object knows.
   *
   * @return the set of Recipe objects
   */
  public Set<String> getRecipes() {
    return recipes;
  }

  /**
   * Set the Recipe objects this Entity object knows.
   *
   * @param recipes the set of Recipe objects
   */
  public void setRecipes(Set<String> recipes) {
    this.recipes = recipes;
  }

  /**
   * Add to the of Recipe objects this Entity object knows.
   *
   * @param recipeId a Recipe id
   */
  public void recipeAdd(String recipeId) {
    recipes.add(recipeId);
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
    if (null == name) {
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
