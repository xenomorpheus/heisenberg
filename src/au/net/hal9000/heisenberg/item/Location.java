package au.net.hal9000.heisenberg.item;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name="ID", referencedColumnName="ID")
public class Location extends ItemContainer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    public Location() {
        this("Location");
    }	
	
	public Location(final String pName) {
		super(pName);
	}

	// Store the object in a file
	public void freezeToFile(final String filename) throws IOException {
		FileOutputStream fos = new FileOutputStream(filename);
		ObjectOutputStream out = new ObjectOutputStream(fos);
		out.writeObject(this);
		out.close();
	}
	
// Just experimenting
//	private void writeObject(ObjectOutputStream out) throws IOException {
//		throw new NotSerializableException("Not today!");
//	}

	// Static
	public static Location thawFromFile(final String filename) throws IOException,
			ClassNotFoundException {
		FileInputStream fis = new FileInputStream(filename);
		ObjectInputStream in = new ObjectInputStream(fis);
		Location newObj = (Location) in.readObject();
		in.close();
		return newObj;
	}
	

	/** {@inheritDoc} */
	@Override
	public Location clone(final IItem toClone) throws CloneNotSupportedException {
		return (Location) super.clone(toClone);
	}
	
}
