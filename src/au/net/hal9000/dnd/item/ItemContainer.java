package au.net.hal9000.dnd.item;

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.Vector;
import au.net.hal9000.dnd.units.*;

import au.net.hal9000.dnd.item.exception.*;

public abstract class ItemContainer extends ItemImpl {
	private Stack<Item> items = new Stack<Item>();

	public ItemContainer(String string) {
		super(string);
	}

	// Getters and Setters

	protected Stack<Item> getContents() {
		return items;
	}

	// Misc

	// Includes contents
	public Weight getWeight() {
		Weight total = this.getWeightBase();
		total.add(this.getContentsWeight());
		return total;
	}

	// Includes contents
	public Volume getVolume() {
		Volume total = this.getVolumeBase();
		total.add(this.getContentsVolume());
		return total;
	}

	// Includes contents
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

		items.add(item);
		item.setLocation(this);
	}

	// add multiple items
	public void add(Vector<Item> items) {
		for (Item item : items) {
			this.add(item);
		}
	}

	public Item pop() throws EmptyStackException {
		return items.pop();
	}

	public Item peek() throws EmptyStackException {
		return items.peek();
	}

	// Empty the bag into this location
	public void empty(Item newLocation) {
		while (!items.isEmpty()) {
			Item item = items.pop();
			item.setLocation(newLocation);
		}
	}

	public int getContentsCount() {
		return items.size();
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
	// Find items that match the criteria
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
		while (!items.isEmpty()) {
			Item item = items.pop();
			item.beNot();
		}
		// Get super to do the rest.
		super.beNot();
	}

}
