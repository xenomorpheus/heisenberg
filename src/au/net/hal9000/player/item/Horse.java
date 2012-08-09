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
	
	// TODO remove when RM has replacement in Item
	/** {@inheritDoc} 
	 * @throws CloneNotSupportedException */
	@Override
	public Item clone() throws CloneNotSupportedException{
         Horse h = new Horse();
         return h.clone(h);
	}

	// Feature
	public boolean isHumanoidMount() {
		return true;
	}

}
