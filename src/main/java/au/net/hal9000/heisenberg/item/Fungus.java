package au.net.hal9000.heisenberg.item;

//Persistence
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Fungus extends Item {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public Fungus() {
        super("Fungus");
    }

    public Fungus(String name) {
        this();
        setName(name);
    }

    public Fungus(String name, String description) {
        this();
        setName(name);
        setDescription(description);
    }

    // Methods

}
