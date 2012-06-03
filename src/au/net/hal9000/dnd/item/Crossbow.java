package au.net.hal9000.dnd.item;

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
}
