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

import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;
import au.net.hal9000.heisenberg.item.property.ItemVisitor;
import au.net.hal9000.heisenberg.units.Currency;
import au.net.hal9000.heisenberg.units.Point3d;

// import org.apache.log4j.BasicConfigurator;
/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class ItemContainer extends Item implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * Field LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ItemContainer.class
            .getName());
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
    protected ItemContainer(String string, String description) {
        super(string, description);
    }

    /**
     * Constructor for ItemContainer.
     * 
     * @param string
     *            String
     */
    protected ItemContainer(String string) {
        this(string, "");
    }

    // Getters and Setters
    /**
     * Get the contents.
     * 
     * 
     * @return the contents
     */
    public List<Item> getContents() {
        return contents;
    }

    /**
     * Set the contents.
     * 
     * @param contents
     *            the new contents of this container.
     */
    public void setContents(List<Item> contents) {
        this.contents = contents;
    }

    /**
     * 
     * @return The max weight that can be carried.
     */
    public float getWeightMax() {
        return weightMax;
    }

    /**
     * Set the max weight that may be carried.
     * 
     * @param weightMax
     *            The max weight that can be carried.
     */
    public void setWeightMax(float weightMax) {
        this.weightMax = weightMax;
    }

    /**
     * get the maximum volume that this item can hold.
     * 
     * 
     * @return the maximum volume that this item can hold.
     */
    public float getVolumeMax() {
        return volumeMax;
    }

    /**
     * Set the maximum volume that this item can hold.
     * 
     * @param volumeMax
     *            the maximum volume that this item can hold.
     */
    public void setVolumeMax(float volumeMax) {
        this.volumeMax = volumeMax;
    }

    /**
     * Does the Item exist in the ItemContainer ?
     * 
     * @param item
     *            Item looking for
     * 
     * @return true iff found
     */
    public boolean contains(Item item) {
        return contents.contains(item);
    }

    // Misc

    /**
     * Used for tree display.
     * 
     * Return the number of first-level items this in this container.
     * 
     * 
     * @return the number of children.
     */
    public int getChildCount() {
        int count = 0;
        if (null != contents) {
            count = contents.size();
        }
        return count;
    }

    /**
     * Used for tree display.
     * 
     * @param index
     *            get child with with this index in list of items.
     * 
     * @return the item requested.
     */
    public Item getChildAt(final int index) {
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
    public int getIndexOfChild(final Item child) {
        return contents.indexOf(child);
    }

    /**
     * 
     * 
     * @return get all the first level contents
     */
    public List<Item> getChildren() {
        return contents;
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
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
     * Add the Item to the contents.
     * 
     * @param item
     * @throws TooLargeException
     * @throws TooHeavyException
     * @throws InvalidTypeException 
     * 
     */
    public void add(Item item) throws TooHeavyException, TooLargeException, InvalidTypeException {
        add(contents.size(), item);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
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
        if (!(obj instanceof ItemContainer)) {
            return false;
        }
        ItemContainer other = (ItemContainer) obj;
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

    /**
     * Add the Item to the contents.
     * 
     * @param index
     *            index position to add at.
     * @param item
     *            item to add.
     * @throws TooHeavyException
     * @throws TooLargeException
     */
    public void add(int index, Item item) throws TooHeavyException, TooLargeException {
        ItemContainer itemCurrentContainer = item.getContainer();
        if (null != itemCurrentContainer) {
            if (this.equals(itemCurrentContainer)) {
                LOGGER.error("Already in container");
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
                StringBuilder text = new StringBuilder();
                text.append("TooHeavy - Adding ");
                text.append(item.getName());
                text.append(" weighing ");
                text.append(item.getWeight());
                text.append(" will total ");
                text.append(total);
                text.append(", which is too heavy for ");
                text.append(this.getName());
                text.append(", weightMax=");
                text.append(weightMax);
                throw new TooHeavyException(text.toString());
            }
        }

        // check Volume
        final float volumeMax = this.getVolumeMax();
        if (volumeMax > 0) {
            float total = this.getContentsVolume();
            total += item.getVolume();
            if (total > volumeMax) {
                StringBuilder text = new StringBuilder();
                text.append("TooLarge - Adding ");
                text.append(item.getName());
                text.append(" weighing ");
                text.append(item.getWeight());
                text.append(" will total ");
                text.append(total);
                text.append(", which is too heavy for ");
                text.append(this.getName());
                text.append(", weightMax=");
                text.append(weightMax);
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
     * 
     * @throws InvalidTypeException
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    public void addItems(List<Item> items) throws InvalidTypeException, TooHeavyException, TooLargeException {
        for (Item item : items) {
            this.add(item);
        }
    }

    /**
     * Take the top item out of the contents.
     * 
     * 
     * 
     * @return the top item in the contents * @throws EmptyStackException
     */
    public Item pop() {
        return contents.remove(contents.size() - 1);
    }

    /**
     * Empty the bag into this location.
     * 
     * @param newLocation
     * 
     * @throws InvalidTypeException
     * @throws TooLargeException
     * @throws TooHeavyException
     */
    public void empty(ItemContainer newLocation) throws InvalidTypeException,
             TooHeavyException, TooLargeException {
        while (!contents.isEmpty()) {
            Item item = contents.remove(0);
            newLocation.add(item);
        }
    }

    /**
     * Get the number of items in the container.
     * 
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

    // TODO rename Visitor Pattern style
    // Find contents that match the criteria
    /**
     * Method accept.
     * 
     * @param visitor
     *            ItemVisitor
     */
    public void accept(ItemVisitor visitor) {
        // Search the Items directly declared in this class.
        for (Item item : getContents()) {
            visitor.visit(item);
        }
        // Get super to do the rest.
        super.accept(visitor);
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

    /**
     * Remove the Item from the container.
     * 
     * @param item
     *            the time to remove.
     */
    public void remove(Item item) {
        contents.remove(item);
        item.setContainer(null);
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
     * @param expectedPosition
     *            the new position.
     * 
     */
    public void moveItemAbsolute(Item item, Point3d expectedPosition) {
        ItemContainer container = item.getContainer();
        if (container != this) {
            throw new RuntimeException(
                    "attempting to move item not in container. item=" + item
                            + ", container=" + container);
        }
        item.setPosition(expectedPosition);
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
     *            the amount to move.
     * 
     */
    public void moveItemDelta(Item item, Point3d delta) {
        ItemContainer container = item.getContainer();
        if (container != this) {
            throw new RuntimeException(
                    "attempting to move item not in container. item=" + item
                            + ", container=" + container);
        }
        item.getPosition().applyDelta(delta);
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
