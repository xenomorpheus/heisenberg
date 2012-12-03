package au.net.hal9000.heisenberg.item;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import test.au.net.hal9000.heisenberg.util.ItemSplitByWeight;

import au.net.hal9000.heisenberg.item.property.SplitByWeight;

public class Water extends Item implements SplitByWeight  {
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
	public Water clone(IItem toClone) throws CloneNotSupportedException{
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

    /** {@inheritDoc} */
    @Override
    public Water splitByWeight(float newItemWeight) {
        return (Water)ItemSplitByWeight.splitByWeight(this, newItemWeight);
    }

}
