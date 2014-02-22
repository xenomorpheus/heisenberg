package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.item.property.ItemProperty;

/**
 * A magical Orb of light.
 * 
 * @author bruins
 * 
 * @version $Revision: 1.0 $
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class OrbOfLight extends Candle {

    /** serial id. */
    private static final long serialVersionUID = 1L;

    /** Constructor. */
    public OrbOfLight() {
        this("Orb Of Light");
    }

    /** Constructor.
     * 
     * @param name name of the orb.
     */
    public OrbOfLight(String name) {
        this(name, "orb of light");
    }

    /**
     * Constructor.
     * @param name name of the orb.
     * @param description the description.
     */
    public OrbOfLight(String name, String description) {
        super(name, description);
        ItemProperty.setMagical(this, true);
    }

}
