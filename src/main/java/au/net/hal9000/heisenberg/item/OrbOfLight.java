package au.net.hal9000.heisenberg.item;

// MagicRing
// is a Ring except:
// It is Magical.

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.item.property.ItemProperty;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name="ID", referencedColumnName="ID")
public class OrbOfLight extends Candle {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    // Constructor
    public OrbOfLight() {
        this("Orb Of Light");
    }

    public OrbOfLight(String pName) {
        this(pName,"orb of light");
    }
    public OrbOfLight(String pName, String pDescription) {
        super(pName, pDescription);
        ItemProperty.setMagical(this,true);
    }

}
