package au.net.hal9000.player.item;

public class Horse extends Entity {
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

	// Feature
	public boolean isHumanoidMount() {
		return true;
	}

}
