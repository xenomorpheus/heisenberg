package au.net.hal9000.heisenberg.item.entity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.item.property.HumanoidMount;
import au.net.hal9000.heisenberg.item.property.ItemProperty;

/**
 * A common horse.
 * @author bruins
 * @version $Revision: 1.0 $
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Horse extends au.net.hal9000.heisenberg.item.entity.Entity implements
        HumanoidMount {

    /** serial version id. */
    private static final long serialVersionUID = 1L;

    /** Constructor. */
    public Horse() {
        this("Horse");
    }

    /** Constructor.
     * 
     * @param name name of Item.
     */
    private Horse(String name) {
        super(name);
        ItemProperty.setLiving(this, true);
    }

}
