package au.net.hal9000.dnd.item;

// MagicRing
// is a Ring except:
// It is Magical.

public class MagicRing extends Ring {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int plus;

	// Constructor
	public MagicRing() {
		super("MagicRing");
	}

	public MagicRing(String pName) {
		super(pName);
	}

	// Features
	public boolean isMagical() {
		return true;
	}

	// Others
	public int getPlus() {
		return plus;
	}

	public void setPlus(int plus) {
		this.plus = plus;
	}

}
