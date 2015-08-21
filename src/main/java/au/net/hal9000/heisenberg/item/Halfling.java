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
public class Halfling extends Humanoid {

    /**
     * Field serialVersionUID. (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for Halfling.
     */
    public Halfling() {
        this("Halfling");
    }

    /**
     * Constructor for Halfling.
     * 
     * @param name
     *            String
     */
    public Halfling(String name) {
        this(name, "A Halfling");
    }

    /**
     * Constructor for Halfling.
     * 
     * @param name
     *            String
     * @param description
     *            String
     */
    public Halfling(String string, String description) {
        super(string, description);
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

    /**
     * Eat.
     * 
     * @param cookie
     *            animal to eat.<br>
     *            Throw RuntimeException on error.
     */
    public void eat(Cookie cookie) {
        Eat.eat(this, cookie);
    }
}
