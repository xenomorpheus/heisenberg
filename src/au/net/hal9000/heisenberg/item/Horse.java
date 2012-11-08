package au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.property.HumanoidMount;

public class Horse extends Being implements HumanoidMount {
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

	protected String getRace() {
		return null;
	}
}
