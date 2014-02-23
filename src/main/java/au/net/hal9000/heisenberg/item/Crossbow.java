package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Crossbow extends Item {

    /**
     * Field serialVersionUID.
     * (value is 1)
     */
    private static final long serialVersionUID = 1L;
    /**
     * Field loadedBolt.
     */
    private CrossbowBolt loadedBolt = null;

    /**
     * Constructor for Crossbow.
     */
    public Crossbow() {
        this("Crossbow");
    }

    /**
     * Constructor for Crossbow.
     * @param pName String
     */
    public Crossbow(final String pName) {
        super(pName);
    }

    /**
     * Method getLoadedBolt.
     * @return CrossbowBolt
     */
    public CrossbowBolt getLoadedBolt() {
        return loadedBolt;
    }

    /**
     * Method setLoadedBolt.
     * @param bolt CrossbowBolt
     */
    public void setLoadedBolt(final CrossbowBolt bolt) {
        loadedBolt = bolt;
    }

    // bow plus bolt if present.
    /**
     * Method getWeight.
     * @return float
     */
    public float getWeight() {
        float total = super.getWeight();
        if (null != loadedBolt) {
            total += loadedBolt.getWeight();
        }
        return total;
    }

    // Find items that match the criteria
    /**
     * Method visit.
     * @param visitor ItemVisitor
     */
    public void visit(final ItemVisitor visitor) {
        // Search fields defined in this class.
        if (null != loadedBolt) {
            visitor.visit(loadedBolt);
        }
        // Let our super handle the rest.
        super.accept(visitor);
    }

}
