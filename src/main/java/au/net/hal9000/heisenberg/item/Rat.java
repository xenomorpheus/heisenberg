package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * A common rat.
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Rat extends Animal {

    /**
     * Field serialVersionUID. (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for Rat.
     */
    public Rat() {
        this("Rat");
    }

    /**
     * Constructor for Rat.
     * 
     * @param name
     *            String
     */
    public Rat(String name) {
        this(name,"A Rat");
    }

    /**
     * Constructor for Rat.
     * 
     * @param name
     *            String
     * @param description
     *            String
     */
    public Rat(String name, String description) {
        super(name, description);
    }
}
