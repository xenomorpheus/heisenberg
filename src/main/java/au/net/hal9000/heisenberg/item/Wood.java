package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.util.ItemSplitByWeight;

import au.net.hal9000.heisenberg.item.property.SplitByWeight;

/**
 * Some plain wood.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Wood extends Item implements SplitByWeight {

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
