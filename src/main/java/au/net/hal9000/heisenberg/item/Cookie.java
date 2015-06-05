package au.net.hal9000.heisenberg.item;

//Persistence
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

// Custom
import au.net.hal9000.heisenberg.item.property.ItemProperty;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Cookie extends Item {

    /**
     * Field serialVersionUID.
     * (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for Cookie.
     */
    public Cookie() {
        this("Cookie");
    }

    /**
     * Constructor for Cookie.
     * @param pName String
     */
    public Cookie(String pName) {
        super(pName);
        ItemProperty.setHumanoidFood(this, true);
    }

    /**
     * Constructor for Cookie.
     * @param pName String
     * @param pDescription String
     */
    Cookie(String pName, String pDescription) {
        super(pName, pDescription);
    }

    // Methods

}
