package au.net.hal9000.heisenberg.item;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * A Location is the base type for an area that Items can interact. e.g. a
 * clearing.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Location extends ItemContainerImpl implements Serializable {

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
