package au.net.hal9000.dnd.item;

public class Sword extends Item {
	public Sword() {
		super("Sword");
	}

	public Sword(String pString) {
		super(pString);
	}

	public boolean isSharp() {
		return true;
	}

}
