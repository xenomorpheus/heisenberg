package au.net.hal9000.dnd.item;

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.Vector;

import au.net.hal9000.dnd.item.exception.*;

// TODO make abstract
public class ItemContainer extends ItemSimple {
	private float weightMax = -1F; // -1 unlimited
	private float volumeMax = -1F; // -1 unlimited
	private Stack<Item> items = new Stack<Item>();

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

	protected Stack<Item> getContents() {
		return items;
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

	public void add(Item item) {
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
		while ( ! items.isEmpty() ){
			Item item = items.pop();
			item.setLocation(newLocation);
		}
	}

	public int getContentsCount(){
		return items.size();
	}
	
	// Warning
	// Use getWeight() to get total including contents.
	// Magic containers will override getWeight().
	public float getContentsWeight() {
		float total = 0F;
        for (Item item : getContents()){
			total += item.getWeight();
        }
		return total;
	}

	// Warning
	// Use getVolume() to get total including contents.
	// Magic containers will override getVolume().
	public float getContentsVolume() {
		float total = 0F;
		for (Item item : getContents()){
			total += item.getVolume();
		}
		return total;
	}

	public Currency getContentsValue() {
		Currency total = new Currency();
		for (Item item : getContents()){
			total.add(item.getValue());
		}
		return total;
	}

	// TODO equal

	// TODO rename Visitor Pattern style
	// Find items that match the criteria
	public void accept(ItemVisitor visitor) {
		// Search the Items directly declared in this class.
		for (Item item : getContents()){
			visitor.visit(item);
		}
		// Get super to do the rest.
		super.accept(visitor);
	}

	public void beNot() {
		// Call beNot on the Items directly declared in this class.
		while ( ! items.isEmpty() ){
			Item item = items.pop();
			item.beNot();
		}
		// Get super to do the rest.
		super.beNot();
	}

}
