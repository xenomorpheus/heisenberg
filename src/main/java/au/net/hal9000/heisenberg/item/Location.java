package au.net.hal9000.heisenberg.item;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Location extends ItemContainer implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public Location() {
        this("Location");
    }

    public Location(final String pName) {
        super(pName);
    }



}
