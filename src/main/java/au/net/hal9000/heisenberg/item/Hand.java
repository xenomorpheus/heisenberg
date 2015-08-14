package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.item.property.ItemProperty;

/**
 * Hand Is like an item except: May wear at most one magical ring. May wear any
 * number of non-magical rings. Rings may be removed in any order.
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Hand extends ItemContainer {

    /**
     * Field serialVersionUID.
     * (value is 1)
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor for Hand.
     */
    public Hand() {
        this("Hand");
    }

    /**
     * Constructor for Hand.
     * @param string String
     */
    Hand(String string) {
        super(string);
        ItemProperty.setLiving(this, true);
        this.setWeightMax(2); // TODO config
        this.setVolumeMax(2); // TODO config
    }


}
