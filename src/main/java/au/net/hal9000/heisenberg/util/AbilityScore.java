package au.net.hal9000.heisenberg.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * AbilityScore properties.<br>
 * Int: value (including modifier)<br>
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class AbilityScore implements Comparable<AbilityScore> {
  /** name. */
  private String name;

  /**
   * value <br>
   * a) For PcClass this is the base prior to modification for levels<br>
   * b) For CharacterSheet this includes the all modifications.
   */
  private int value = 0;

  /**
   * modifier<br>
   * a) For PcClass this is the value to add on per level<br>
   * b) For CharacterSheet this is the customisation of the ability.
   */
  private int mod = 0;

  /**
   * Constructor.
   *
   * @param name name of ability.
   * @param value value of ability.
   * @param mod modifier.
   */
  public AbilityScore(String name, int value, int mod) {
    this.name = name;
    this.value = value;
    this.mod = mod;
  }

  /**
   * Constructor.
   *
   * @param name name of ability.
   * @param mixed combined value and modifier.
   */
  AbilityScore(String name, String mixed) {

    this.name = name;
    // integer with optional "/" and second integer.
    // each integer may be prefixed with a + or - sign.

    Pattern pattern = Pattern.compile("([+-]?\\d+)(?:/([+-]?\\d+))?");
    Matcher matcher = pattern.matcher(mixed);
    if (!matcher.find()) {
      throw new IllegalArgumentException("Invalid AbilityScore: " + mixed);
    }

    // Integer.parseInt is Brain-Dead! Can't handle "+" prefix.

    // Modifier
    String modString = matcher.group(2);
    if (null != modString) {
      if (modString.length() > 0 && '+' == modString.charAt(0)) {
        modString = modString.substring(1);
      }
      mod = Integer.parseInt(modString);
    }

    // Value
    String valueString = matcher.group(1);
    if (valueString.length() > 0 && '+' == valueString.charAt(0)) {
      valueString = valueString.substring(1);
    }
    value = Integer.parseInt(valueString);
  }

  /**
   * Name.
   *
   * @return the name
   */
  public final String getName() {
    return name;
  }

  /**
   * Set name.
   *
   * @param name the name to set
   */
  public final void setName(String name) {
    this.name = name;
  }

  /**
   * Get the value.
   *
   * @return the value
   */
  public final int getValue() {
    return value;
  }

  /**
   * Set the value.
   *
   * @param value the value to set
   */
  public final void setValue(int value) {
    this.value = value;
  }

  /**
   * Get the modifier.
   *
   * @return the mod
   */
  public final int getMod() {
    return mod;
  }

  /**
   * Set the modifier.
   *
   * @param mod the mod to set
   */
  public final void setMod(int mod) {
    this.mod = mod;
  }

  // misc
  /**
   * String form of value with optional modifier e.g. 3/1 or 3.
   *
   * @return Value with optional mod. e.g. 3/1 or 3
   */
  public String valueOptionalMod() {
    String description = "" + value;
    if (0 != mod) {
      if (mod > 0) {
        description += "/+" + mod;
      } else {
        description += "/" + mod;
      }
    }
    return description;
  }

  /**
   * {@inheritDoc}
   *
   * @return String
   */
  @Override
  public String toString() {
    return name + ": " + valueOptionalMod();
  }

  /**
   * {@inheritDoc}
   *
   * @param other AbilityScore
   * @return int
   */
  @Override
  public int compareTo(AbilityScore other) {
    // TODO Auto-generated method stub
    return name.compareTo(other.getName());
  }
}
