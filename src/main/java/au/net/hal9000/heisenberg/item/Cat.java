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
public class Cat extends Animal {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for Cat.
     */
    public Cat() {
        super("Cat");
    }

    /**
     * Constructor for Cat.
     * @param name String
     */
    public Cat(String name) {
        this();
        setName(name);
    }

    /**
     * Constructor for Cat.
     * @param name String
     * @param description String
     */
    public Cat(String name, String description) {
        this();
        setName(name);
        setDescription(description);
    }
}
