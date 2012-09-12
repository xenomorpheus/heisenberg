package au.net.hal9000.heisenberg.item;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import au.net.hal9000.heisenberg.item.property.HumanoidFood;

public class Wood extends Item {
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
	public Wood clone(Item toClone) {
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

}
