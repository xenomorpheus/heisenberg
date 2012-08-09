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
	/** {@inheritDoc} */
	@Override
	public boolean isClothing() {
		return true;
	}

	// TODO remove when RM has replacement in Item
	/** {@inheritDoc} 
	 * @throws CloneNotSupportedException */
	@Override
	public Cloak clone() throws CloneNotSupportedException {
		return (Cloak)super.clone(new Cloak());
	}

}
