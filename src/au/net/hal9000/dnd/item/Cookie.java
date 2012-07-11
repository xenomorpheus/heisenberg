package au.net.hal9000.dnd.item;

public class Cookie extends ItemImpl {
	public Cookie() {
		super("Cookie");
	}

	public Cookie(String pName) {
		super(pName);
	}

	public Cookie(String pName, String pDescription) {
		super(pName, pDescription);
	}

	// Feature
	public static boolean isHumanFood() {
		return true;
	}

	public boolean equals(Cookie other) {
		// TODO Auto-generated method stub
		return false;
	}
}
