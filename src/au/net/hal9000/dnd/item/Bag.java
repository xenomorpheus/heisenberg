package au.net.hal9000.dnd.item;

import au.net.hal9000.dnd.item.exception.*;

public class Bag extends ItemContainer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Bag() {
		super("Bag");
		setDescription("A common cloth sack about 2 feet by 4 feet in size.");

	}

	public Bag(String pName) {
		super(pName);
		setDescription("A common cloth sack about 2 feet by 4 feet in size.");
	}

	// TODO finish rupture
	public void rupture() {
		// System.out.println("Ordinary rupture");
	}

	public void add(Item item) throws ExceptionTooHeavy, ExceptionTooBig,
			ExceptionInvalidType {
		// Look for sharp items. Wrapped sharp items are safe.
		if (item.isSharp()) {
			this.rupture();
			throw new ExceptionInvalidType("Sharp");
		}
		super.add(item);
	}

}
