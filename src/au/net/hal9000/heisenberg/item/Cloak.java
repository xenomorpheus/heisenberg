package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.property.Clothing;
public class Cloak extends Item implements Clothing {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Cloak() {
		this("Cloak");
	}

	public Cloak(String pString) {
		super(pString);
	}

}
