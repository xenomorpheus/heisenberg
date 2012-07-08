package au.net.hal9000.dnd.item;

public class Cloak extends ItemImpl {
	public Cloak() {
		super("Cloak");
	}

	public Cloak(String pString) {
		super(pString);
	}

	// Features
	public boolean isClothing() {
		return true;
	}
}
