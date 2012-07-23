package au.net.hal9000.player.item;

public class Cloak extends Item {
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

	// Features
	@Override
	public boolean isClothing() {
		return true;
	}

}
