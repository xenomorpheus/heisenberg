package au.net.hal9000.player.item;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Stack;

import au.net.hal9000.player.units.*;

//IItem:
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

public abstract class Item implements IItem, Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// set as many objects as possible.
	// we don't want to have to keep checking for null
	private String name = "";
	private String description = "";
	private Weight weightBase = null;
	private Weight weightMax = null;
	private Volume volumeBase = null;
	private Volume volumeMax = null;
	private Currency valueBase = new Currency();
	private Item location = null;
	private float hitPoints = 0F;
	/** Who owns this item. null means no-one. */
	private Item owner = null;

	// Class methods
	public Item(String pName) {
		super();
		this.name = pName;
	}

	public Item(String pName, String pDescription) {
		this(pName);
		this.description = pDescription;
	}

	// static methods
	public static boolean null_safe_equals(IItem iItem, IItem itemOther) {
		if (iItem == null) {
			if (itemOther == null) {
				return true;
			}
			return false;
		}

		if (itemOther == null) {
			return false;
		}

		return iItem.equals(itemOther);
	}

	// instance methods

	// Feature
	/** {@inheritDoc} */
	@Override
	public boolean isMagical() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isExtraDimensional() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isHumanoidFood() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isHumanoidMount() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isRingWearer() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isLiving() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isClothing() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isSharp() {
		return false;
	}

	// Getters and Setters
	/** {@inheritDoc} */
	@Override
	public String getName() {
		return name;
	}

	/** {@inheritDoc} */
	@Override
	public void setName(String pName) {
		this.name = pName;
	}

	/** {@inheritDoc} */
	@Override
	public String getDescription() {
		return description;
	}

	/** {@inheritDoc} */
	@Override
	public void setDescription(String pDescription) {
		this.description = pDescription;
	}

	/** {@inheritDoc} */
	@Override
	public Weight getWeightBase() {
		return weightBase;
	}

	/** {@inheritDoc} */
	@Override
	public void setWeightBase(Weight baseWeight) {
		this.weightBase = baseWeight;
	}

	/** {@inheritDoc} */
	@Override
	public void setWeightBase(float baseWeight) {
		this.weightBase = new Weight(baseWeight);
	}

	// weightMax - max weight that can be carried
	/** {@inheritDoc} */
	@Override
	public Weight getWeightMax() {
		return weightMax;
	}

	/** {@inheritDoc} */
	@Override
	public void setWeightMax(Weight weightMax) {
		this.weightMax = weightMax;
	}

	/** {@inheritDoc} */
	@Override
	public void setWeightMax(float weightMax) {
		this.weightMax = new Weight(weightMax);
	}

	/** {@inheritDoc} */
	@Override
	public Volume getVolume() {
		return volumeBase;
	}

	/** {@inheritDoc} */
	@Override
	public Volume getVolumeBase() {
		return volumeBase;
	}

	/** {@inheritDoc} */
	@Override
	public void setVolumeBase(Volume volumeBase) {
		this.volumeBase = volumeBase;
	}

	/** {@inheritDoc} */
	@Override
	public void setVolumeBase(float volumeBase) {
		if (this.volumeBase == null)
			this.volumeBase = new Volume(volumeBase);
		else
			this.volumeBase.setValue(volumeBase);
	}

	/** {@inheritDoc} */
	@Override
	public Volume getVolumeMax() {
		return volumeMax;
	}

	/** {@inheritDoc} */
	@Override
	public void setVolumeMax(Volume volumeMax) {
		this.volumeMax = volumeMax;
	}

	/** {@inheritDoc} */
	@Override
	public void setVolumeMax(float volumeMax) {
		if (this.volumeMax == null)
			this.volumeMax = new Volume(volumeMax);
		else
			this.volumeMax.setValue(volumeMax);
	}

	/** {@inheritDoc} */
	@Override
	public Currency getValueBase() {
		return valueBase;
	}

	/** {@inheritDoc} */
	@Override
	public Currency getValue() {
		return valueBase;
	}

	/** {@inheritDoc} */
	@Override
	public void setValueBase(Currency pValueBase) {
		this.valueBase = pValueBase;
	}

	/** {@inheritDoc} */
	@Override
	public IItem getLocation() {
		return this.location;
	}

	/** {@inheritDoc} */
	@Override
	public void setLocation(IItem location) {
		this.location = (Item) location;
	}

	/** {@inheritDoc} */
	@Override
	public float getHitPoints() {
		return hitPoints;
	}

	/** {@inheritDoc} */
	@Override
	public void setHitPoints(float hitPoints) {
		this.hitPoints = hitPoints;
	}

	/** {@inheritDoc} */
	@Override
	public Item getOwner() {
		return owner;
	}

	/** {@inheritDoc} */
	@Override
	public void setOwner(IItem owner) {
		this.owner = (Item) owner;
	}

	// misc methods

	/** {@inheritDoc} */
	@Override
	public Weight getWeight() {
		return weightBase;
	}

	/**
	 * Items are considered equal if all their properties are the same except
	 * for location.
	 */
	public boolean equals(Item other) {
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
		/*
		 * We can't check the location because checking of deep persistence will
		 * break. Nested objects will have different locations.
		 */

		// if (!Item.null_safe_equals(location, other.getLocation()))
		// return false;
		if (Math.abs(hitPoints - other.getHitPoints()) > 0.0001F)
			return false;
		// call equals on any super class.
		return true;
	}

	/** {@inheritDoc} */
	@Override
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

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + ": " + name;
	}

	/** {@inheritDoc} */
	@Override
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
		IItem location = this.getLocation();
		if (location != null) {
			str = str.concat("Location: " + location.getName() + "\n");
		}
		return str;
	}

	// Find items that match the criteria
	/** {@inheritDoc} */
	@Override
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

	/** {@inheritDoc} */
	@Override
	protected Item clone() throws CloneNotSupportedException {
		Item clone = (Item) super.clone();

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
	@Override
	public boolean isLeaf() {
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public int getChildCount() {
		return 0;
	}

	/** {@inheritDoc} */
	@Override
	public IItem getChild(int index) {
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public int getIndexOfChild(IItem child) {
		return -1;
	}

	/** {@inheritDoc} */
	@Override
	public Stack<IItem> getChildren() {
		return new Stack<IItem>();
	}
}
