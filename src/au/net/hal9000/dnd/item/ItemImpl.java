package au.net.hal9000.dnd.item;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import au.net.hal9000.dnd.units.*;

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

public abstract class ItemImpl implements Item, Serializable {

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
	private Item location = null;
	private float hitPoints = 0F;

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

	// For simple items the weight is the weightBase.
	// will be overridden by collections
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

	// For simple items the value is the valueBase.
	// will be overridden by collections
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
		this.location = location;
	}

	public float getHitPoints() {
		return hitPoints;
	}

	public void setHitPoints(float hitPoints) {
		this.hitPoints = hitPoints;
	}

	// misc methods

	// For simple items the weight is the weightBase.
	// will be overridden by collections
	public Weight getWeight() {
		return weightBase;
	}

	private static boolean _null_safe_compare(Object o1, Object o2) {
		if (o1 == null) {
			if (o2 == null) {
				return true;
			}
			return false;
		}

		if (o2 == null) {
			return false;
		}

		return o1.equals(o2);
	}

	public boolean equals(ItemImpl other) {
		if (this == other)
			return true;
		// Check each of our immediate properties.
		if (!_null_safe_compare(name, other.getName())){
			System.out.println("name");
			return false;
		}
		if (!_null_safe_compare(description, other.getDescription())){
			System.out.println("des");
			return false;
		}
		if (!_null_safe_compare(weightBase, other.getWeightBase())){
			System.out.println("wb");
			return false;
		}
		if (!_null_safe_compare(weightMax, other.getWeightMax())){
			System.out.println("wn");
			return false;
		}
		if (!_null_safe_compare(volumeBase, other.getVolumeBase())){
			System.out.println("vb");
			return false;
		}
		if (!_null_safe_compare(volumeMax, other.getVolumeMax())){
			System.out.println("vm");
			return false;
		}
		if (!_null_safe_compare(valueBase, other.getValueBase())){
			System.out.println("valB");
			return false;
		}
		if (!_null_safe_compare(location, other.getLocation())){
			System.out.println("loc");
			return false;
		}
		if (Math.abs(hitPoints - other.getHitPoints()) > 0.0001F){
			System.out.println("hp");
			return false;
		}
		// call equals on any super class.
		return true;
	}

	// Attempt to unlink the item from everything so that
	// it can be garbage collected.
	// Won't work if anything is referencing this item.
	public void beNot() {
		System.out.println("beNot called on " + this.getName());
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

}
