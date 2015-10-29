package au.net.hal9000.heisenberg.item.entity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import au.net.hal9000.heisenberg.item.Animal;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.mixin.EntityConsumeSustenance;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Cat extends au.net.hal9000.heisenberg.item.entity.Entity implements
        Animal {

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

    @Override
    public void consume(Item sustenance) {
        EntityConsumeSustenance.consume(this, sustenance);
    }

}
