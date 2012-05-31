package au.net.hal9000.dnd.item;

import java.util.EmptyStackException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Stack;

import au.net.hal9000.dnd.item.exception.*;

// TODO make abstract
public class ItemContainer extends Item {
	float weightMax = -1F; // -1 unlimited
	float volumeMax = -1F; // -1 unlimited
	Stack<Item> items = new Stack<Item>();

	ItemContainer(String string) {
		super(string);
	}

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
		Iterator<Item> itr = this.items.iterator();
		while (itr.hasNext()) {
			itr.next().setLocation(newLocation);
		}
	}

	// Warning
	// Use getWeight() to get total including contents.
	// Magic containers will override getWeight().
	public float getContentsWeight() {
		float total = 0F;
		Iterator<Item> itr = this.items.iterator();
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
		Iterator<Item> itr = this.items.iterator();
		while (itr.hasNext()) {
			total += itr.next().getVolume();
		}
		return total;
	}

	public Currency getContentsValue() {
		Currency total = new Currency();
		Iterator<Item> itr = this.items.iterator();
		while (itr.hasNext()) {
			total.add(itr.next().getValue());
		}
		return total;
	}

	// Recursively search the container looking for
	// an item that implements a class.
	public Item findFirstImplementsDeep(Class pClass, Stack<Item> seen) {
		Enumeration<Item> e = items.elements();
		while (e.hasMoreElements()) {
			Item item =  e.nextElement();
			System.out.println("Looking at item named "+item.getName());
            if (! seen.contains(item)){
            	if (item.implementsInterface(pClass)){
            		return item;
            	}
            	seen.push(item);
            	// look inside containers
                if (item instanceof ItemContainer){
                	Item inside = ((ItemContainer) item).findFirstImplementsDeep(pClass, seen);
                	if (inside != null){
                		return inside;
                	}
                }
            }
		}
		return null;
	}

}
