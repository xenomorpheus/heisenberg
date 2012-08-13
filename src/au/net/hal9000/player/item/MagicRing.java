package au.net.hal9000.player.item;

// MagicRing
// is a Ring except:
// It is Magical.

import au.net.hal9000.player.item.property.Magical;

public class MagicRing extends Ring implements Magical {
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

	// Others
	public int getPlus() {
		return plus;
	}

	public void setPlus(int plus) {
		this.plus = plus;
	}

}
