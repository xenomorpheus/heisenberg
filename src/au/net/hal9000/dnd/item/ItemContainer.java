package au.net.hal9000.dnd.item;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Stack;

import au.net.hal9000.dnd.item.exception.*;
import au.net.hal9000.dnd.item.property.ItemSearch;

// TODO make abstract
public class ItemContainer extends Item {
	float weightMax = -1F; // -1 unlimited
	float volumeMax = -1F; // -1 unlimited
	Stack<Item> items = new Stack<Item>();

	ItemContainer(String string) {
		super(string);
	}

	// Getters and Setters
	public float getWeightMax() {
		return weightMax;
	}

	public void setWeightMax(float weightMax) {
		this.weightMax = weightMax;
	}

	public void setVolumeMax(float volumeMax) {
		this.volumeMax = volumeMax;
	}

	public float getVolumeMax() {
		return volumeMax;
	}

	public Stack<Item> getItems() {
		return items;
	}

	public void setItems(Stack<Item> items) {
		this.items = items;
	}

	// Misc

	// Includes contents
	public float getWeight() {
		float total = this.getWeightBase();
		total += this.getContentsWeight();
		return total;
	}

	// Includes contents
	public float getVolume() {
		float total = this.getVolumeBase();
		total += this.getContentsVolume();
		return total;
	}

	// Includes contents
	public Currency getValue() {
		Currency total = new Currency(this.getValueBase());
		total.add(this.getContentsValue());
		return total;
	}

	public void add(Item item) throws ExceptionTooHeavy, ExceptionTooBig,
			ExceptionInvalidType {
		if (weightMax >= 0) {
			float total = item.getWeight() + this.getContentsWeight();
			if (total > this.weightMax) {
				throw new ExceptionTooHeavy("Adding " + item.getName()
						+ " weighing " + item.getWeight() + " will total "
						+ total + ", which is too heavy for " + this.getName()
						+ ", weightMax=" + this.weightMax);
			}
		}
		if (volumeMax >= 0) {
			float total = item.getVolume() + this.getContentsVolume();
			if (total > this.volumeMax) {
				throw new ExceptionTooBig("Adding " + item.getName()
						+ " of volume " + item.getVolume() + " will total "
						+ total + ", which is too big for " + this.getName()
						+ ", volumeMax=" + this.volumeMax);
			}
		}

		items.add(item);
		item.setLocation(this);
	}

	public Item pop() throws EmptyStackException {
		return items.pop();
	}

	public Item peek() throws EmptyStackException {
		return items.peek();
	}

	// Empty the bag into this location
	public void empty(Item newLocation) {
		Iterator<Item> itr = getItems().iterator();
		while (itr.hasNext()) {
			itr.next().setLocation(newLocation);
		}
	}

	// Warning
	// Use getWeight() to get total including contents.
	// Magic containers will override getWeight().
	public float getContentsWeight() {
		float total = 0F;
		Iterator<Item> itr = this.getItems().iterator();
		while (itr.hasNext()) {
			total += itr.next().getWeight();
		}
		return total;
	}

	// Warning
	// Use getVolume() to get total including contents.
	// Magic containers will override getVolume().
	public float getContentsVolume() {
		float total = 0F;
		Iterator<Item> itr = this.getItems().iterator();
		while (itr.hasNext()) {
			total += itr.next().getVolume();
		}
		return total;
	}

	public Currency getContentsValue() {
		Currency total = new Currency();
		Iterator<Item> itr = this.getItems().iterator();
		while (itr.hasNext()) {
			total.add(itr.next().getValue());
		}
		return total;
	}

	// TODO equal

	// TODO rename visitor framework style
	// Find items that match the criteria
	public void searchHelper(ItemSearch pSearch) {
		// Search the Items directly declared in this class.
		Iterator<Item> itr = this.getItems().iterator();
		while (itr.hasNext()) {
			pSearch.searchItem(itr.next());
		}
		// Get super to do the rest.
		super.searchHelper(pSearch);
	}

	public void beNot() {
		// Call beNot on the Items directly declared in this class.
		Iterator<Item> itr = this.getItems().iterator();
		while (itr.hasNext()) {
			itr.next().beNot();
		}
		// Get super to do the rest.
		super.beNot();
	}

}
