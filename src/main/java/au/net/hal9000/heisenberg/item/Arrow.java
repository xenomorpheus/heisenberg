package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

// import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.item.property.Sharp;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// @PrimaryKeyJoinColumn(name="tableid", referencedColumnName="tableid")
public class Arrow extends Item implements Sharp {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private static final float VOLUME_BASE = 1; // TODO arrow volume default

    // Constructor
    public Arrow() {
        super("Arrow");
        this.setVolumeBase(VOLUME_BASE);
    }

    public Arrow(final String pString) {
        this.setName(pString);
    }

}
