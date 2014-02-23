package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * A small ground fire.
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class SmallGroundFire extends Item {

    /**
     * Field serialVersionUID.
     * (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for SmallGroundFire.
     */
    public SmallGroundFire() {
        this("SmallGroundFire");
    }

    /**
     * Constructor for SmallGroundFire.
     * @param pName String
     */
    public SmallGroundFire(String pName) {
        this(pName, "a small ground fire");
    }

    /**
     * Constructor for SmallGroundFire.
     * @param pName String
     * @param pDescription String
     */
    public SmallGroundFire(String pName, String pDescription) {
        super(pName, pDescription);
    }

    // Methods

}
