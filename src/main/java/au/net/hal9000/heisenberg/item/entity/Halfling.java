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

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Halfling extends Humanoid implements Animal{

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
