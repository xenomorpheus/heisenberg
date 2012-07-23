package au.net.hal9000.player.item;

public class Arrow extends Item {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructor
	public Arrow() {
		this("Arrow");
	}

	public Arrow(String pString) {
		super(pString);
	}

	// Feature
	/** {@inheritDoc} */
	@Override
	public boolean isSharp() {
		return true;
	}
}
