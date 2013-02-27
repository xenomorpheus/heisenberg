package au.net.hal9000.heisenberg.item;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.item.exception.*;
import au.net.hal9000.heisenberg.item.property.Sharp;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name="ID", referencedColumnName="ID")
public class Bag extends Box {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public Bag() {
        this("Bag");
    }

    public Bag(final String pName) {
        super(pName);
        setDescription("A common cloth sack about 2 feet by 4 feet in size.");
        this.setWeightMax(100);  // TODO config
        this.setVolumeMax(100);  // TODO config
        }

    // TODO finish rupture
    public void rupture() {
        // System.out.println("Ordinary rupture");
    }

    /** {@inheritDoc} */
	@Override
    public void add(final IItem item) throws ExceptionTooHeavy, ExceptionTooBig,
            ExceptionInvalidType {
        // Look for sharp items. Wrapped sharp items are safe.
        if (item instanceof Sharp) {
            this.rupture();
            throw new ExceptionInvalidType("Sharp");
        }
        super.add(item);
    }

    // Methods
    // Static
    public static Bag thawFromFile(final String filename) throws IOException,
            ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(fis);
        Bag newObj = (Bag) in.readObject();
        in.close();
        return newObj;
    }
}
