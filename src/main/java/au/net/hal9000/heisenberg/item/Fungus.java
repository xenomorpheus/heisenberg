package au.net.hal9000.heisenberg.item;

//Persistence
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Fungus extends Item {

    /**
     * Field serialVersionUID.
     * (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for Fungus.
     */
    public Fungus() {
        this("Fungus");
    }

    /**
     * Constructor for Fungus.
     * @param name String
     */
    public Fungus(String name) {
        this(name, "Some fugus");
    }

    /**
     * Constructor for Fungus.
     * @param name String
     * @param description String
     */
    public Fungus(String name, String description) {
        super(name, description);
    }

    // Methods

}
