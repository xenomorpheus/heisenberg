package au.net.hal9000.player.item;

public class Crossbow extends Item {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private CrossbowBolt loadedBolt = null;

    public Crossbow() {
        this("Crossbow");
    }

    public Crossbow(final String pName) {
        super(pName);
    }

    public CrossbowBolt getLoadedBolt() {
        return loadedBolt;
    }

    public void setLoadedBolt(final CrossbowBolt bolt) {
        this.loadedBolt = bolt;
    }

    // TODO should other be Item? if not will super's equals be used?
    /** {@inheritDoc} */
    // @Override
    public boolean equals(final Object other) {
        // loadedBolt
        boolean isEqual = false;
        if (other instanceof Crossbow) {
            final Crossbow otherCrossbow = (Crossbow) other;
            if (loadedBolt == null) {
                if (otherCrossbow.loadedBolt != null) {
                    isEqual = false;
                }
            } else {
                if (otherCrossbow.loadedBolt == null) {
                    isEqual = false;
                }

                else {
                    if (!loadedBolt.equals(otherCrossbow.loadedBolt)) {
                        isEqual = false;
                    }
                }
            }
            // Check the super properties as well.
            if (isEqual){
                isEqual =  super.equals(other);
            }
        }
        return isEqual;
    }

    // bow plus bolt if present.
    public float getWeight() {
        float total = super.getWeight();
        if (loadedBolt != null) {
            total += loadedBolt.getWeight();
        }
        return total;
    }

    // Find items that match the criteria
    public void visit(final ItemVisitor visitor) {
        // Search fields defined in this class.
        if (loadedBolt != null) {
            visitor.visit(loadedBolt);
        }
        // Let our super handle the rest.
        super.accept(visitor);
    }

}
