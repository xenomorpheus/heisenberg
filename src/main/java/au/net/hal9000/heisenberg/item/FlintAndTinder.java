package au.net.hal9000.heisenberg.item;


import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class FlintAndTinder extends Item {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public FlintAndTinder() {
        this("FlintAndTinder");
    }

    public FlintAndTinder(String pName) {
        this(pName, "some flint and tinder");
    }

    public FlintAndTinder(String pName, String pDescription) {
        super(pName, pDescription);
    }

    // Methods

}
