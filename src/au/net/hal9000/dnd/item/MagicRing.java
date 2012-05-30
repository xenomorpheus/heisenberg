package au.net.hal9000.dnd.item;

import au.net.hal9000.dnd.item.property.Magical;
// MagicRing
// is a Ring except:
// It is Magical.


public class MagicRing extends Ring implements Magical {
	private int plus;

	public int getPlus() {
		return plus;
	}

	public void setPlus(int plus) {
		this.plus = plus;
	}

}

