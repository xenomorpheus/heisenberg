package au.net.hal9000.dnd.item;

import au.net.hal9000.dnd.units.*;

public interface Item {

	// Feature
	public boolean isMagical();

	public boolean isExtraDimensional();

	public boolean isHumanoidFood();

	public boolean isHumanoidMount();

	public boolean isRingWearer();

	public boolean isLiving();

	public boolean isClothing();

	public boolean isSharp();

	// Getters and Setters

	// name
	public String getName();

	public void setName(String pName);

	// description
	public String getDescription();

	public void setDescription(String pDescription);

	// weight related
	// weightBase - weight before carried items are added
	public Weight getWeightBase();

	public void setWeightBase(Weight baseWeight);

	public void setWeightBase(float baseWeight);

	// For simple items the weight is the weightBase.
	// will be overridden by collections
	public Weight getWeight();

	// weightMax - max weight that can be carried
	public Weight getWeightMax();

	public void setWeightMax(Weight loadMax);

	public void setWeightMax(float loadMax);

	// volume related
	// volumeBase - volume before carried items are added
	public Volume getVolumeBase();

	public void setVolumeBase(Volume volumeWeight);

	public void setVolumeBase(float volumeWeight);

	// For simple items the weight is the weightBase.
	// will be overridden by collections
	public Volume getVolume();

	// valueBase
	public Volume getVolumeMax();

	public Currency getValueBase();

	// For simple items the value is the valueBase.
	// will be overridden by collections
	public Currency getValue();

	public void setValueBase(Currency pValueBase);

	// location
	public Item getLocation();

	public void setLocation(Item location);

	// hitPoints
	public void setHitPoints(float hitPoints);

	public float getHitPoints();

	// misc methods

	// public boolean equals(Item other);

	// Attempt to unlink the item from everything so that
	// it can be garbage collected.
	// Won't work if anything is referencing this item.
	public void beNot();

	public String toString();

	public String detailedDescription();

	// Find items that match the criteria
	public void accept(ItemVisitor visitor);

	// TODO public void accept(Damage damage);

}
