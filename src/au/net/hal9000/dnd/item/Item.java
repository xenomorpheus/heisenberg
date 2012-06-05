package au.net.hal9000.dnd.item;

import java.lang.Math;
import java.util.Vector;

import au.net.hal9000.dnd.item.property.ItemSearch;

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

public abstract class Item {
	private String name;
	private String description = null;
	private float weightBase = 0F; // pounds
	private float volumeBase = 0F; // cubic feet
	private Currency valueBase = new Currency();
	private Item location = null;

	// List <Defence> defenceList = new List();
	// List <Protection> protectionList = new List();

	// Class methods
	public Item(String pName) {
		super();
		this.name = pName;
	}

	public Item(String pName, String pDescription) {
		super();
		this.name = pName;
		this.description = pDescription;
	}

	// Feature
	public static boolean isMagical() {
		return false;
	}

	public static boolean isExtraDimensional() {
		return false;
	}

	public static boolean isHumanoidFood() {
		return false;
	}

	public static boolean isHumanoidMount() {
		return false;
	}

	public static boolean isRingWearer() {
		return false;
	}

	public static boolean isLiving() {
		return false;
	}

	public static boolean isArmour() {
		return false;
	}

	public static boolean isClothing() {
		return false;
	}

	public static boolean isSharp() {
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

	public float getWeightBase() {
		return weightBase;
	}

	public void setWeightBase(float baseWeight) {
		this.weightBase = baseWeight;
	}

	// For simple items the weight is the weightBase.
	// will be overridden by collections
	public float getVolume() {
		return volumeBase;
	}

	public float getVolumeBase() {
		return volumeBase;
	}

	public void setVolumeBase(float volumeWeight) {
		this.volumeBase = volumeWeight;
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

	// misc methods

	// For simple items the weight is the weightBase.
	// will be overridden by collections
	public float getWeight() {
		return weightBase;
	}

	public boolean equals(Item other) {
		if (this == other)
			return true;
		if (!name.equals(other.name))
			return false;
		if (!description.equals(other.description))
			return false;
		if (Math.abs(weightBase - other.weightBase) >= 0.0001F)
			return false;
		if (!valueBase.equals(other.valueBase))
			return false;

		// location
		if (location == null) {
			if (other.location != null) {
				return false;
			}
		} else {
			if (other.location == null) {
				return false;
			}

			if (!location.equals(other.location)) {
				return false;
			}
		}

		return true;
	}

	// Attempt to unlink the item from everything so that
	// it can be garbage collected.
	// Won't work if anything is referencing this item.
	public void beNot() {
		this.name = null;
		this.description = null;
		this.valueBase = null;
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
	public void searchHelper(ItemSearch pSearch){
		pSearch.searchItem(this);
	}

}
