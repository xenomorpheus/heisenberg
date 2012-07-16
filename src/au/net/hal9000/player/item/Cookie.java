package au.net.hal9000.player.item;

public class Cookie extends ItemImpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Cookie() {
		super("Cookie");
	}

	public Cookie(String pName) {
		super(pName);
	}

	public Cookie(String pName, String pDescription) {
		super(pName, pDescription);
	}

	// Feature
	public static boolean isHumanFood() {
		return true;
	}

	// Methods
}
