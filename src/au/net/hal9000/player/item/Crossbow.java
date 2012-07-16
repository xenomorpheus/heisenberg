package au.net.hal9000.player.item;

import au.net.hal9000.player.units.Weight;

public class Crossbow extends ItemImpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	// bow plus bolt if present.
	public Weight getWeight() {
		Weight total = super.getWeight();
		if (loadedBolt != null) {
			total.add(loadedBolt.getWeight());
		}
		return total;
	}

	// Find items that match the criteria
	public void visit(ItemVisitor visitor) {
		// Search fields defined in this class.
		if (loadedBolt != null) {
			visitor.visit(loadedBolt);
		}
		// Let our super handle the rest.
		super.accept(visitor);
	}

}
