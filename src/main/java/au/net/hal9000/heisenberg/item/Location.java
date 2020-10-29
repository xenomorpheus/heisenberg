package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * A Location is the base type for an area that Items can interact. e.g. a
 * clearing.
 *
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Location extends ItemContainerImpl {

    /** serial version id. */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     */
    public Location() {
        this("Location");
    }

    /**
     * Constructor.
     *
     * @param name
     *            name of location.
     */
    public Location(final String name) {
        super(name);
    }
 
}
