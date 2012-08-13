package au.net.hal9000.player.item;

import au.net.hal9000.player.item.property.Sharp;

public class Sword extends Item implements Sharp{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Sword() {
		this("Sword");
	}

	public Sword(final String pString) {
		super(pString);
	}

}
