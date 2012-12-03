package au.net.hal9000.heisenberg.item;

import java.io.Serializable;
import java.util.EmptyStackException;
import java.util.Vector;

import au.net.hal9000.heisenberg.item.exception.*;
import au.net.hal9000.heisenberg.units.*;

public abstract class ItemContainer extends Item implements IItemContainer,
        Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private float weightMax = 0;
    private float volumeMax = 0;
    private Vector<IItem> contents = new Vector<IItem>();

    public ItemContainer(String string) {
        super(string);
    }

    // Getters and Setters
    /** {@inheritDoc} */
    @Override
    public Vector<IItem> getContents() {
        return contents;
    }

    /** {@inheritDoc} */
    @Override
    public void setContents(Vector<IItem> contents) {
        this.contents = contents;
    }

    /** {@inheritDoc} */
    @Override
    public float getWeightMax() {
        return weightMax;
    }

    /** {@inheritDoc} */
    @Override
    public void setWeightMax(float weightMax) {
        this.weightMax = weightMax;
    }

    /** {@inheritDoc} */
    @Override
    public float getVolumeMax() {
        return volumeMax;
    }

    /** {@inheritDoc} */
    @Override
    public void setVolumeMax(float volumeMax) {
        this.volumeMax = volumeMax;
    }

    /** {@inheritDoc} */
    @Override
    public boolean contains(IItem item) {
        return contents.contains(item);
    }

    // Misc
    /** {@inheritDoc} */
    @Override
    public boolean isLeaf() {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public int getChildCount() {
        int count = 0;
        if (contents != null) {
            count = contents.size();
        }
        return count;
    }

    /** {@inheritDoc} */
    @Override
    public IItem getChild(final int index) {
        return contents.get(index);
    }

    /** {@inheritDoc} */
    @Override
    public int getIndexOfChild(final IItem child) {
        return contents.indexOf(child);
    }

    /** {@inheritDoc} */
    @Override
    public Vector<IItem> getChildren() {
        return contents;
    }

    /** Total weight, including contents. */
    @Override
    public float getWeight() {
        float total = this.getWeightBase();
        total += this.getContentsWeight();
        return total;
    }

    /** Total volume, including contents. */
    @Override
    public float getVolume() {
        float total = this.getVolumeBase();
        total += this.getContentsVolume();
        return total;
    }

    /** Total value, including contents. */
    @Override
    public Currency getValue() {
        Currency total = new Currency(this.getValueBase());
        total.add(this.getContentsValue());
        return total;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(IItem item) {
        add(contents.size(), item);
    }

    /**
     * {@inheritDoc}
     * 
     * @deprecated use transfer instead
     */
    @Override
    public void add(int index, IItem item) {
        IItemContainer itemCurrentContainer = item.getLocation();
        if (itemCurrentContainer != null) {
            if (this.equals(itemCurrentContainer)) {
                // TODO Move to assert or log
                System.out.println("Moving to existing location");
                // Nothing to do.
                return;
            }
        }

        // check Weight
        final float weightMax = this.getWeightMax();
        if (weightMax >= 0) {
            float total = this.getContentsWeight();
            total += item.getWeight();
            if (total > weightMax) {
                throw new ExceptionTooHeavy("Adding " + item.getName()
                        + " weighing " + item.getWeight() + " will total "
                        + total + ", which is too heavy for " + this.getName()
                        + ", weightMax=" + weightMax);
            }
        }

        // check Volume
        final float volumeMax = this.getVolumeMax();
        if (volumeMax >= 0) {
            float total = this.getContentsVolume();
            total += item.getVolume();
            if (total > volumeMax) {
                throw new ExceptionTooBig("Adding " + item.getName()
                        + " of volume " + item.getVolume() + " will total "
                        + total + ", which is too big for " + this.getName()
                        + ", volumeMax=" + volumeMax);
            }
        }

        // Add the item and update the item's location.
        contents.add(index, item);
        item.setLocation(this);
    }

    /** {@inheritDoc} */
    @Override
    public void add(Vector<IItem> items) {
        for (IItem item : items) {
            this.add(item);
        }
    }

    /**
     * Take the top item out of the contents.
     * 
     * @return the top item in the contents
     * @throws EmptyStackException
     */
    public IItem pop() throws EmptyStackException {
        return contents.remove(contents.size() - 1);
    }

    /**
     * Peek at the top item of contents without removing it.
     * 
     * @return The top item.
     * @throws EmptyStackException
     */
    public IItem peek() throws EmptyStackException {
        return contents.lastElement();
    }

    /**
     * Empty the bag into this location
     * 
     * @param newLocation
     */
    public void empty(ItemContainer newLocation) {
        while (!contents.isEmpty()) {
            IItem item = contents.remove(0);
            newLocation.add(item);
        }
    }

    /**
     * Get the number of items in the container.
     * 
     * @return the number of items directly inside the container. Items with
     *         other items don't add to the count as they are *NOT* directly
     *         contained.
     */
    public int getContentsCount() {
        return contents.size();
    }

    /**
     * Use getWeight() to get total including contents. Magic containers will
     * override getWeight().
     * 
     * @return the weight of just the contents.
     */
    private float getContentsWeight() {
        float total = 0;
        for (IItem iItem : getContents()) {
            total += iItem.getWeight();
        }
        return total;
    }

    /**
     * Use getVolume() to get total including contents. Magic containers will
     * override getVolume().
     * 
     * @return the volume of just the contents.
     */
    private float getContentsVolume() {
        float total = 0;
        for (IItem iItem : getContents()) {
            total += iItem.getVolume();
        }
        return total;
    }

    /**
     * Use getValue() to get total including contents. Magic containers will
     * override getValue().
     * 
     * @return the value of just the contents.
     */
    private Currency getContentsValue() {
        Currency total = new Currency();
        for (IItem iItem : getContents()) {
            total.add(iItem.getValue());
        }
        return total;
    }

    // TODO rename Visitor Pattern style
    // Find contents that match the criteria
    public void accept(ItemVisitor visitor) {
        // Search the Items directly declared in this class.
        for (IItem iItem : getContents()) {
            visitor.visit(iItem);
        }
        // Get super to do the rest.
        super.accept(visitor);
    }

    /** {@inheritDoc} */
    @Override
    public void beNot() {
        // Call beNot on the Items directly declared in this class.
        while (!contents.isEmpty()) {
            IItem iItem = contents.remove(0);
            iItem.beNot();
        }
        // Get super to do the rest.
        super.beNot();
    }

    /** {@inheritDoc} */
    @Override
    public ItemContainer clone(IItem toClone) throws CloneNotSupportedException {
        ItemContainer clone = (ItemContainer) super.clone(toClone);

        // Make sure the cloning is deep, not shallow.
        // e.g. set the non-mutable, non-primitives

        // contents
        Vector<IItem> contents = this.getContents();
        if (contents == null) {
            clone.setContents(null);
        } else {
            Vector<IItem> cloneContents = new Vector<IItem>();
            for (IItem item : contents) {

                try {
                    cloneContents.add(item.clone());
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException("clone() failure");
                }
            }
            clone.setContents(cloneContents);
        }

        // location is *NOT* cloned.
        return clone;
    }

    /**
     * Shallow copy properties from one object to another.
     * 
     * @param container
     *            source of the properties we copy.
     */
    public void setAllFrom(ItemContainer container) {
        setAllFrom((Item) container);
        setWeightMax(container.getWeightMax());
        setVolumeMax(container.getVolumeMax());
        setContents(container.getContents());
    }

    /** {@inheritDoc} */
    @Override
    public void remove(IItem item) {
        contents.remove(item);
        item.setLocation(null);
    }

    /** {@inheritDoc} */
    @Override
    public void relocateItem(IItem item, IItemContainer location) {
        IItemContainer losingContainer = item.getLocation();
        if (losingContainer != null) {
            losingContainer.remove(item);
        }
        location.add(item);
    }

}
