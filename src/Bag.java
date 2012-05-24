public class Bag extends ItemContainer {

	public Bag() {
		super("Bag");
		setDescription("A common cloth sack about 2 feet by 4 feet in size.");

	}

	public Bag(String pName) {
		super(pName);
		setDescription("A common cloth sack about 2 feet by 4 feet in size.");
	}
    // TODO add
	// Look for items that implement Sharp
	// if so, call destruct or damage ?
}
