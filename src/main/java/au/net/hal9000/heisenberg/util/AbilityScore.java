package au.net.hal9000.heisenberg.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 * AbilityScore properties.<br>
 * Int: value (including modifier)<br>
 */
public class AbilityScore implements Comparable<AbilityScore> {
  /** name. */
  @Setter @Getter
  private String name;

  /**
   * value <br>
   * a) For PcClass this is the base prior to modification for levels<br>
   * b) For CharacterSheet this includes the all modifications.
   */
  @Setter @Getter
  private int value = 0;

  /**
   * modifier<br>
   * a) For PcClass this is the value to add on per level<br>
   * b) For CharacterSheet this is the customisation of the ability.
   */
  @Setter @Getter
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
    if (modString != null) {
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

  // misc
  /**
   * String form of value with optional modifier e.g. 3/1 or 3.
   *
   * @return Value with optional mod e.g. 3/1 or 3
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

  @Override
  public String toString() {
    return name + ": " + valueOptionalMod();
  }

  @Override
  public int compareTo(AbilityScore other) {
    // TODO Auto-generated method stub
    return name.compareTo(other.getName());
  }
}
