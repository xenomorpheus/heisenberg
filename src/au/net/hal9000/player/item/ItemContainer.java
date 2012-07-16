package au.net.hal9000.player.item;

import java.io.Serializable;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.Vector;

import au.net.hal9000.player.item.exception.*;
import au.net.hal9000.player.units.*;

public abstract class ItemContainer extends ItemImpl implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Stack<ItemImpl> contents = new Stack<ItemImpl>();

	public ItemContainer(String string) {
		super(string);
	}

	// Getters and Setters

	protected Stack<ItemImpl> getContents() {
		return contents;
	}

	private void setContents(Stack<ItemImpl> contents) {
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

	public void add(ItemImpl item) {

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
	public void add(Vector<ItemImpl> items) {
		for (ItemImpl item : items) {
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
	public void empty(ItemContainer newLocation) {
		while (!contents.isEmpty()) {
			ItemImpl item = contents.pop();
			newLocation.add(item);
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
			Stack<ItemImpl> otherContents = other.getContents();
			if (contents.size() != otherContents.size())
				return false;
			for (int i = contents.size() -1 ; i>= 0  ; i --){
               if ( ! this.contents.get(i).equals(otherContents.get(i)) )
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
		Stack<ItemImpl> contents = this.getContents();
		if (contents != null) {
			Stack<ItemImpl> cloneContents = new Stack<ItemImpl>();
			for (ItemImpl item : contents) {
				cloneContents.add(item.clone());
			}
			clone.setContents(cloneContents);
		}

		// location is *NOT* cloned.
		return clone;
	}
}
