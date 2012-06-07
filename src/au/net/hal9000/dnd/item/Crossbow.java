package au.net.hal9000.dnd.item;


import au.net.hal9000.dnd.item.property.ItemSearch;

public class Crossbow extends Item {
	private CrossbowBolt loadedBolt = null;

	public Crossbow() {
		super("Crossbow");
	}

	public Crossbow(String pCrossbow) {
		super(pCrossbow);
	}

	public CrossbowBolt getLoadedBolt() {
		return loadedBolt;
	}

	public void setLoadedBolt(CrossbowBolt bolt) {
		this.loadedBolt = bolt;
	}

	public boolean equals(Crossbow other) {
		// loadedBolt
		if (loadedBolt == null) {
			if (other.loadedBolt != null) {
				return false;
			}
		} else {
			if (other.loadedBolt == null) {
				return false;
			}

			if (!loadedBolt.equals(other.loadedBolt)) {
				return false;
			}
		}
		return super.equals(other);
	}

	// Find items that match the criteria
	public void searchHelper(ItemSearch pSearch) {
		// Search fields defined in this class.
		if (this.loadedBolt != null) {
			pSearch.searchItem(this.loadedBolt);
		}
		// Let our super handle the rest.
		pSearch.searchItem(this);
	}	
    // todo getWeight(), equal()

}
