package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.item.property.HumanoidMount;
import au.net.hal9000.heisenberg.item.property.ItemProperty;

/**
 * A horse.
 * @author bruins
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Horse extends au.net.hal9000.heisenberg.item.Entity implements
        HumanoidMount {

    /** serial version id. */
    private static final long serialVersionUID = 1L;

    /** Consturctor. */
    public Horse() {
        this("Horse");
    }

    /** Constructor.
     * 
     * @param name name of Item.
     */
    public Horse(String name) {
        super(name);
        ItemProperty.setLiving(this, true);
        this.setWeightMax(100); // TODO config
        this.setVolumeMax(100); // TODO config
    }

    protected String getRace() {
        return null;
    }
}
