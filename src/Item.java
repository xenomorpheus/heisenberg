import java.lang.Math;

//Item:
//* An item has a name which is text.
//* An item has a description which is text.
//* An item has a weight which is measured in pounds which defaults to zero.
//* An item has a value measured in coins which defaults to zero.
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
	String name = "";
	String description = "";
	float baseWeight = 0F;
	CoinCollection value = new CoinCollection();
	Item location = null;
	boolean alive = false;
	boolean extraDimensional = false;
	boolean magical = false;

	// List <Defence> defenceList = new List();
	// List <Protection> protectionList = new List();

	Item() {
	}

	Item(String pName) {
		this.name = new String(pName);
	}

	Item(String pName, String pDescription) {
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

	// For simple items the weight is the baseWeight.
	public float getWeight() {
		return baseWeight;
	}

	public float getBaseWeight() {
		return baseWeight;
	}

	public void setBaseWeight(float baseWeight) {
		this.baseWeight = baseWeight;
	}

	public CoinCollection getValue() {
		return value;
	}

	public void setValue(CoinCollection pValue) {
		this.value = new CoinCollection(pValue);
	}

	public Item getLocation() {
		return location;
	}

	public void setLocation(Item location) {
		this.location = location;
	}

	public boolean getAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public boolean getExtraDimensional() {
		return extraDimensional;
	}

	public void setExtraDimensional(boolean extraDimensional) {
		this.extraDimensional = extraDimensional;
	}

	public boolean getMagical() {
		return magical;
	}

	public void setMagical(boolean magical) {
		this.magical = magical;
	}

	public boolean equals(Item other) {
		if (this == other)
			return true;
		if (!name.equals(other.name))
			return false;
		if (!description.equals(other.description))
			return false;
		if (Math.abs(baseWeight - other.baseWeight) >= 0.0001F)
			return false;
		if (!value.equals(other.value))
			return false;
		if (alive != other.alive)
			return false;
		if (extraDimensional != other.extraDimensional)
			return false;
		if (magical != other.magical)
			return false;
		// location not important
		// if (location != other.location)
		// return false;
		return true;
	}
}
