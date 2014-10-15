package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;
import au.net.hal9000.heisenberg.item.property.ItemProperty;

/**
 * A common quiver for holding arrows.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Quiver extends ItemContainer {

    /** serial version. */
    private static final long serialVersionUID = 1L;
    /** Quiver volume default. 20 times volume of an arrow. */
    private static final float VOLUME_MAX_DFT = 20;

    /**
     * Constructor.
     */
    public Quiver() {
        this("Quiver");
    }

    /**
     * Constructor.
     * 
     * @param name
     *            name to use.
     */
    public Quiver(final String name) {
        super(name);
        this.setVolumeMax(VOLUME_MAX_DFT);
        ItemProperty.setClothing(this, true);
    }

    /**
     * Add arrows to the quiver.
     * 
     * @param arrow
     *            arrow Item to add to Quiver.
     * @throws InvalidTypeException
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    public void add(final Arrow arrow) throws InvalidTypeException,
            TooHeavyException, TooLargeException {
        super.add(arrow);
    }

    /**
     * This method is required to override the parent class method.
     * 
     * 
     * @param item
     *            item to attempt (and fail) to add to quiver.
     * @deprecated since <unknown>
     */
    @Override
    public void add(final Item item) {
        throw new IllegalArgumentException("Only Arrows accepted");
    }
}
