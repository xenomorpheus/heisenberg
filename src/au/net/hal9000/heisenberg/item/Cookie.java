package au.net.hal9000.heisenberg.item;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import au.net.hal9000.heisenberg.item.property.HumanoidFood;

public class Cookie extends Item implements HumanoidFood{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Cookie() {
		this("Cookie");
	}

	public Cookie(String pName) {
		super(pName);
	}

	public Cookie(String pName, String pDescription) {
		super(pName, pDescription);
	}

	/** {@inheritDoc} */
	@Override
	public Cookie clone(Item toClone) {
		return (Cookie) super.clone(toClone);
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
