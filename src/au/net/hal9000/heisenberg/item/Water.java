package au.net.hal9000.heisenberg.item;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Water extends ItemSplitByWeight  {
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

	/** {@inheritDoc} */
	@Override
	public Water clone(Item toClone) {
		return (Water) super.clone(toClone);
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

}
