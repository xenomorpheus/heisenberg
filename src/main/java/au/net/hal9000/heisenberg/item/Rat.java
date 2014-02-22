package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * An ordinary rat.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Rat extends au.net.hal9000.heisenberg.item.Entity {

    /**
     * Field serialVersionUID. (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for Rat.
     */
    public Rat() {
        super("Rat");
    }

    /**
     * Constructor for Rat.
     * 
     * @param name
     *            String
     */
    public Rat(String name) {
        this();
        setName(name);
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
        this();
        setName(name);
        setDescription(description);
    }
}
