package au.net.hal9000.player.item;

public class Sword extends Item {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Sword() {
		this("Sword");
	}

	public Sword(String pString) {
		super(pString);
	}

	/** {@inheritDoc} */
	@Override
	public boolean isSharp() {
		return true;
	}

}
