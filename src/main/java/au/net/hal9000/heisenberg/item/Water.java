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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class Water extends Item implements SplitByWeight {
    /**
     * Some plain water.
     */
    private static final long serialVersionUID = 1L;

    public Water() {
        this("Water");
    }

    public Water(String pName) {
        super(pName);
    }

    public Water(String pName, String pDescription) {
        super(pName, pDescription);
    }

    // Methods

    // Static
    public static Water thawFromFile(String filename) throws IOException,
            ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(fis);
        Water newObj = (Water) in.readObject();
        in.close();
        return newObj;
    }

    /** {@inheritDoc} */
    @Override
    public Water splitByWeight(float newItemWeight) {
        return (Water) ItemSplitByWeight.splitByWeight(this, newItemWeight);
    }

}
