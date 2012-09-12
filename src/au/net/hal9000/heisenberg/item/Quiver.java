package au.net.hal9000.heisenberg.item;

public class Quiver extends ItemContainer {


    private static final long serialVersionUID = 1L;
    // TODO quiver volume default
    // 20 times volume of an arrow ?
    private static final float VOLUME_MAX_DFT = 20; 

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

    public void add(final Object object) {
        throw new IllegalArgumentException("Only Arrows accepted");
    }
}
