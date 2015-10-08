package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.api.Sharp;
import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;

/**
 * A common Bog for holding multiple Item objects.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Bag extends Box {

    /** serial version. */
    private static final long serialVersionUID = 1L;

    /** maximum default weight this bag can hold. */
    private static final float WEIGHT_MAX_DEFAULT = 100; // TODO config

    /** maximum default volume this bag can hold. */
    private static final float VOLUME_MAX_DEFAULT = 100; // TODO config

    /**
     * Constructor.
     */
    public Bag() {
        this("Bag");
    }

    /**
     * Constructor.
     * 
     * @param pName
     *            the name of the bag.
     */
    public Bag(final String pName) {
        super(pName);
        setDescription("A common cloth sack about 2 feet by 4 feet in size.");
        this.setWeightMax(WEIGHT_MAX_DEFAULT);
        this.setVolumeMax(VOLUME_MAX_DEFAULT);
    }

    // Methods

    // TODO finish rupture
    /**
     * Method rupture.
     */
    public void rupture() {
        // System.out.println("Ordinary rupture");
    }

    /**
     * {@inheritDoc}
     * 
     * @param item
     *            Item
     * 
     * 
     * @throws InvalidTypeException
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    @Override
    public void add(final Item item) throws InvalidTypeException,
            TooHeavyException, TooLargeException {
        // Look for sharp items. Wrapped sharp items are safe.
        if (item instanceof Sharp) {
            this.rupture();
            throw new InvalidTypeException("Sharp");
        }
        super.add(item);
    }

}
