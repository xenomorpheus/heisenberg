package au.net.hal9000.dnd.item;

public class Cloak extends ItemImpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Cloak() {
		super("Cloak");
	}

	public Cloak(String pString) {
		super(pString);
	}

	// Features
	public boolean isClothing() {
		return true;
	}
}
