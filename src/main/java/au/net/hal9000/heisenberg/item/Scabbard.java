package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;
import au.net.hal9000.heisenberg.item.property.ItemProperty;

/**
 * An ordinary scabbard (with belt) for holding a sword.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Scabbard extends Box {

    /**
     * Field serialVersionUID. (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for Scabbard.
     */
    public Scabbard() {
        this("Scabbard");
    }

    /**
     * Constructor for Scabbard.
     * 
     * @param name
     *            String
     */
    public Scabbard(String name) {
        this(name, "A scabbard");
    }

    /**
     * Constructor for Scabbard.
     * 
     * @param name
     *            String
     * @param description
     *            String
     */
    public Scabbard(String name, String description) {
        super(name, description);
        ItemProperty.setClothing(this, true);
        // TODO set max volume & weight to that of a sword.
    }

    /**
     * {@inheritDoc} * @param item Item
     */
    @Override
    public void add(Item item){
        // We need to accept all Items, not just swords,
        // otherwise our super will accept them for us
        // which is bad.
        // Currently there are no plans to allow low volume items
        // such a coins to be added instead of a sword.
        if (!(item instanceof Sword)) {
            throw new InvalidTypeException("non sword");
        }
        if (size() > 0) {
            throw new TooLargeException("scabbard full");
        }
        super.add(item);
    }

}
