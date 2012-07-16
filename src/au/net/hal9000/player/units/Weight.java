package au.net.hal9000.player.units;

public class Weight extends Unit implements Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Weight(){
		super();
	}
	public Weight(float f){
		super(f);
	}

	public Weight clone() throws CloneNotSupportedException {
		// Make sure the cloning is deep, not shallow.
		// e.g. set the non-mutable, non-primitives
		return (Weight) super.clone();
	}
}
