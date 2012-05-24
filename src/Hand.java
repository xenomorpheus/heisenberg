import java.util.Vector;

// Hand
// Is like an item except.
// Is living  (todo)
// May wear at most one magical ring.
// May wear any number of non-magical rings.
// Rings may be removed in any order.

public class Hand extends Item implements RingWearer {

	Vector<Ring> nonMagicalRings = new Vector<Ring>();
	MagicRing magicRing = null;

	@Override
	public void ringWear(Ring ring) throws ExceptionCantWear {
		if (ring.implementsInterface(Magic.class)) {
			if (magicRing != null) {
				magicRing = (MagicRing) ring;
			} else {
				throw new ExceptionCantWear();
			}
		} else {
			ring.setLocation(this);
			this.nonMagicalRings.add(ring);
		}
	}

	@Override
	public void ringRemove(Ring ring, Location newLocation)
			throws ExceptionItemNotPresent {
		if (ring == magicRing) {
			magicRing = null;
			ring.setLocation(newLocation);
			return;
		}
		if (nonMagicalRings.removeElement(ring)) {
			ring.setLocation(newLocation);
			return;
		}
		throw new ExceptionItemNotPresent();
	}
}
