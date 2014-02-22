package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Torch extends Candle {

    /**
     * A stick with some kind of combustable material on the end e.g. oiled rag.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for Torch.
     */
    public Torch() {
        this("Torch");
    }

    /**
     * Constructor for Torch.
     * @param pName String
     * @param pDescription String
     */
    public Torch(String pName, String pDescription) {
        super(pName, pDescription);
    }

    /**
     * Constructor for Torch.
     * @param pName String
     */
    public Torch(String pName) {
        this(pName, "a short wooden rod tipped with cloth soaked in oil");
    }

    /**
     * Method setType.
     * @param type int
     */
    public void setType(final int type) {
        if (type == 1) {
            this.setVolumeBase(1.0f); // TODO what about litres vs. gallons
            this.setWeightBase(1.0f); // TODO what about kilos vs. pounds ?
        }
    }

}
