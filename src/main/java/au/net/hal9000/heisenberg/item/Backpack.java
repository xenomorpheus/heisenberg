package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.item.api.HumanoidCoreClothing;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Backpack extends Bag implements HumanoidCoreClothing {

    /**
     * Field serialVersionUID.
     * (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for Backpack.
     */
    public Backpack() {
        this("Backpack");
    }

    /**
     * Constructor for Backpack.
     * @param pString String
     */
    public Backpack(final String pString) {
        super(pString);
        ItemProperty.setClothing(this, true);
    }

}
