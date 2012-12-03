package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.property.ItemProperty;


public class Backpack extends Bag  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Backpack() {
		this("Backpack");
	}

	public Backpack(final String pString) {
		super(pString);
		ItemProperty.setClothing(this, true);
	}

}
