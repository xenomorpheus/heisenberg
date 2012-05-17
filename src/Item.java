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
	Boolean alive = false;
	Boolean extraDimensional = false;
	Boolean magical = false;
	// List <Defence> defenceList = new List();
	// List <Protection> protectionList = new List();
	
    Item(){}

    Item(String pName){
    	this.description = new String(pName);
    }
    
    Item(String pName, String pDescription){
    	this.name = new String(pName);
    	this.description = new String(pDescription);
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public void setValue(CoinCollection value) {
		this.value = value;
	}

	public Item getLocation() {
		return location;
	}

	public void setLocation(Item location) {
		this.location = location;
	}

	public Boolean getAlive() {
		return alive;
	}

	public void setAlive(Boolean alive) {
		this.alive = alive;
	}

	public Boolean getExtraDimensional() {
		return extraDimensional;
	}

	public void setExtraDimensional(Boolean extraDimensional) {
		this.extraDimensional = extraDimensional;
	}

	public Boolean getMagical() {
		return magical;
	}

	public void setMagical(Boolean magical) {
		this.magical = magical;
	}
	
	// TODO equals - all properties the same except location
}
