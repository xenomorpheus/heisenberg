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
public class Halfling extends Humanoid implements Animal {

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

    @Override
    public void consume(Item sustenance) {
        EntityConsumeSustenance.consume(this, sustenance);
    }

}
