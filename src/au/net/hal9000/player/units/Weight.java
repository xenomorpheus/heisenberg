package au.net.hal9000.player.units;

import java.io.Serializable;

/** A representation of weight.
 * 
 * @author bruins
 *
 */
public class Weight extends Unit implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructors
	public Weight() {
		super();
	}

	public Weight(float f) {
		super(f);
	}

    // Methods
	// TODO is this required?
	/**
	 * Returns a clone of the current object.
	 * 
	 * @return Weight
	 * 
	 */
	public Weight clone() throws CloneNotSupportedException {
		// Make sure the cloning is deep, not shallow.
		// e.g. set the non-mutable, non-primitives
		return (Weight) super.clone();
	}
}
