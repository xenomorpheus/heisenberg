package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import au.net.hal9000.heisenberg.item.property.ItemProperty;

/**
 * Is like an item except: May wear at most one magical ring. May wear any
 * number of non-magical rings. Rings may be removed in any order.
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Hand extends ItemContainerImpl {

    /**
     * Field serialVersionUID.
     * (value is 1)
     */
    private static final long serialVersionUID = 1L;
 
    /**
     * Constructor.
     */
    public Hand() {
        this("Hand");
    }

    /**
     * Constructor.
     * @param string String
     */
    public Hand(String string) {
        super(string);
        ItemProperty.setLiving(this, true);
        this.setWeightMax(2); // TODO move to config
        this.setVolumeMax(2); // TODO move to config
    }
}
