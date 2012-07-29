package au.net.hal9000.player.item;

public class Arrow extends Item {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private static final float defaultVolumeBase = 1; // TODO arrow volume default
	// Constructor
	public Arrow() {
		this("Arrow");
	}

	public Arrow(String pString) {
		super(pString);
		this.setVolumeBase(defaultVolumeBase);
	}

	// Feature
	/** {@inheritDoc} */
	@Override
	public boolean isSharp() {
		return true;
	}
}
