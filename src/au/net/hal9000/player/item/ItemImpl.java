package au.net.hal9000.player.item;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import au.net.hal9000.player.units.*;

//Item:
//* An item has a name which is text.
//* An item has a description which is text.
//* An item has a weight which is measured in pounds which defaults to zero.
//* An item has a valueBase measured in coins which defaults to zero.
//* An item has a location (e.g. ground, a bag, an arm)
//* (discuss) An item has a condition (0-100) 100=new, 0=worn away.  Or
//hit points?
//* An item by default is not alive.
//* An item by default is not extra-dimensional.
//* An item by default is not magical.
//* An item by default does not have a defence (e.g. resistance to fire)
//* An item may have any number of defences.
//* An item by default does not offer protection (e.g armour, magic
//resistance)
//* An item may offer any number of protections.
//Actions
//* An item may be dropped, which will cause the location to be changed to
//the ground below the object.
//* An item may be damaged by fire which will...
//* An item may be damaged by acid which will...
//* An item may be repaired which will ...

public abstract class ItemImpl implements Item, Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// set as many objects as possible.
	// we don't want to have to keep checking for null
	private String name = "";
	private String description = "";
	private Weight weightBase = new Weight();
	private Weight weightMax = new Weight();
	private Volume volumeBase = new Volume();
	private Volume volumeMax = new Volume();
	private Currency valueBase = new Currency();
	private ItemImpl location = null;
	private float hitPoints = 0F;
	/** Who owns this item. null means no-one. */
	private ItemImpl owner = null;

	// Class methods
	public ItemImpl(String pName) {
		super();
		this.name = pName;
	}

	public ItemImpl(String pName, String pDescription) {
		super();
		this.name = pName;
		this.description = pDescription;
	}

	// static methods
	public static boolean null_safe_equals(Item item, Item itemOther) {
		if (item == null) {
			if (itemOther == null) {
				return true;
			}
			return false;
		}

		if (itemOther == null) {
			return false;
		}

		return item.equals(itemOther);
	}

	// instance methods

	// Feature

	public boolean isMagical() {
		return false;
	}

	public boolean isExtraDimensional() {
		return false;
	}

	public boolean isHumanoidFood() {
		return false;
	}

	public boolean isHumanoidMount() {
		return false;
	}

	public boolean isRingWearer() {
		return false;
	}

	public boolean isLiving() {
		return false;
	}

	public boolean isDefence() {
		return false;
	}

	public boolean isClothing() {
		return false;
	}

	public boolean isSharp() {
		return false;
	}

	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String pName) {
		this.name = pName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String pDescription) {
		this.description = pDescription;
	}

	public Weight getWeightBase() {
		return weightBase;
	}

	public void setWeightBase(Weight baseWeight) {
		this.weightBase = baseWeight;
	}

	public void setWeightBase(float baseWeight) {
		this.weightBase = new Weight(baseWeight);
	}

	// weightMax - max weight that can be carried
	public Weight getWeightMax() {
		return weightMax;
	}

	public void setWeightMax(Weight weightMax) {
		this.weightMax = weightMax;
	}

	public void setWeightMax(float weightMax) {
		this.weightMax = new Weight(weightMax);
	}

	/**
	 * For simple items the volume is the volumeBase. Will be overridden by
	 * collections.
	 * 
	 */
	public Volume getVolume() {
		return volumeBase;
	}

	public Volume getVolumeBase() {
		return volumeBase;
	}

	public void setVolumeBase(Volume volumeBase) {
		this.volumeBase = volumeBase;
	}

	public void setVolumeBase(float volumeBase) {
		if (this.volumeBase == null)
			this.volumeBase = new Volume(volumeBase);
		else
			this.volumeBase.setValue(volumeBase);
	}

	public Volume getVolumeMax() {
		return volumeMax;
	}

	public void setVolumeMax(Volume volumeMax) {
		this.volumeMax = volumeMax;
	}

	public void setVolumeMax(float volumeMax) {
		if (this.volumeMax == null)
			this.volumeMax = new Volume(volumeMax);
		else
			this.volumeMax.setValue(volumeMax);
	}

	public Currency getValueBase() {
		return valueBase;
	}

	/**
	 * For simple items the value is the valueBase. Will be overridden by
	 * collections
	 * 
	 */
	public Currency getValue() {
		return valueBase;
	}

	public void setValueBase(Currency pValueBase) {
		this.valueBase = pValueBase;
	}

	public Item getLocation() {
		return this.location;
	}

	public void setLocation(Item location) {
		this.location = (ItemImpl) location;
	}

	public float getHitPoints() {
		return hitPoints;
	}

	public void setHitPoints(float hitPoints) {
		this.hitPoints = hitPoints;
	}

	public ItemImpl getOwner() {
		return owner;
	}

	public void setOwner(Item owner) {
		this.owner = (ItemImpl) owner;
	}

	// misc methods

	/**
	 * For simple items the weight is the weightBase. Will be overridden by
	 * collections.
	 * 
	 */
	public Weight getWeight() {
		return weightBase;
	}

	public boolean equals(ItemImpl other) {
		if (this == other)
			return true;
		// Check each of our immediate properties.
		// name
		{
			String otherName = other.getName();
			if (name == null) {
				if (other != null)
					return false;
			} else {
				if (otherName == null)
					return false;
				if (!name.equals(otherName))
					return false;
			}
		}
		// description
		{
			String otherDescription = other.getDescription();
			if (description == null) {
				if (other != null)
					return false;
			} else {
				if (otherDescription == null)
					return false;
				if (!description.equals(otherDescription))
					return false;
			}
		}
		if (!Weight.null_safe_equals(weightBase, other.getWeightBase()))
			return false;
		if (!Weight.null_safe_equals(weightMax, other.getWeightMax()))
			return false;
		if (!Volume.null_safe_equals(volumeBase, other.getVolumeBase()))
			return false;
		if (!Volume.null_safe_equals(volumeMax, other.getVolumeMax()))
			return false;
		if (!Currency.null_safe_equals(valueBase, other.getValueBase()))
			return false;
		if (!ItemImpl.null_safe_equals(location, other.getLocation()))
			return false;
		if (Math.abs(hitPoints - other.getHitPoints()) > 0.0001F)
			return false;
		// call equals on any super class.
		return true;
	}

	// Attempt to unlink the item from everything so that
	// it can be garbage collected.
	// Won't work if anything is referencing this item.
	public void beNot() {
		// System.out.println("beNot called on " + this.getName());
		this.name = null;
		this.description = null;
		this.weightBase = null;
		this.weightMax = null;
		this.volumeBase = null;
		this.volumeMax = null;
		this.valueBase = null;
		// We don't call beNot on the location.
		this.location = null;
	}

	public String toString() {
		return name;
	}

	public String detailedDescription() {
		String str = new String();
		String temp;

		temp = this.getName();
		if (temp != null) {
			str = str.concat("Name: " + temp + "\n");
		}
		temp = this.getDescription();
		if (temp != null) {
			str = str.concat("Description: " + temp + "\n");
		}
		str = str.concat("Weight Base: " + this.getWeightBase() + "\n"
				+ "Volume Base: " + this.getVolumeBase() + "\n");

		Currency valueBase = this.getValueBase();
		if (valueBase != null) {
			str = str.concat("Value Base: " + valueBase + "\n");
		}
		Item location = this.getLocation();
		if (location != null) {
			str = str.concat("Location: " + location.getName() + "\n");
		}
		return str;
	}

	// Find items that match the criteria
	public void accept(ItemVisitor visitor) {
		visitor.visit(this);
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
	}

	private void readObject(ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		// our "pseudo-constructor"
		in.defaultReadObject();
		// now we are a "live" object again, so let's run rebuild and start
	}

	@Override
	protected ItemImpl clone() throws CloneNotSupportedException {
		ItemImpl clone = (ItemImpl) super.clone();

		// Make sure the cloning is deep, not shallow.
		// e.g. set the non-mutable, non-primitives

		// weightBase
		Weight weightBase = this.getWeightBase();
		if (weightBase != null) {
			clone.setWeightBase(weightBase.clone());
		}

		// weightMax
		Weight weightMax = this.getWeightMax();
		if (weightMax != null) {
			clone.setWeightMax(weightMax.clone());
		}

		// volumeBase
		Volume volumeBase = this.getVolumeBase();
		if (volumeBase != null) {
			clone.setVolumeBase(volumeBase.clone());
		}

		// volumeMax
		Volume volumeMax = this.getVolumeMax();
		if (volumeMax != null) {
			clone.setVolumeMax(volumeMax.clone());
		}

		// valueBase
		Currency valueBase = this.getValueBase();
		if (valueBase != null) {
			clone.setValueBase(valueBase.clone());
		}

		// location is *NOT* cloned.
		return clone;

	}

	// Store the object in a file
	public void freezeToFile(String filename) throws IOException {
		FileOutputStream fos = new FileOutputStream(filename);
		ObjectOutputStream out = new ObjectOutputStream(fos);
		out.writeObject(this);
		out.close();
	}

	/** {@inheritDoc} */
	public boolean isLeaf() {
		return true;
	}

	/** {@inheritDoc} */
	public int getChildCount() {
		return 0;
	}

	/** {@inheritDoc} */
	public Item getChild(int index){
		return null;
	}

	/** {@inheritDoc} */
	public int getIndexOfChild(Item child){
		return -1;
	}
}
