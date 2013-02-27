package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name="ID", referencedColumnName="ID")
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
