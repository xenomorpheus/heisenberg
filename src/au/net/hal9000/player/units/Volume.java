package au.net.hal9000.player.units;

import java.io.Serializable;

public class Volume extends Unit implements Serializable, Cloneable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Volume(){
		super();
	}
	public Volume(float f){
		super(f);
	}

	public Volume clone() throws CloneNotSupportedException {
		// Make sure the cloning is deep, not shallow.
		// e.g. set the non-mutable, non-primitives
		return (Volume) super.clone();
	}
}
