package au.net.hal9000.heisenberg.item;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Stack;

import au.net.hal9000.heisenberg.units.*;

//IItem:
//* An item has a name which is text.
//* An item has a description which is text.
//* An item has a weight which is measured in pounds which defaults to zero.
//* An item has a valueBase measured in coins which defaults to zero.
//* An item has a location (e.g. ground, a bag, an arm)
//* (discuss) An item has a condition (0-100) 100=new, 0=worn away.  Or
//hit points?
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
    private static final float WITHIN_MARGIN = 0.00009F;
    // set as many objects as possible.
    /** name may not be null */
    private String name = "";
    /** description may not be null */
    private String description = "";
    private float weightBase = 0;
    private float volumeBase = 0;
    private Currency valueBase = new Currency();
    private Item location = null;
    private float hitPoints = 0F;
    /** Who owns this item. null means no-one. */
    private Item owner = null;

    // Class methods
    public Item(final String pName) {
        super();
        this.name = pName;
    }

    public Item(final String pName, final String pDescription) {
        this(pName);
        this.description = pDescription;
    }

    /** {@inheritDoc} */
    @Override
	public Item clone() throws CloneNotSupportedException {
        Item clone = (Item) super.clone();
        this.clone(clone);
        return clone;
    }

    /** {@inheritDoc} */
    @Override
    public Item clone(Item clone) {

        // Make sure the cloning is deep, not shallow.
        // e.g. set the non-mutable, non-primitives

        // valueBase
        final Currency valueBase = this.getValueBase();
        if (valueBase == null) {
            clone.setValueBase(null);
        } else {
            try {
                clone.setValueBase(valueBase.clone());
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException("Failed to clone Currency");
            }
        }

        // location is *NOT* cloned.
        return clone;

    }

    // static methods

    // instance methods

    // Getters and Setters
    /** {@inheritDoc} */
    @Override
    public String getName() {
        return name;
    }

    /** {@inheritDoc} */
    @Override
    public void setName(final String pName) {
        if (pName == null) {
            throw new IllegalArgumentException("Not null");
        }
        this.name = pName;
    }

    /** {@inheritDoc} */
    @Override
    public String getDescription() {
        return description;
    }

    /** {@inheritDoc} */
    @Override
    public void setDescription(final String pDescription) {
        if (pDescription == null) {
            throw new IllegalArgumentException("Not null");
        }
        this.description = pDescription;
    }

    /** {@inheritDoc} */
    @Override
    public float getWeightBase() {
        return weightBase;
    }

    /** {@inheritDoc} */
    @Override
    public void setWeightBase(final float baseWeight) {
        this.weightBase = baseWeight;
    }

    /** {@inheritDoc} */
    @Override
    public float getVolume() {
        return volumeBase;
    }

    /** {@inheritDoc} */
    @Override
    public float getVolumeBase() {
        return volumeBase;
    }

    /** {@inheritDoc} */
    @Override
    public void setVolumeBase(float volumeBase) {
        this.volumeBase = volumeBase;
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
    public void setValueBase(final Currency pValueBase) {
        if (pValueBase == null) {
            throw new IllegalArgumentException("Not null");
        }
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
    public float getWeight() {
        return weightBase;
    }

    /**
     * Items are considered equal if all their properties are the same except
     * for location.
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (this == other) {
            return true;
        }
        if (!(other instanceof Item)) {
            return false;
        }
        Item otherItem = (Item) other;

        // Check each of our immediate properties.
        // name
        if (!name.equals(otherItem.getName())) {
            return false;
        }
        // description
        if (!description.equals(otherItem.getDescription())) {
            return false;
        }
        if (Math.abs(weightBase - otherItem.getWeightBase()) >= WITHIN_MARGIN)
            return false;
        if (Math.abs(volumeBase - otherItem.getVolumeBase()) >= WITHIN_MARGIN)
            return false;
        if (!(valueBase.equals(otherItem.getValueBase()))) {
            return false;
        }
        /*
         * We can't check the location because checking of deep persistence will
         * break. Nested objects will have different locations.
         */

        // if (!Item.null_safe_equals(location, other.getLocation()))
        // return false;
        if (Math.abs(hitPoints - otherItem.getHitPoints()) > 0.0001F)
            return false;
        // call equals on any super class.
        return true;
    }

    /** {@inheritDoc} */
    @Override
    public void beNot() {
    	// TODO - Help Required - How do I delete an object that
    	// may be referenced by other objects?
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

    // TODO what about Weight/Volume/Currency etc
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    // TODO what about Weight/Volume/Currency etc
    private void readObject(ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        // our "pseudo-constructor"
        in.defaultReadObject();
        // now we are a "live" object again, so let's run rebuild and start
    }

    // Store the object in a file
    public void freezeToFile(String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(this);
        out.close();
    }

    // TODO thawFormFile

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
