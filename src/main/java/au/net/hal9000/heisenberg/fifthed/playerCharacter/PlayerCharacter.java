package au.net.hal9000.heisenberg.fifthed.playercharacter;

import au.net.hal9000.heisenberg.fifthed.characterClass.CharacterClass;
import au.net.hal9000.heisenberg.fifthed.combat.Action;
import au.net.hal9000.heisenberg.fifthed.combat.CombatArena;
import au.net.hal9000.heisenberg.fifthed.item.Item;
import au.net.hal9000.heisenberg.units.Position;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class PlayerCharacter {
  private String name = null;
  private List<CharacterClass> characterClasses = new ArrayList<CharacterClass>();
  private Set<PlayerCharacterCondition> conditions = new HashSet<PlayerCharacterCondition>();
  // TODO Position reference object from which the location is measured. Eg. Room
  // or, Combat area.
  private Position location = new Position();

  // TODO assign specific slots for Item instances.
  // For now we'll assume an Item is in a valid slot.
  private Set<Item> equipped = new HashSet<Item>();

  // Inventory other than equipped Item instances.
  // private List<Item> unEquipped = new ArrayList<Item>();
  private CreatureSize creatureSize;

  public PlayerCharacter(String name) {
    super();
    setName(name);
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
  public PlayerCharacter setName(String name) {
    this.name = name;
    return this;
  }

  /**
   * @return the characterClasses
   */
  public List<CharacterClass> getCharacterClasses() {
    return characterClasses;
  }

  /**
   * @param characterClasses the characterClasses to set
   */
  public PlayerCharacter setCharacterClasses(List<CharacterClass> characterClasses) {
    this.characterClasses = characterClasses;
    return this;
  }

  /**
   * @return the location
   */
  public Position getLocation() {
    return location;
  }

  /**
   * @param location the location to set
   */
  public PlayerCharacter setLocation(Position location) {
    this.location = location;
    return this;
  }

  /**
   * @return the conditions
   */
  public Set<PlayerCharacterCondition> getConditions() {
    return conditions;
  }

  /** set the Condition */
  public PlayerCharacter setConditions(Set<PlayerCharacterCondition> conditionsNew) {
    conditions.clear();
    conditions.addAll(conditionsNew);
    return this;
  }

  /** set the Position */
  public PlayerCharacter setPosition(Position locationNew) {
    location.set(locationNew);
    return this;
  }

  /**
   * @return the equipped
   */
  public Set<Item> getEquipped() {
    return equipped;
  }

  /**
   * @param equipped the equipped to set
   */
  public PlayerCharacter setEquipped(Set<Item> equipped) {
    this.equipped = equipped;
    return this;
  }

  /**
   * @return the creatureSize
   */
  public CreatureSize getCreatureSize() {
    return creatureSize;
  }

  /**
   * @param creatureSize the creatureSize to set
   */
  public void setCreatureSize(CreatureSize creatureSize) {
    this.creatureSize = creatureSize;
  }

  // Misc
  @Override
  public String toString() {
    return getName();
  }

  /**
   * A detailed description.
   *
   * @return
   */
  public String details() {
    return details("");
  }

  /**
   * A detailed description.
   *
   * @return
   */
  public String details(String prefix) {
    StringBuilder sb = new StringBuilder(10);
    sb.append(String.format("%sName: %s%n", prefix, name));
    sb.append(String.format("%sRace: %s%n", prefix, getRaceName()));
    sb.append(String.format("%sLevel: %d%n", prefix, getLevel()));
    if ((characterClasses != null) && (!characterClasses.isEmpty())) {
      for (CharacterClass characterClass : characterClasses) {
        sb.append(characterClass.details(prefix + "  "));
      }
    }
    if ((conditions != null) && (!conditions.isEmpty())) {
      for (PlayerCharacterCondition playerCondition : conditions) {
        sb.append(String.format("%sCondition: %s%n", prefix, playerCondition.toString()));
      }
    }
    sb.append(String.format("%sLocation (relative): %s%n", prefix, location.toString()));
    if ((equipped != null) && (!equipped.isEmpty())) {
      sb.append(String.format("%sEquiped:%n", prefix));
      for (Item item : equipped) {
        sb.append(String.format("%s  %s%n", prefix, item.getSummary()));
      }
    }
    return sb.toString();
  }

  public abstract String getRaceName();

  public double distance(PlayerCharacter other) {
    return location.distance(other.location);
  }

  public Integer getLevel() {
    Integer level = 0;
    if (characterClasses != null) {
      for (CharacterClass characterClass : characterClasses) {
        level += characterClass.getClassLevel();
      }
    }
    return level;
  }

  /**
   * @return a set of actions that the player character may choose only one to perform.
   */
  public Set<Action> getActionsCombat(CombatArena arena) {
    Set<Action> actions = new HashSet<Action>();
    if ((characterClasses != null) && (!characterClasses.isEmpty())) {
      for (CharacterClass characterClass : characterClasses) {
        actions.addAll(characterClass.getActionsCombat(arena));
      }
    }
    return actions;
  }

  /**
   * Add this Class to the PC.
   *
   * @param characterClass
   * @return
   */
  public PlayerCharacter classesAdd(CharacterClass characterClass) {
    characterClasses.add(characterClass);
    return this;
  }

  /** Add this condition to the PC. e.g. deaf, blind */
  public void conditionAdd(PlayerCharacterCondition condition) {
    conditions.add(condition);
  }

  public void equippedAdd(Item item) {
    equipped.add(item);
  }

  public String getSummary() {
    StringBuilder sb = new StringBuilder();
    sb.append(getName()).append(", ").append(getRaceName());
    if (characterClasses.size() != 1) {
      sb.append(", level ").append(getLevel());
    }
    for (CharacterClass characterClass : characterClasses) {
      sb.append(", ")
          .append(characterClass.getName())
          .append("(")
          .append(characterClass.getClassLevel())
          .append(")");
    }
    return sb.toString();
  }

  public double getNaturalReach() {
    return creatureSize.getNaturalReach();
  }
}
