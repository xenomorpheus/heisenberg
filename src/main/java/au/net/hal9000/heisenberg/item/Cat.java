package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.item.mixin.Eat;

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
        this("Cat");
    }

    /**
     * Constructor for Cat.
     * 
     * @param name
     *            String
     */
    Cat(String name) {
        this(name, "A cat");
    }

    /**
     * Constructor for Cat.
     * 
     * @param name
     *            String
     * @param description
     *            String
     */
    Cat(String name, String description) {
        super(name, description);
    }

    /**
     * Eat.
     * 
     * @param animal
     *            animal to eat.<br>
     *            Throw RuntimeException on error.
     */
    public void eat(Animal animal) {
        Eat.eat(this, animal);
    }


}
