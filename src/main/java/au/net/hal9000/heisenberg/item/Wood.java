package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.property.SplitByWeight;
import au.net.hal9000.heisenberg.util.ItemSplitByWeight;

/**
 * Some common wood.
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Wood extends ItemImpl implements SplitByWeight {

    /**
     * Field serialVersionUID. (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for Wood.
     */
    public Wood() {
        this("Wood");
    }

    /**
     * Constructor for Wood.
     * 
     * @param pName
     *            String
     */
    public Wood(String pName) {
        this(pName, "some wood");
    }

    /**
     * Constructor for Wood.
     * 
     * @param pName
     *            String
     * @param pDescription
     *            String
     */
    public Wood(String pName, String pDescription) {
        super(pName, pDescription);
    }

    // Methods

    /**
     * {@inheritDoc} * @param newItemWeight float
     * 
     * @return Item
     */
    @Override
    public Item splitByWeight(float newItemWeight) {
        return ItemSplitByWeight.splitByWeight(this, newItemWeight);
    }

}
