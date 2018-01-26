package au.net.hal9000.heisenberg.fifthed.spell;

public abstract class Spell {
	private String name = null;
	private boolean rangeTouch = false;

	// Setters and Getters

	/**
	 * @param name
	 *            the name to set
	 */
	public Spell setName(String name) {
		this.name = name;
		return this;
	}

	public String getName() {
		return name;
	}

	protected Spell setRangeTouch(boolean rangeTouch) {
		this.rangeTouch = rangeTouch;
		return this;
	}

	/**
	 * @return the rangeTouch
	 */
	public boolean isRangeTouch() {
		return rangeTouch;
	}

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
		if (rangeTouch) {
			sb.append(String.format("%sRangeTouch: Yes%n", prefix));
		}
		return sb.toString();
	}

}
