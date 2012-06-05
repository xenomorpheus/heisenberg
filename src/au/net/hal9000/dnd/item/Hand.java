package au.net.hal9000.dnd.item;

import java.util.Iterator;
import java.util.Vector;

import au.net.hal9000.dnd.item.exception.ExceptionCantRemove;
import au.net.hal9000.dnd.item.exception.ExceptionCantWear;
import au.net.hal9000.dnd.item.property.*;

// Hand
// Is like an item except:
// May wear at most one magical ring.
// May wear any number of non-magical rings.
// Rings may be removed in any order.
// Is living  (todo)

public class Hand extends Item {

	private Vector<Ring> rings = new Vector<Ring>();
	private int magicRingCount = 0;
	private int magicRingMax = 1;

	public Hand() {
		super("Hand");
	}

	public Hand(String string) {
		super(string);
	}

	// Features
	public static boolean isLiving() {
		return true;
	}

	public static boolean isRingWearer() {
		return true;
	}

	// Other

	public void ringWear(Ring ring) throws ExceptionCantWear {

		if (ring.isMagical()) {
			if (magicRingCount >= magicRingMax) {
				throw new ExceptionCantWear(
						"would be exceeded magic ring max of " + magicRingCount);
			}
			magicRingCount++;
		}
		if (!rings.add(ring)) {
			throw new ExceptionCantWear("add failed");
		}
		ring.setLocation(this);
	}

	public Vector<Ring> getRings() {
		return rings;
	}

	public int getMagicRingCount() {
		return magicRingCount;
	}

	public int getMagicRingMax() {
		return magicRingMax;
	}

	public void ringRemove(Ring ring, Item newLocation)
			throws ExceptionCantRemove {

		if (!this.rings.removeElement(ring)) {
			throw new ExceptionCantRemove("remove failed");
		}
		if (ring.isMagical()) {
			magicRingCount--;
			assert magicRingCount >= 0;
		}
		ring.setLocation(newLocation);
	}

	public float getWeight() {
		float total = this.getWeightBase();
		Iterator<Ring> itr = this.rings.iterator();
		while (itr.hasNext()) {
			total += itr.next().getWeight();
		}
		return total;
	}

	// Find items that match the criteria
	public void searchHelper(ItemSearch pSearch) {
		Iterator<Ring> itr = this.getRings().iterator();
		while (itr.hasNext()) {
			pSearch.searchItem(itr.next());
		}
		pSearch.searchItem(this);
	}
}
