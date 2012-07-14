package au.net.hal9000.dnd.units;

/*
 * The basis for Weight, Volume, Length.
 * 
 * May be extended to hold both
 * S.I and Imperial measurements.
 * 
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.Math;

public class Unit implements Serializable, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final float MARGIN = 0.0001F;

	private float value = 0.0F;

	// Constructor
	public Unit() {
		super();
	}

	public Unit(float pValue) {
		super();
		this.value = pValue;
	}

	// static methods
	public static boolean null_safe_compare(Unit unit, Unit unitOther) {
		if (unit == null) {
			if (unitOther == null) {
				return true;
			}
			return false;
		}

		if (unitOther == null) {
			return false;
		}

		return unit.equals(unitOther);
	}

	// instance methods
	// Getters and Setters
	public float getValue() {
		return value;
	}

	public void setValue(float pValue) {
		this.value = pValue;
	}

	// Methods
	public boolean equals(Unit other) {
		if (Math.abs(this.value - other.getValue()) >= MARGIN)
			return false;
		return true;
	}

	public boolean equals(float other) {
		if (Math.abs(this.value - other) >= MARGIN)
			return false;
		return true;
	}

	public void add(Unit other) {
		this.value += other.getValue();
	}

	public void add(float other) {
		this.value += other;
	}

	public void subtract(Unit other) {
		this.value -= other.getValue();
	}

	public void subtract(float f) {
		this.value -= f;
	}

	public void multiply(float multiplier) {
		this.value *= multiplier;
	}

	public Unit divide(float divisor) {
		if (divisor == 0) {
			throw new IllegalArgumentException();
		}
		return new Unit(this.value /= divisor);
	}

	public int compare(Unit other) {
		if (this.equals(other))
			return 0;
		return (this.value < other.getValue()) ? -1 : 1;
	}

	public int compare(float other) {
		if (this.equals(other))
			return 0;
		return (this.value < other) ? -1 : 1;
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
	}

	private void readObject(ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		// our "pseudo-constructor"
		in.defaultReadObject();
		// now we are a "live" object again, so let's run rebuild and start
	}

	protected Unit clone() throws CloneNotSupportedException {
		// Make sure the cloning is deep, not shallow.
		// e.g. set the non-mutable, non-primitives
		return (Unit) super.clone();
	}
}
