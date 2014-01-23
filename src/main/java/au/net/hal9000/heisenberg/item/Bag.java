package au.net.hal9000.heisenberg.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.item.exception.ExceptionInvalidType;
import au.net.hal9000.heisenberg.item.property.Sharp;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Bag extends Box {

    private static final long serialVersionUID = 1L;

    public Bag() {
        this("Bag");
    }

    public Bag(final String pName) {
        super(pName);
        setDescription("A common cloth sack about 2 feet by 4 feet in size.");
        this.setWeightMax(100); // TODO config
        this.setVolumeMax(100); // TODO config
    }

    // Methods

    // TODO finish rupture
    public void rupture() {
        // System.out.println("Ordinary rupture");
    }

    /** {@inheritDoc} */
    @Override
    public void add(final Item item) {
        // Look for sharp items. Wrapped sharp items are safe.
        if (item instanceof Sharp) {
            this.rupture();
            throw new ExceptionInvalidType("Sharp");
        }
        super.add(item);
    }

}
