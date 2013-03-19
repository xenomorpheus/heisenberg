package au.net.hal9000.heisenberg.item;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
public class SmallGroundFire extends Item {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public SmallGroundFire() {
        this("SmallGroundFire");
    }

    public SmallGroundFire(String pName) {
        this(pName, "a small ground fire");
    }

    public SmallGroundFire(String pName, String pDescription) {
        super(pName, pDescription);
    }

    // Methods

    // Static
    public static SmallGroundFire thawFromFile(String filename)
            throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(fis);
        SmallGroundFire newObj = (SmallGroundFire) in.readObject();
        in.close();
        return newObj;
    }

}