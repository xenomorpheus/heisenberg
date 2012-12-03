package au.net.hal9000.heisenberg.item;

/**
 *  MagicRing
 * is a Ring except, it is Magical.
 */

import au.net.hal9000.heisenberg.item.property.ItemProperty;

public class MagicRing extends Ring {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    // Constructor
    public MagicRing() {
        this("MagicRing");
    }

    public MagicRing(String pName) {
        super(pName);
        ItemProperty.setMagical(this, true);
    }

}
