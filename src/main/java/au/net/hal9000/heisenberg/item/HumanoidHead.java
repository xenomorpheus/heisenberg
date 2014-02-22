package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import au.net.hal9000.heisenberg.item.property.ItemProperty;

/**
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class HumanoidHead extends Item {

    /**
     * Field serialVersionUID.
     * (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for HumanoidHead.
     * @param pName String
     */
    public HumanoidHead(String pName) {
        super(pName);
        ItemProperty.setLiving(this, true);
    }

    /**
     * Constructor for HumanoidHead.
     */
    public HumanoidHead() {
        this("Head");
    }

}
