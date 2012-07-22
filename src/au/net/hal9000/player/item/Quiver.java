package au.net.hal9000.player.item;

public class Quiver extends ItemContainer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Quiver() {
		this("Quiver");
	}

	public Quiver(String pName) {
		super(pName);
	}

	public void add(Arrow pArrow) {
		super.add(pArrow);
	}

}
