package au.net.hal9000.heisenberg.item;

import java.util.Iterator;
import java.util.Vector;

import au.net.hal9000.heisenberg.item.exception.*;
import au.net.hal9000.heisenberg.item.property.RingWearer;
import au.net.hal9000.heisenberg.item.property.ItemProperty;

/**
 * Hand Is like an item except: May wear at most one magical ring. May wear any
 * number of non-magical rings. Rings may be removed in any order.
 */
public class Hand extends ItemContainer implements RingWearer {

    private static final long serialVersionUID = 1L;
    private Vector<Ring> rings = new Vector<Ring>();
    private int magicRingCount = 0;
    private int magicRingMax = 1;

    public Hand() {
        this("Hand");
    }

    public Hand(String string) {
        super(string);
        ItemProperty.setLiving(this, true);
        this.setWeightMax(2); // TODO config
        this.setVolumeMax(2); // TODO config
    }

    // Other

    public void ringWear(Ring ring) throws ExceptionCantWear {

        if (ItemProperty.isMagical(ring)) {
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

    public void ringRemove(Ring ring, IItemContainer newLocation)
            throws ExceptionCantRemove {

        if (!this.rings.removeElement(ring)) {
            throw new ExceptionCantRemove("remove failed");
        }
        if (ItemProperty.isMagical(ring)) {
            magicRingCount--;
            assert magicRingCount >= 0;
        }
        ring.setLocation(newLocation);
    }

    /** Total weight, including contents. */
    @Override
    public float getWeight() {
        float total = this.getWeightBase();
        Iterator<Ring> itr = this.rings.iterator();
        while (itr.hasNext()) {
            total += itr.next().getWeight();
        }
        return total;
    }

    /** Total volume, including contents. */
    @Override
    public float getVolume() {
        float total = this.getVolumeBase();
        Iterator<Ring> itr = this.rings.iterator();
        while (itr.hasNext()) {
            total += itr.next().getWeight();
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
