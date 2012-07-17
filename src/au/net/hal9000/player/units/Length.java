package au.net.hal9000.player.units;

import java.io.Serializable;

/** A representation of length.
 * 
 * @see		au.net.hal9000.player.units.Unit
 * @author bruins
 *
 */
public class Length extends Unit implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// TODO is this explicit constructor best practice?
	public Length(){
		// TODO is this explicit super() J.B.P.?
		super();
	}
	// TODO is this explicit constructor best practice?
	public Length(float f){
		// TODO is this explicit super() J.B.P.?
		super(f);
	}

	public Length clone() throws CloneNotSupportedException {
		// Make sure the cloning is deep, not shallow.
		// e.g. set the non-mutable, non-primitives
		return (Length) super.clone();
	}
}
