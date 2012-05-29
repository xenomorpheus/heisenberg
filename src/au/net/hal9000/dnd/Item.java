package au.net.hal9000.dnd;
import java.lang.Math;

//Item:
//* An item has a name which is text.
//* An item has a description which is text.
//* An item has a weight which is measured in pounds which defaults to zero.
//* An item has a cost measured in coins which defaults to zero.
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

public class Item {
	String name;
	String description = "";
	float weightBase = 0F; // pounds
	float volumeBase = 0F; // cubic feet
	CoinCollection cost = new CoinCollection();
	Item location = null;

	// List <Defence> defenceList = new List();
	// List <Protection> protectionList = new List();

	public Item() {
		this.name = new String("Item");
	}

	public Item(String pName) {
		this.name = new String(pName);
	}

	public Item(String pName, String pDescription) {
		this.name = new String(pName);
		this.description = new String(pDescription);
	}

	public String getName() {
		return name;
	}

	public void setName(String pName) {
		this.name = new String(pName);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String pDescription) {
		this.description = new String(pDescription);
	}

	// For simple items the weight is the weightBase.
	public float getWeight() {
		return weightBase;
	}

	public float getWeightBase() {
		return weightBase;
	}

	public void setWeightBase(float baseWeight) {
		this.weightBase = baseWeight;
	}

	// For simple items the weight is the weightBase.
	public float getVolume() {
		return volumeBase;
	}

	public float getVolumeBase() {
		return volumeBase;
	}

	public void setVolumeBase(float volumeWeight) {
		this.volumeBase = volumeWeight;
	}

	public CoinCollection getCost() {
		return cost;
	}

	public void setCost(CoinCollection pCost) {
		this.cost = new CoinCollection(pCost);
	}

	public Item getLocation() {
		return location;
	}

	public void setLocation(Item location) {
		this.location = location;
	}

	public Boolean implementsInterface(Class interf) {
		for (Class c : getClass().getInterfaces()) {
			if (c.equals(interf)) {
				return true;
			}
		}
		return false;
	}

	public String toString() {
		return "Class: " + getClass().getName() + "\nName: " + name
				+ "\nCost: " + cost + "\nWeight: " + weightBase
				+ "\nDescription: " + description + "\n";
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
		if (!cost.equals(other.cost))
			return false;
		// location not important
		return true;
	}
}
