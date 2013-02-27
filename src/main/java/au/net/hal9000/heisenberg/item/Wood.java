package au.net.hal9000.heisenberg.item;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import au.net.hal9000.heisenberg.util.ItemSplitByWeight;


import au.net.hal9000.heisenberg.item.property.SplitByWeight;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name="ID", referencedColumnName="ID")
public class Wood extends Item implements SplitByWeight {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public Wood() {
        this("Wood");
    }

    public Wood(String pName) {
        this(pName, "some wood");
    }

    public Wood(String pName, String pDescription) {
        super(pName, pDescription);
    }

    /** {@inheritDoc} */
    @Override
    public Wood clone(IItem toClone) throws CloneNotSupportedException {
        return (Wood) super.clone(toClone);
    }

    // Methods

    // Static
    public static Wood thawFromFile(String filename) throws IOException,
            ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(fis);
        Wood newObj = (Wood) in.readObject();
        in.close();
        return newObj;
    }

    /** {@inheritDoc} */
    @Override
    public Wood splitByWeight(float newItemWeight) {
        return (Wood)ItemSplitByWeight.splitByWeight(this, newItemWeight);
    }

}
