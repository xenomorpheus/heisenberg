package au.net.hal9000.dnd.item;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;
import au.net.hal9000.dnd.units.*;

import au.net.hal9000.dnd.item.exception.*;

public abstract class ItemContainer extends ItemImpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Stack<Item> contents = new Stack<Item>();

	public ItemContainer(String string) {
		super(string);
	}

	// Getters and Setters

	protected Stack<Item> getContents() {
		return contents;
	}

	private void setContents(Stack<Item> contents) {
		this.contents = contents;
	}

	// Misc

	// Includes contents
	@Override
	public Weight getWeight() {
		Weight total = this.getWeightBase();
		total.add(this.getContentsWeight());
		return total;
	}

	// Includes contents
	@Override
	public Volume getVolume() {
		Volume total = this.getVolumeBase();
		total.add(this.getContentsVolume());
		return total;
	}

	// Includes contents
	@Override
	public Currency getValue() {
		Currency total = new Currency(this.getValueBase());
		total.add(this.getContentsValue());
		return total;
	}

	public void add(Item item) {

		// check Weight
		Weight weightMax = this.getWeightMax();
		if (weightMax != null) {
			Weight total = this.getContentsWeight();
			total.add(item.getWeight());
			if (total.compare(weightMax) > 0) {
				throw new ExceptionTooHeavy("Adding " + item.getName()
						+ " weighing " + item.getWeight() + " will total "
						+ total + ", which is too heavy for " + this.getName()
						+ ", weightMax=" + weightMax.getValue());
			}
		}

		// check Volume
		Volume volumeMax = this.getVolumeMax();
		if (volumeMax != null) {
			Volume total = this.getContentsVolume();
			total.add(item.getVolume());
			if (total.compare(volumeMax) > 0) {
				throw new ExceptionTooBig("Adding " + item.getName()
						+ " of volume " + item.getVolume() + " will total "
						+ total + ", which is too big for " + this.getName()
						+ ", volumeMax=" + volumeMax.getValue());
			}
		}

		contents.add(item);
		item.setLocation(this);
	}

	// add multiple items
	public void add(Vector<Item> items) {
		for (Item item : items) {
			this.add(item);
		}
	}

	public Item pop() throws EmptyStackException {
		return contents.pop();
	}

	public Item peek() throws EmptyStackException {
		return contents.peek();
	}

	// Empty the bag into this location
	public void empty(Item newLocation) {
		while (!contents.isEmpty()) {
			Item item = contents.pop();
			item.setLocation(newLocation);
		}
	}

	public int getContentsCount() {
		return contents.size();
	}

	// Warning
	// Use getWeight() to get total including contents.
	// Magic containers will override getWeight().
	public Weight getContentsWeight() {
		Weight total = new Weight();
		for (Item item : getContents()) {
			total.add(item.getWeight());
		}
		return total;
	}

	// Warning
	// Use getVolume() to get total including contents.
	// Magic containers will override getVolume().
	public Volume getContentsVolume() {
		Volume total = new Volume();
		for (Item item : getContents()) {
			total.add(item.getVolume());
		}
		return total;
	}

	public Currency getContentsValue() {
		Currency total = new Currency();
		for (Item item : getContents()) {
			total.add(item.getValue());
		}
		return total;
	}

	// TODO equal

	// TODO rename Visitor Pattern style
	// Find contents that match the criteria
	public void accept(ItemVisitor visitor) {
		// Search the Items directly declared in this class.
		for (Item item : getContents()) {
			visitor.visit(item);
		}
		// Get super to do the rest.
		super.accept(visitor);
	}

	public void beNot() {
		// Call beNot on the Items directly declared in this class.
		while (!contents.isEmpty()) {
			Item item = contents.pop();
			item.beNot();
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
			Iterator<Item> itr = this.contents.iterator();
			Iterator<Item> otherItr = otherContents.iterator();
			while(itr.hasNext()){
				Item item = itr.next();
				Item otherItem = otherItr.next();
				// TODO - which equals is run? Wrong class.
                if (! item.equals(otherItem))				
                	return false;
			}
		}
		return true;
	}

	@Override
	protected ItemContainer clone() throws CloneNotSupportedException {
		ItemContainer clone = (ItemContainer) super.clone();

		// Make sure the cloning is deep, not shallow.
		// e.g. set the non-mutable, non-primitives

		// contents
		Stack<Item> contents = this.getContents();
		if (contents != null) {
			Stack<Item> cloneContents = new Stack<Item>();
			for (Item item : contents) {
				// TODO check this.
				ItemImpl itemImpl = (ItemImpl) item;
				cloneContents.add(itemImpl.clone());
			}
			clone.setContents(cloneContents);
		}

		// location is *NOT* cloned.
		return clone;
	}
}
