package au.net.hal9000.player.item;

import au.net.hal9000.player.item.property.Sharp;

public class Arrow extends Item implements Sharp{
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private static final float VOLUME_BASE = 1; // TODO arrow volume default

    // Constructor
    public Arrow() {
        this("Arrow");
    }

    public Arrow(final String pString) {
        super(pString);
        this.setVolumeBase(VOLUME_BASE);
    }

}
