package au.net.hal9000.heisenberg.item;

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
public class FlintAndTinder extends Item {

    /**
     * Field serialVersionUID.
     * (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for FlintAndTinder.
     */
    public FlintAndTinder() {
        this("FlintAndTinder");
    }

    /**
     * Constructor for FlintAndTinder.
     * @param pName String
     */
    private FlintAndTinder(String pName) {
        this(pName, "some flint and tinder");
    }

    /**
     * Constructor for FlintAndTinder.
     * @param pName String
     * @param pDescription String
     */
    private FlintAndTinder(String pName, String pDescription) {
        super(pName, pDescription);
    }

    // Methods

}
