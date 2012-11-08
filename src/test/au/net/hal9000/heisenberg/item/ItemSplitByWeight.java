package test.au.net.hal9000.heisenberg.item;

import au.net.hal9000.heisenberg.item.property.SplitByWeight;

public abstract class ItemSplitByWeight extends Item implements SplitByWeight{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ItemSplitByWeight(String pName) {
		super(pName);
	}

	public ItemSplitByWeight(String pName, String pDescription) {
		super(pName, pDescription);
	}

	/** {@inheritDoc} */
	@Override
	public Water splitByWeight(float newItemWeight) {
		if (newItemWeight <= 0) {
			throw new IllegalArgumentException(
					"New weight must be strictly positive");
		}
	
		// New weight must be strictly less than current weight
		float oldItemWeight = getWeightBase();
		if (newItemWeight >= oldItemWeight) {
			throw new IllegalArgumentException(
					"New weight must be strictly less than current weight");
		}
	
		// Create new Item
		Water newWater = new Water();
		// Split old item by weight, volume, etc.
		newWater.setWeightBase(newItemWeight);
		float factor = newItemWeight / oldItemWeight;
		float oldItemVolume = getVolumeBase();
		newWater.setVolumeBase(oldItemVolume * factor);
	
		// Update old item weight, volume, etc
		setWeightBase(oldItemWeight - newWater.getWeightBase());
		setVolumeBase(oldItemVolume - newWater.getVolumeBase());
	
		return newWater;
	}

}