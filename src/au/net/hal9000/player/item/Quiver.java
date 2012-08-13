package au.net.hal9000.player.item;

public class Quiver extends ItemContainer {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private static final float VOLUME_MAX_DFT = 20; // TODO quiver volume
                                                    // default

    // 20 times volume of an arrow ?

    public Quiver() {
        this("Quiver");
    }

    public Quiver(final String pName) {
        super(pName);
        this.setVolumeMax(VOLUME_MAX_DFT);
    }

    public void add(final Arrow pArrow) {
        super.add(pArrow);
    }
    public void add(final Item pItem) {
        throw new RuntimeException("Invalid Type");
    }
}
