package au.net.hal9000.heisenberg.util;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * AbilityScore properties:<br>
 * Int: value (including modifier)<br>
 * 
 * @author bruins
 * 
 */
public class AbilityScore implements Comparable<AbilityScore> {
	/** name */
	String name;
	/**
	 * value <br>
	 * a) For PcClass this is the base prior to modification for levels<br>
	 * b) For CharacterSheet this includes the all modifications.
	 */
	int value = 0;
	/**
	 * modifier<br>
	 * a) For PcClass this is the value to add on per level<br>
	 * b) For CharacterSheet this is the customisation of the ability.
	 */
	int mod = 0;

	public AbilityScore(String pName, int pValue, int pMod) {
		name = pName;
		value = pValue;
		mod = pMod;
	}

	public AbilityScore(String pName, String mixed) {

		name = pName;
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
			if (modString.startsWith("+"))
				modString = modString.substring(1);
			mod = Integer.parseInt(modString);
		}

		// Value
		String valueString = matcher.group(1);
		if (valueString.startsWith("+"))
			valueString = valueString.substring(1);
		value = Integer.parseInt(valueString);
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the value
	 */
	public final int getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public final void setValue(int value) {
		this.value = value;
	}

	/**
	 * @return the mod
	 */
	public final int getMod() {
		return mod;
	}

	/**
	 * @param mod
	 *            the mod to set
	 */
	public final void setMod(int mod) {
		this.mod = mod;
	}

	// misc
    /**
     * @return Value with optional mod. e.g. 3/1 or 3
     */
    public String valueOptionalMod() {
        String string = "" + value;
        if (mod != 0) {
            if (mod > 0) {
                string += "/+" + mod;
            } else {
                string += "/" + mod;
            }
        }
        return string;
    }
	

	public String toString() {
		return name + ": " + valueOptionalMod();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(AbilityScore other) {
		// TODO Auto-generated method stub
		return name.compareTo(other.getName());
	}

}
