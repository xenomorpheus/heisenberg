package au.net.hal9000.heisenberg.item;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import au.net.hal9000.heisenberg.item.property.SplitByWeight;

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

    /** {@inheritDoc} */
    @Override
	public Water splitByWeight(float newItemWeight) {
		// TODO Auto-generated method stub
		if (newItemWeight <= 0) {
			throw new IllegalArgumentException(
					"weight must be strictly positive");
		}
		Water newWater = null;
		// New weight must be strictly less than current weight
		float oldItemWeight = getWeightBase();
		if (newItemWeight < oldItemWeight) {
			// Create new Item
			newWater = new Water();
			// Split old item by weight, volume, etc.
			newWater.setWeightBase(newItemWeight);
			float factor = newItemWeight / oldItemWeight;
			float oldItemVolume = getVolumeBase();
			newWater.setVolumeBase(oldItemVolume * factor);

			// Update new item weight, volume, etc
			setWeightBase(oldItemWeight - newWater.getWeightBase());
			setVolumeBase(oldItemVolume - newWater.getVolumeBase());
		}

		return newWater;
	}

}
