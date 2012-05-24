import java.util.Iterator;
import java.util.Vector;

// Hand
// Is like an item except:
// May wear at most one magical ring.
// May wear any number of non-magical rings.
// Rings may be removed in any order.
// Is living  (todo)

public class Hand extends Item implements RingWearer {

	Vector<Ring> rings = new Vector<Ring>();
	int magicRingCount = 0;
	int magicRingMax = 1;


	public Hand() {
		super("Hand");
	}
	
	public Hand(String string) {
		super(string);
	}

	@Override
	public void ringWear(Ring ring) throws ExceptionCantWear {

		if (ring.implementsInterface(Magical.class)) {
			if (magicRingCount >= magicRingMax) {
				throw new ExceptionCantWear("would be exceeded magic ring max of "+ magicRingCount);
			}
			magicRingCount++;
		}
		if (!rings.add(ring)) {
			throw new ExceptionCantWear("add failed");
		}
		ring.setLocation(this);
	}

	@Override
	public void ringRemove(Ring ring, Location newLocation)
			throws ExceptionCantRemove {

		if (!this.rings.removeElement(ring)) {
			throw new ExceptionCantRemove("remove failed");
		}
		if (ring.implementsInterface(Magical.class)) {
			magicRingCount--;
			assert magicRingCount >= 0;
		}
		ring.setLocation(newLocation);
	}
	
	@Override
	public float getWeight() {
		float total = weightBase;
		Iterator<Ring> itr = this.rings.iterator();
		while( itr.hasNext()){
			total += itr.next().getWeight();
		}
		return total;
	}
}
