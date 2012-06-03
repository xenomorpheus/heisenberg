package au.net.hal9000.dnd.item;

public class Cloak extends Item {
	public Cloak() {
		super("Cloak");
	}

	public Cloak(String pString) {
		super(pString);
	}

	// Features
	public static boolean isClothing() {
		return true;
	}
}
