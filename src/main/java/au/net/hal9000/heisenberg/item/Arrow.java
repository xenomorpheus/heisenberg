package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

// import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.item.property.Sharp;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// @PrimaryKeyJoinColumn(name="tableid", referencedColumnName="tableid")
public class Arrow extends Item implements Sharp {

    /**
     * Field serialVersionUID.
     * (value is 1)
     */
    private static final long serialVersionUID = 1L;
    /**
     * Field VOLUME_BASE.
     * (value is 1.0)
     */
    private static final float VOLUME_BASE = 1; // TODO arrow volume default

    /**
     * Constructor.
     * 
     */
    public Arrow() {
        super("Arrow");
        this.setVolumeBase(VOLUME_BASE);
    }

    /**
     * Constructor.
     * 
     * @param name
     *            common name.
     */
    public Arrow(final String name) {
        this();
        this.setName(name);
    }

}
