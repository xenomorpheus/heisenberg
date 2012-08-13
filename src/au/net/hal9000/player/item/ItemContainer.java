package au.net.hal9000.player.item;

import java.io.Serializable;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.Vector;

import au.net.hal9000.player.item.exception.*;
import au.net.hal9000.player.units.*;

public abstract class ItemContainer extends Item implements Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private Stack<Item> contents = new Stack<Item>();

    public ItemContainer(String string) {
	super(string);
    }

    // Getters and Setters

    public Stack<Item> getContents() {
	return contents;
    }

    private void setContents(Stack<Item> contents) {
	this.contents = contents;
    }

    // Misc
    /** {@inheritDoc} */
    public boolean isLeaf() {
	return false;
    }

    /** {@inheritDoc} */
    public int getChildCount() {
	if (contents != null) {
	    return contents.size();
	}
	return 0;
    }

    /** {@inheritDoc} */
    public Item getChild(int index) {
	return contents.get(index);
    }

    /** {@inheritDoc} */
    @Override
    public int getIndexOfChild(IItem child) {
	// TODO getIndexOfChild
	return -1;
    }

    /** {@inheritDoc} */
    @Override
    public Stack<IItem> getChildren() {
	Stack<IItem> children = super.getChildren();
	// TODO what about items in contents ?
	return children;
    }

    /** Total weight, including contents. */
    @Override
    public float getWeight() {
	float total = this.getWeightBase();
	total += this.getContentsWeight();
	return total;
    }

    /** Total volume, including contents. */
    @Override
    public float getVolume() {
	float total = this.getVolumeBase();
	total += this.getContentsVolume();
	return total;
    }

    /** Total value, including contents. */
    @Override
    public Currency getValue() {
	Currency total = new Currency(this.getValueBase());
	total.add(this.getContentsValue());
	return total;
    }

    /**
     * Add the item to the contents.
     * 
     * @param item
     */
    public void add(Item item) {

	// check Weight
	float weightMax = this.getWeightMax();
	if (weightMax >= 0) {
	    float total = this.getContentsWeight();
	    total += item.getWeight();
	    if (total > weightMax) {
		throw new ExceptionTooHeavy("Adding " + item.getName()
			+ " weighing " + item.getWeight() + " will total "
			+ total + ", which is too heavy for " + this.getName()
			+ ", weightMax=" + weightMax);
	    }
	}

	// check Volume
	float volumeMax = this.getVolumeMax();
	if (volumeMax >= 0) {
	    float total = this.getContentsVolume();
	    total += item.getVolume();
	    if (total > volumeMax) {
		throw new ExceptionTooBig("Adding " + item.getName()
			+ " of volume " + item.getVolume() + " will total "
			+ total + ", which is too big for " + this.getName()
			+ ", volumeMax=" + volumeMax);
	    }
	}

	contents.add(item);
	item.setLocation(this);
    }

    /**
     * Add multiple items to the contents.
     * 
     * @param items
     */
    public void add(Vector<Item> items) {
	for (Item item : items) {
	    this.add(item);
	}
    }

    /**
     * Take the top item out of the contents.
     * 
     * @return the top item in the contents
     * @throws EmptyStackException
     */
    public IItem pop() throws EmptyStackException {
	return contents.pop();
    }

    /**
     * Peek at the top item of contents without removing it.
     * 
     * @return The top item.
     * @throws EmptyStackException
     */
    public IItem peek() throws EmptyStackException {
	return contents.peek();
    }

    /**
     * Empty the bag into this location
     * 
     * @param newLocation
     */
    public void empty(ItemContainer newLocation) {
	while (!contents.isEmpty()) {
	    Item item = contents.pop();
	    newLocation.add(item);
	}
    }

    /**
     * Get the number of items in the container.
     * 
     * @return the number of items directly inside the container. Items with
     *         other items don't add to the count as they are *NOT* directly
     *         contained.
     */
    public int getContentsCount() {
	return contents.size();
    }

    /**
     * Use getWeight() to get total including contents. Magic containers will
     * override getWeight().
     * 
     * @return the weight of just the contents.
     */
    private float getContentsWeight() {
	float total = 0;
	for (IItem iItem : getContents()) {
	    total += iItem.getWeight();
	}
	return total;
    }

    /**
     * Use getVolume() to get total including contents. Magic containers will
     * override getVolume().
     * 
     * @return the volume of just the contents.
     */
    private float getContentsVolume() {
	float total = 0;
	for (IItem iItem : getContents()) {
	    total += iItem.getVolume();
	}
	return total;
    }

    /**
     * Use getValue() to get total including contents. Magic containers will
     * override getValue().
     * 
     * @return the value of just the contents.
     */
    private Currency getContentsValue() {
	Currency total = new Currency();
	for (IItem iItem : getContents()) {
	    total.add(iItem.getValue());
	}
	return total;
    }

    // TODO rename Visitor Pattern style
    // Find contents that match the criteria
    public void accept(ItemVisitor visitor) {
	// Search the Items directly declared in this class.
	for (IItem iItem : getContents()) {
	    visitor.visit(iItem);
	}
	// Get super to do the rest.
	super.accept(visitor);
    }

    @Override
    public void beNot() {
	// Call beNot on the Items directly declared in this class.
	while (!contents.isEmpty()) {
	    IItem iItem = contents.pop();
	    iItem.beNot();
	}
	// Get super to do the rest.
	super.beNot();
    }

    public boolean equals(ItemContainer other) {
	// call equals on any super class.
	if (!super.equals(other))
	    return false;
	// Check each of our immediate properties.
	// check contents.
	{
	    Stack<Item> otherContents = other.getContents();
	    if (contents.size() != otherContents.size())
		return false;
	    for (int i = contents.size() - 1; i >= 0; i--) {
		if (!this.contents.get(i).equals(otherContents.get(i)))
		    return false;
	    }
	}
	return true;
    }

    @Override
    public ItemContainer clone(Item toClone) {
	ItemContainer clone = (ItemContainer) super.clone(toClone);

	// Make sure the cloning is deep, not shallow.
	// e.g. set the non-mutable, non-primitives

	// contents
	Stack<Item> contents = this.getContents();
	if (contents != null) {
	    Stack<Item> cloneContents = new Stack<Item>();
	    for (Item item : contents) {
		cloneContents.add(item.clone(item));
	    }
	    clone.setContents(cloneContents);
	}

	// location is *NOT* cloned.
	return clone;
    }
}
