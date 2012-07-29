package au.net.hal9000.player.item;

public class Quiver extends ItemContainer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private static final float defaultVolumeMax = 20; // TODO quiver volume default
    // 20 times volume of an arrow ?

	public Quiver() {
		this("Quiver");
	}

	public Quiver(String pName) {
		super(pName);
		this.setVolumeMax(defaultVolumeMax);
	}

	public void add(Arrow pArrow) {
		super.add(pArrow);
	}

}
