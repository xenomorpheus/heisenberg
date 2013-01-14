package au.net.hal9000.heisenberg.item;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name="ID", referencedColumnName="ID")
public class FlintAndTinder extends Item {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FlintAndTinder() {
		this("FlintAndTinder");
	}

	public FlintAndTinder(String pName) {
		this(pName, "some flint and tinder");
	}

	public FlintAndTinder(String pName, String pDescription) {
		super(pName, pDescription);
	}

	/** {@inheritDoc} */
	@Override
	public FlintAndTinder clone(IItem toClone) throws CloneNotSupportedException {
		return (FlintAndTinder) super.clone(toClone);
	}

	// Methods

	// Static
	public static FlintAndTinder thawFromFile(String filename) throws IOException,
			ClassNotFoundException {
		FileInputStream fis = new FileInputStream(filename);
		ObjectInputStream in = new ObjectInputStream(fis);
		FlintAndTinder newObj = (FlintAndTinder) in.readObject();
		in.close();
		return newObj;
	}

}
