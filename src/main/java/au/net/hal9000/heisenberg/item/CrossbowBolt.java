package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class CrossbowBolt extends ItemImpl {

    /**
     * Field serialVersionUID.
     * (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for CrossbowBolt.
     */
    public CrossbowBolt() {
        this("CrossbowBolt");
    }

    /**
     * Constructor for CrossbowBolt.
     * @param pString String
     */
    private CrossbowBolt(String pString) {
        super(pString);
    }

}
