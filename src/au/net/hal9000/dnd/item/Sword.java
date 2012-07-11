package au.net.hal9000.dnd.item;

public class Sword extends ItemImpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
