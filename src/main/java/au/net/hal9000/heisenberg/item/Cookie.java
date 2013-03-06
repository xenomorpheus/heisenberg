package au.net.hal9000.heisenberg.item;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


//Persistence
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

// Custom
import au.net.hal9000.heisenberg.item.property.ItemProperty;


@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name="ID", referencedColumnName="ID")
public class Cookie extends Item {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Cookie() {
		this("Cookie");
	}

	public Cookie(String pName) {
		super(pName);
		ItemProperty.setHumanoidFood(this, true);
	}

	public Cookie(String pName, String pDescription) {
		super(pName, pDescription);
	}

	// Methods

	// Static
	public static Cookie thawFromFile(String filename) throws IOException,
			ClassNotFoundException {
		FileInputStream fis = new FileInputStream(filename);
		ObjectInputStream in = new ObjectInputStream(fis);
		Cookie newObj = (Cookie) in.readObject();
		in.close();
		return newObj;
	}

}
