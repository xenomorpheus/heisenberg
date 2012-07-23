package au.net.hal9000.player.item;

import java.util.Iterator;
import java.util.Vector;

import au.net.hal9000.player.item.exception.*;
import au.net.hal9000.player.units.*;

// Hand
// Is like an item except:
// May wear at most one magical ring.
// May wear any number of non-magical rings.
// Rings may be removed in any order.

public class Hand extends ItemImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	public boolean isLiving() {
		return true;
	}

	public boolean isRingWearer() {
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

	public void ringRemove(Ring ring, IItem newLocation)
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

	public Weight getWeight() {
		Weight total = this.getWeightBase();
		if (total == null){
			total = new Weight();
		}
		Iterator<Ring> itr = this.rings.iterator();
		while (itr.hasNext()) {
			total.add( itr.next().getWeight());
		}
		return total;
	}

	// Find items that match the criteria
	public void accept(ItemVisitor visitor) {
		Iterator<Ring> itr = this.rings.iterator();
		while (itr.hasNext()) {
			visitor.visit(itr.next());
		}
		// TODO why doesn't the following work?
		// visitor.visit(rings);
		super.accept(visitor);
	}
}
