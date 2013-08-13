package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name="ID", referencedColumnName="ID")
public class Cat extends au.net.hal9000.heisenberg.item.Entity {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public Cat() {
        super("Cat");
    }

    public Cat(String name) {
        this();
        setName(name);
    }

    public Cat(String name, String description) {
        this();
        setName(name);
        setDescription(description);
    }
}
