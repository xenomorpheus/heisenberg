package au.net.hal9000.heisenberg.item;

/**
 *  MagicRing
 * is a Ring except, it is Magical.
 */

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.item.property.ItemProperty;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name="ID", referencedColumnName="ID")
public class MagicRing extends Ring {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    // Constructor
    public MagicRing() {
        this("MagicRing");
    }

    public MagicRing(String pName) {
        super(pName);
        ItemProperty.setMagical(this, true);
    }

}
