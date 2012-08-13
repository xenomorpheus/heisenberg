package au.net.hal9000.player.item;

import au.net.hal9000.player.item.property.HumanoidMount;

public class Horse extends Entity implements HumanoidMount {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Horse() {
		super("Horse");
	}

	public Horse(String string) {
		super(string);
	}
}
