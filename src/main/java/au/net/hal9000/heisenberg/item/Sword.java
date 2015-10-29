package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import au.net.hal9000.heisenberg.item.api.Sharp;

/**
 * A common sword.
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Sword extends ItemImpl implements Sharp {

    /**
     * Field serialVersionUID.
     * (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for Sword.
     */
    public Sword() {
        this("Sword");
    }

    /**
     * Constructor for Sword.
     * @param pString String
     */
    private Sword(final String pString) {
        super(pString);
    }

}
