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
	public Item clone(Item item) throws CloneNotSupportedException {
		return super.clone(item);
	}

}
