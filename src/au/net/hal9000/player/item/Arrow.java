package au.net.hal9000.player.item;

public class Arrow extends Item {
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

    // Feature
    /** {@inheritDoc} */
    @Override
    public boolean isSharp() {
        return true;
    }
}
