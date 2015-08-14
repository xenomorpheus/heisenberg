package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.item.mixin.Eat;
import au.net.hal9000.heisenberg.util.PcClass;

/**
 * A common human.
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Human extends Humanoid {

    /**
     * Field serialVersionUID.
     * (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for Human.
     */
    public Human() {
        super("Human");
    }

    /**
     * Constructor for Human.
     * @param string String
     */
    public Human(String string) {
        super(string);
    }

    /**
     * Constructor for Human.
     * @param string String
     * @param description String
     */
    Human(String string, String description) {
        super(string, description);
    }

    /**
     * Constructor for Human.
     * @param name String
     * @param pcClass PcClass
     */
    Human(String name, PcClass pcClass) {
        super(name, pcClass);
    }

    /**
     * Constructor for Human.
     * @param name String
     * @param description String
     * @param pcClass PcClass
     */
    Human(String name, String description, PcClass pcClass) {
        super(name, description, pcClass);
    }
    /**
     * Eat.
     * 
     * @param food
     *            food Item to eat.<br>
     *            Throw RuntimeException on error.
     */
    public void eat(Animal food) {
        Eat.eat(this, food);
    }
}
