package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.item.property.ItemProperty;
import au.net.hal9000.heisenberg.item.property.SplitByWeight;
import au.net.hal9000.heisenberg.util.ItemSplitByWeight;

/**
 * Some common water.
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Water extends Item implements SplitByWeight {
    /** serial version id. */
    private static final long serialVersionUID = 1L;

    /** constructor. */
    public Water() {
        this("Water", "water");
    }

    /**
     * constructor.
     * 
     * @param pName
     *            name to use.
     * @param pDescription
     *            description to use.
     */
    private Water(String pName, String pDescription) {
        super(pName, pDescription);
        ItemProperty.setHumanoidFood(this, true);
    }

    // Methods

    // Static

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
