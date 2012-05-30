package au.net.hal9000.dnd.item;

import au.net.hal9000.dnd.item.property.Sharp;

public class Bag extends ItemContainer {

	public Bag() {
		super("Bag");
		setDescription("A common cloth sack about 2 feet by 4 feet in size.");

	}

	public Bag(String pName) {
		super(pName);
		setDescription("A common cloth sack about 2 feet by 4 feet in size.");
	}
	// TODO
	public void rupture() {
		//
	}

	public void add(Item item) throws ExceptionTooHeavy, ExceptionTooBig, ExceptionInvalidType {
		// Look for sharp items. Wrapped sharp items are safe.
		if (item.implementsInterface(Sharp.class)) {
			this.rupture();
		}
		else{
  	       super.add(item);
		}
	}
}
