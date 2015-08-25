package au.net.hal9000.heisenberg.item.entity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.crafting.Cooker;
import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.mixin.Drink;
import au.net.hal9000.heisenberg.item.mixin.Eat;
import au.net.hal9000.heisenberg.util.PcClass;

/**
 * A common human.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Human extends Humanoid implements Animal {

    /**
     * Field serialVersionUID. (value is 1)
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
     * 
     * @param string
     *            String
     */
    public Human(String string) {
        super(string);
    }

    /**
     * Constructor for Human.
     * 
     * @param string
     *            String
     * @param description
     *            String
     */
    Human(String string, String description) {
        super(string, description);
    }

    /**
     * Constructor for Human.
     * 
     * @param name
     *            String
     * @param pcClass
     *            PcClass
     */
    Human(String name, PcClass pcClass) {
        super(name, pcClass);
    }

    /**
     * Constructor for Human.
     * 
     * @param name
     *            String
     * @param description
     *            String
     * @param pcClass
     *            PcClass
     */
    Human(String name, String description, PcClass pcClass) {
        super(name, description, pcClass);
    }

    @Override
    public void eat(Item food) {
        if (food instanceof Cookie) {
            Cooker cooker = this.getCooker("ItemEatCookie");
            cooker.setChef(this);
            cooker.setItemsAvailable("Food", food);
            cooker.cook();
        } else{
        Eat.eat(this, this);
        }
    }

    @Override
    public void drink(Item liquid) {
        Drink.drink(this, liquid);
    }

}
