package au.net.hal9000.dnd;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Stack;

public class ItemContainer extends Item {
	float weightMax = -1F; // -1 unlimited
	float volumeMax = -1F; // -1 unlimited
	Stack<Item> items = new Stack<Item>();

	ItemContainer(String string) {
		super(string);

	}

	// Includes contents
	public float getWeight() {
		float total = this.weightBase;
		total += this.getContentsWeight();
		return total;
	}

	// Includes contents
	public float getVolume() {
		float total = this.volumeBase;
		total += this.getContentsVolume();
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

	public void add(Item item) throws ExceptionTooHeavy, ExceptionTooBig {
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

	public void empty(Location newLocation) {
		Iterator<Item> itr = this.items.iterator();
		while (itr.hasNext()) {
			itr.next().setLocation(newLocation);
		}
	}

	// Warning
	// Use getWeight() to get total of including contents.
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
	// Use getVolume() to get total of including contents.
	// Magic containers will override getVolume().
	public float getContentsVolume() {
		float total = 0F;
		Iterator<Item> itr = this.items.iterator();
		while (itr.hasNext()) {
			total += itr.next().getVolume();
		}
		return total;
	}
}