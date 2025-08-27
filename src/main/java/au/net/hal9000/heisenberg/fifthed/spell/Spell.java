package au.net.hal9000.heisenberg.fifthed.spell;

import au.net.hal9000.heisenberg.fifthed.combat.ActionDuration;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;

public abstract class Spell {

  @Getter
  private String name = null;

  @Getter
  private boolean rangeTouch = false;

  @Getter
  private Set<SpellComponent> spellComponents = new HashSet<SpellComponent>();

  @Getter
  private ActionDuration actionDuration = null;

  @Getter
  private SpellSavingThrow spellSavingThrow;

  @Getter
  private int effectDurationSeconds = 0;

  @Getter
  private EffectArea effectAreaType = null;

  @Getter
  private int effectAreaFeet = 0;

  @Getter
  private int effectRangeBase = 0;

  @Getter
  private int effectRangeLevelMultiplier = 0;

  @Getter
  private boolean effectSpellResistance = false;

  @Getter
  private String description = null;

  @Getter
  private String url = null;

  // Setters and Getters. Builder pattern.

  /**
   * Sets the name of the spell.
   *
   * @param name the name to set
   * @return the updated spell instance
   */
  public Spell setName(String name) {
    this.name = name;
    return this;
  }

  protected Spell setRangeTouch(boolean rangeTouch) {
    this.rangeTouch = rangeTouch;
    return this;
  }

  /**
   * Set the action duration.
   *
   * @param actionDuration the actionDuration to set
   */
  public Spell setCastingTime(ActionDuration actionDuration) {
    this.actionDuration = actionDuration;
    return this;
  }

  public Spell setEffectSavingThrowsAdd(SpellSavingThrow spellSavingThrow) {
    this.spellSavingThrow = spellSavingThrow;
    return this;
  }

  public Spell setEffectDuration(int effectDurationSeconds) {
    this.effectDurationSeconds = effectDurationSeconds;
    return this;
  }

  public Spell setEffectAreaType(EffectArea effectAreaType) {
    this.effectAreaType = effectAreaType;
    return this;
  }

  public Spell setEffectAreaFeet(int effectAreaFeet) {
    this.effectAreaFeet = effectAreaFeet;
    return this;
  }

  public Spell setEffectRangeBase(int effectRangeBase) {
    this.effectRangeBase = effectRangeBase;
    return this;
  }

  public Spell setEffectRangeLevelMultiplier(int effectRangeLevelMultiplier) {
    this.effectRangeLevelMultiplier = effectRangeLevelMultiplier;
    return this;
  }

  /**
   * Set the spell components.
   *
   * @param spellComponents the spellComponents to set
   * @return this
   */
  public Spell setSpellComponents(Set<SpellComponent> spellComponents) {
    this.spellComponents = spellComponents;
    return this;
  }

  /**
   * @param spellSavingThrow the spellSavingThrow to set
   */
  public Spell setSpellSavingThrow(SpellSavingThrow spellSavingThrow) {
    this.spellSavingThrow = spellSavingThrow;
    return this;
  }

  /**
   * @param effectDurationSeconds the effectDurationSeconds to set
   */
  public Spell setEffectDurationSeconds(int effectDurationSeconds) {
    this.effectDurationSeconds = effectDurationSeconds;
    return this;
  }

  /**
   * @param description the description to set
   */
  public Spell setDescription(String description) {
    this.description = description;
    return this;
  }

  /**
   * Sets the URL associated with the spell.
   *
   * @param newUrl the URL to set
   * @return the updated spell instance
   */
  public Spell setUrl(String newUrl) {
    url = newUrl;
    return this;
  }

  /**
   * @param actionDuration the actionDuration to set
   */
  public Spell setActionDuration(ActionDuration actionDuration) {
    this.actionDuration = actionDuration;
    return this;
  }

  /**
   * @param effectSpellResistance the effectSpellResistance to set
   */
  public Spell setEffectSpellResistance(boolean effectSpellResistance) {
    this.effectSpellResistance = effectSpellResistance;
    return this;
  }

  public Spell componentsAdd(SpellComponent spellComponent) {
    this.spellComponents.add(spellComponent);
    return this;
  }

  // Misc

  public String toString() {
    return getName();
  }

  public int getEffectRange(int casterLevel) {
    return effectRangeBase + effectRangeLevelMultiplier * casterLevel;
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
    sb.append(String.format("%s  Components: %s%n", prefix, spellComponents));
    if (spellSavingThrow != null) {
      sb.append(String.format("%s  SavingThrow: %s%n", prefix, spellSavingThrow));
    }
    if (actionDuration != null) {
      sb.append(String.format("%s  ActionDuration: %s%n", prefix, actionDuration));
    }

    if (rangeTouch) {
      sb.append(String.format("%s  RangeTouch: Yes%n", prefix));
    }
    if (url != null) {
      sb.append(String.format("%s  URL: %s%n", prefix, url));
    }
    if (description != null) {
      sb.append(String.format("%s  Description: %s%n", prefix, description));
    }
    // Effect
    sb.append(String.format("%s  Effect: %n", prefix));
    if (effectDurationSeconds == 0) {
      sb.append(String.format("%s      Duration: Instantaneous%n", prefix));
    } else {
      sb.append(String.format("%s      Duration: %d seconds%n", prefix, effectDurationSeconds));
    }
    if (effectAreaType != null) {
      sb.append(String.format("%s      Area Type: %s%n", prefix, effectAreaType));
    }
    sb.append(String.format("%s      Area: %d ft%n", prefix, effectAreaFeet));
    sb.append(String.format("%s      Range Base: %d ft%n", prefix, effectRangeBase));
    sb.append(
        String.format("%s      Range Level Mult: %d ft%n", prefix, effectRangeLevelMultiplier));
    sb.append(String.format("%s      Spell Resistance: %s%n", prefix, effectSpellResistance));

    return sb.toString();
  }
}
