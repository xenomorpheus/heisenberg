package au.net.hal9000.heisenberg.item;

/**
 * The container has control of the movement of Item objects
 * within it.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//Import log4j classes.
import org.apache.log4j.Logger;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.api.ItemContainer;
import au.net.hal9000.heisenberg.item.api.ItemList;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;
import au.net.hal9000.heisenberg.item.property.ItemVisitor;
import au.net.hal9000.heisenberg.units.Currency;
import au.net.hal9000.heisenberg.units.Position;

// import org.apache.log4j.BasicConfigurator;
/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class ItemContainerImpl extends ItemImpl
        implements Serializable, ItemContainer, ItemList {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * Field LOGGER.
     */
    private static final Logger LOGGER = Logger
            .getLogger(ItemContainerImpl.class.getName());
    /**
     * Field weightMax.
     */
    private float weightMax = 0;
    /**
     * Field volumeMax.
     */
    private float volumeMax = 0;
    /**
     * Field contents.
     */
    private List<Item> contents = new ArrayList<Item>();

    // Constructor
    /**
     * Constructor for ItemContainer.
     * 
     * @param string
     *            String
     * @param description
     *            String
     */
    protected ItemContainerImpl(String string, String description) {
        super(string, description);
    }

    /**
     * Constructor for ItemContainer.
     * 
     * @param string
     *            String
     */
    public ItemContainerImpl(String string) {
        this(string, "");
    }

    // Getters and Setters
    /*
     * (non-Javadoc)
     * 
     * @see au.net.hal9000.heisenberg.item.ItemContainer#getContents()
     */
    @Override
    public List<Item> getContents() {
        return contents;
    }

    /*
     * (non-Javadoc)
     * 
     * @see au.net.hal9000.heisenberg.item.ItemContainer#getWeightMax()
     */
    @Override
    public float getWeightMax() {
        return weightMax;
    }

    /*
     * (non-Javadoc)
     * 
     * @see au.net.hal9000.heisenberg.item.ItemContainer#setWeightMax(float)
     */
    @Override
    public void setWeightMax(float weightMax) {
        this.weightMax = weightMax;
    }

    /*
     * (non-Javadoc)
     * 
     * @see au.net.hal9000.heisenberg.item.ItemContainer#getVolumeMax()
     */
    @Override
    public float getVolumeMax() {
        return volumeMax;
    }

    /*
     * (non-Javadoc)
     * 
     * @see au.net.hal9000.heisenberg.item.ItemContainer#setVolumeMax(float)
     */
    @Override
    public void setVolumeMax(float volumeMax) {
        this.volumeMax = volumeMax;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * au.net.hal9000.heisenberg.item.ItemContainer#contains(au.net.hal9000.
     * heisenberg.item.api.Item)
     */
    @Override
    public boolean contains(Item item) {
        return contents.contains(item);
    }

    // Misc

    /*
     * (non-Javadoc)
     * 
     * @see au.net.hal9000.heisenberg.item.ItemContainer#size()
     */

    @Override
    public int size() {
        int count = 0;
        if (null != contents) {
            count = contents.size();
        }
        return count;
    }

    /*
     * (non-Javadoc)
     * 
     * @see au.net.hal9000.heisenberg.item.ItemContainer#get(int)
     */
    @Override
    public Item get(final int index) {
        return contents.get(index);
    }

    /**
     * Used for tree display etc. <br>
     * 0-based index, -1 for missing.
     * 
     * @param child
     *            item to locate.
     * 
     * @return the index of the child item.
     */
    public int indexOf(final Item child) {
        return contents.indexOf(child);
    }

    /**
     * 
     * 
     * @return Total weight, including contents.
     */
    @Override
    public float getWeight() {
        float total = this.getWeightBase();
        total += this.getContentsWeight();
        return total;
    }

    /**
     * 
     * 
     * @return Total volume, including contents.
     */
    @Override
    public float getVolume() {
        float total = this.getVolumeBase();
        total += this.getContentsVolume();
        return total;
    }

    /**
     * 
     * 
     * @return Total value, including contents.
     */
    @Override
    public Currency getValue() {
        Currency total = new Currency(this.getValueBase());
        total.add(this.getContentsValue());
        return total;
    }

    // end of setters and getters

    // misc methods

    /**
     * Method hashCode.
     * 
     * @return int
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((null == contents) ? 0 : contents.hashCode());
        result = prime * result + Float.floatToIntBits(volumeMax);
        result = prime * result + Float.floatToIntBits(weightMax);
        return result;
    }

    /**
     * Method equals.
     * 
     * @param obj
     *            Object
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        // Code almost certainly won't reach here as Item id values are random
        // and very likely to be different.
        if (!(obj instanceof ItemContainerImpl)) {
            return false;
        }
        ItemContainerImpl other = (ItemContainerImpl) obj;
        if (null == contents) {
            if (null != other.contents) {
                return false;
            }
        } else if (!contents.equals(other.contents)) {
            return false;
        }
        if (Float.floatToIntBits(volumeMax) != Float
                .floatToIntBits(other.volumeMax)) {
            return false;
        }
        if (Float.floatToIntBits(weightMax) != Float
                .floatToIntBits(other.weightMax)) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see au.net.hal9000.heisenberg.item.ItemContainer#add(au.net.hal9000.
     * heisenberg.item.api.Item)
     */
    @Override
    public void add(Item item) {
        add(contents.size(), item);
    }

    /*
     * (non-Javadoc)
     * 
     * @see au.net.hal9000.heisenberg.item.ItemContainer#add(int,
     * au.net.hal9000.heisenberg.item.api.Item)
     */
    @Override
    public void add(int index, Item item) {
        ItemContainer itemCurrentContainer = item.getContainer();
        if (null != itemCurrentContainer) {
            if (this.equals(itemCurrentContainer)) {
                LOGGER.error("Item " + this + " was already in container "
                        + itemCurrentContainer);
                // Nothing to do.
                return;
            }
        }
        // TODO consider re-factor into a visitor pattern that totals
        // up the weight and volume in one pass.

        // check Weight
        final float weightMax = this.getWeightMax();
        if (weightMax > 0) {
            float total = this.getContentsWeight();
            total += item.getWeight();
            if (total > weightMax) {
                StringBuilder text = new StringBuilder(11)
                        .append("TooHeavy - Adding ").append(item.getName())
                        .append(" weighing ").append(item.getWeight())
                        .append(" will total ").append(total)
                        .append(", which is too heavy for ").append(getName())
                        .append(", weightMax=").append(weightMax);
                throw new TooHeavyException(text.toString());
            }
        }

        // check Volume
        final float volumeMax = this.getVolumeMax();
        if (volumeMax > 0) {
            float total = this.getContentsVolume();
            total += item.getVolume();
            if (total > volumeMax) {
                StringBuilder text = new StringBuilder(11)
                        .append("TooLarge - Adding ").append(item.getName())
                        .append(" weighing ").append(item.getWeight())
                        .append(" will total ").append(total)
                        .append(", which is too heavy for ").append(getName())
                        .append(", weightMax=").append(weightMax);
                throw new TooLargeException(text.toString());
            }
        }

        // remove item from existing location
        if (null != itemCurrentContainer) {
            itemCurrentContainer.remove(item);
        }

        // Add the item and update the item's location.
        int currentSize = contents.size();
        if (index > (currentSize + 1)) {
            throw new RuntimeException("Requested position " + index
                    + " much greater " + currentSize + ". container is a "
                    + this.getName() + ", added item is " + item.getName());
        }
        contents.add(index, item);
        // check
        int pos = contents.indexOf(item);
        if (pos != index) {
            throw new RuntimeException("Requested position " + index
                    + " != reported position " + pos);
        }
        item.setContainer(this);
    }

    /**
     * Add multiple items to the contents.
     * 
     * @param items
     *            the items to add.
     */
    @Override
    public void addAll(List<Item> items) {
        for (Item item : items) {
            this.add(item);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see au.net.hal9000.heisenberg.item.ItemContainer#empty(au.net.hal9000.
     * heisenberg.item.ItemContainer)
     */
    @Override
    public void empty(ItemContainer newLocation) {
        while (!contents.isEmpty()) {
            Item item = contents.remove(0);
            newLocation.add(item);
        }
    }

    /**
     * Use getWeight() to get total including contents. Magic containers will
     * override getWeight().
     * 
     * 
     * @return the weight of just the contents.
     */
    private float getContentsWeight() {
        float total = 0;
        for (Item item : getContents()) {
            total += item.getWeight();
        }
        return total;
    }

    /**
     * Use getVolume() to get total including contents. Magic containers will
     * override getVolume().
     * 
     * 
     * @return the volume of just the contents.
     */
    private float getContentsVolume() {
        float total = 0;
        for (Item item : getContents()) {
            total += item.getVolume();
        }
        return total;
    }

    /**
     * Use getValue() to get total including contents. Magic containers will
     * override getValue().
     * 
     * 
     * @return the value of just the contents.
     */
    private Currency getContentsValue() {
        Currency total = new Currency();
        for (Item item : getContents()) {
            total.add(item.getValue());
        }
        return total;
    }

    @Override
    public void accept(ItemVisitor visitor) {
        // Search the Items directly declared in this class.
        for (Item item : getContents()) {
            item.accept(visitor);
        }
        // Get super to do the rest.
        visitor.visit(this);
    }

    /**
     * Destroy this object.
     */
    public void beNot() {
        // Call beNot on the Items directly declared in this class.
        while (!contents.isEmpty()) {
            Item item = contents.remove(0);
            item.beNot();
        }
        // Get super to do the rest.
        super.beNot();
    }

    /*
     * (non-Javadoc)
     * 
     * @see au.net.hal9000.heisenberg.item.ItemContainer#remove(au.net.hal9000.
     * heisenberg.item.api.Item)
     */
    @Override
    public void remove(Item item) {
        contents.remove(item);
        item.setContainer(null);
    }

	@Override
	public void remove(int index) {
		remove(contents.get(index));
		
	}
    
    /**
     * Attempt to move Item's position within the ItemContainer to this new
     * position.
     * 
     * The container has control of the movement of Item objects within it.
     * Partial or no movement may occur.
     * 
     * @param item
     *            the item to be moved.
     * @param requestedPosition
     *            the new position.
     * 
     */
    @Override
    public void moveItemAbsolute(Item item, Position requestedPosition) {
        ItemContainer container = item.getContainer();
        if (container != this) {
            throw new RuntimeException(
                    "attempting to move item not in container. item=" + item
                            + ", container=" + container);
        }
        item.setPosition(requestedPosition);
    }

    /**
     * Attempt to move Item's position within the ItemContainer by this amount.
     * 
     * The container has control of the movement of Item objects within it.
     * Partial or no movement may occur.
     * 
     * @param item
     *            the item to be moved.
     * @param delta
     *            the amount to move within the container.
     * 
     */
    @Override
    public void moveItemDelta(Item item, Position delta) {
        ItemContainer container = item.getContainer();
        if (container != this) {
            throw new RuntimeException(
                    "attempting to move item not in container. item=" + item
                            + ", container=" + container);
        }
        item.applyDelta(delta);
    }

    /**
     * {@inheritDoc} * @param item Item
     */
    @Override
    public void setAllFrom(Item item) {
        super.setAllFrom(item);
        // TODO set local properties
        // TODO Add unit tests
        // TODO Do we copy contents? Thinking no.
    }

}
