package au.net.hal9000.heisenberg.item;

/**
* The container has control of the movement of Item objects
* within it.
*/

import java.io.Serializable;
import java.util.EmptyStackException;
import java.util.Vector;

//Import log4j classes.
import org.apache.log4j.Logger;
// import org.apache.log4j.BasicConfigurator;

import au.net.hal9000.heisenberg.units.*;

public abstract class ItemContainer extends Item implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final Logger  logger = Logger.getLogger(ItemContainer.class.getName());
    private float weightMax = 0;
    private float volumeMax = 0;
    private Vector<Item> contents = new Vector<Item>();

    // Constructor
    public ItemContainer(String string, String description) {
        super(string, description);
    }

    public ItemContainer(String string) {
        this(string,"");
    }
    // Getters and Setters
    /**
     * Get the contents
     * 
     * @return the contents
     */
    public Vector<Item> getContents() {
        return contents;
    }

    /**
     * Set the contents
     * 
     * @param contents
     */
    public void setContents(Vector<Item> contents) {
        this.contents = contents;
    }

    /**
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
     * @return true iff found
     */
    public boolean contains(Item item) {
        return contents.contains(item);
    }

    // Misc
    /**
     * Used for tree display.
     * 
     * We aren't a leaf as we can hold items.
     * 
     * @return false
     */
    @Override
    public boolean isLeaf() {
        return false;
    }

    /**
     * Used for tree display.
     * 
     * Return the number of first-level items this in this container.
     * 
     * @return the number of children.
     */
    public int getChildCount() {
        int count = 0;
        if (contents != null) {
            count = contents.size();
        }
        return count;
    }

    /**
     * Used for tree display.
     * 
     * @param index
     *            get child with with this index in list of items.
     * @return the item requested.
     */
    public Item getChild(final int index) {
        return contents.get(index);
    }

    /**
     * Used for tree display etc. <br>
     * 0-based index, -1 for missing.
     * 
     * @param child
     * @return the index of the child item.
     */
    public int getIndexOfChild(final Item child) {
        return contents.indexOf(child);
    }

    /**
     * 
     * @return get all the first level contents
     */
    public Vector<Item> getChildren() {
        return contents;
    }

    /**
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
     * @return Total value, including contents.
     */
    @Override
    public Currency getValue() {
        Currency total = new Currency(this.getValueBase());
        total.add(this.getContentsValue());
        return total;
    }

    /**
     * Add the Item to the contents.
     * 
     * @param item
     */
    public void add(Item item) {
        add(contents.size(), item);
    }

    /**
     * Add the Item to the contents.
     * 
     * @deprecated use transfer. add() is only for testing.
     * @param index
     * @param item
     * @deprecated use transfer instead
     */
    public void add(int index, Item item) {
        ItemContainer itemCurrentContainer = item.getContainer();
        if (itemCurrentContainer != null) {
            if (this.equals(itemCurrentContainer)) {
                logger.error("Already in container");
                // Nothing to do.
                return;
            }
        }
        // TODO consider refactor into a visitor pattern that tallies 
        // up the weight and volume in one pass.

        // check Weight
        final float weightMax = this.getWeightMax();
        if (weightMax >= 0) {
            float total = this.getContentsWeight();
            total += item.getWeight();
            if (total > weightMax) {
                logger.error("TooHeavy - Adding " + item.getName()
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
                // ExceptionTooBig
                logger.error("TooBig - Adding " + item.getName()
                        + " of volume " + item.getVolume() + " will total "
                        + total + ", which is too big for " + this.getName()
                        + ", volumeMax=" + volumeMax);
            }
        }
        // remove item from existing location
        ItemContainer currentLocation = item.getContainer();
        if (currentLocation != null) {
            currentLocation.remove(item);
        }

        // Add the item and update the item's location.
        int currentSize = contents.size();
        if (index > (currentSize+1) ){
            throw new RuntimeException("Requested possition "+index+" much greater "+currentSize+". container is a "+this.getName()+", added item is "+item.getName());
        }
        contents.add(index, item);
        // check
        int pos = contents.indexOf(item);
        if (pos != index){
            throw new RuntimeException("Requested possition "+index+" != reported possition "+pos);
        }
        item.setContainer(this);
    }

    /**
     * Add multiple items to the contents.
     * 
     * @param items
     */
    public void add(Vector<Item> items) {
        for (Item item : items) {
            this.add(item);
        }
    }

    /**
     * Take the top item out of the contents.
     * 
     * @return the top item in the contents
     * @throws EmptyStackException
     */
    public Item pop() throws EmptyStackException {
        return contents.remove(contents.size() - 1);
    }

    /**
     * Peek at the top item of contents without removing it.
     * 
     * @return The top item.
     * @throws EmptyStackException
     */
    public Item peek() throws EmptyStackException {
        return contents.lastElement();
    }

    /**
     * Empty the bag into this location
     * 
     * @param newLocation
     */
    public void empty(ItemContainer newLocation) {
        while (!contents.isEmpty()) {
            Item item = contents.remove(0);
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
        for (Item Item : getContents()) {
            total += Item.getWeight();
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
        for (Item Item : getContents()) {
            total += Item.getVolume();
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
        for (Item Item : getContents()) {
            total.add(Item.getValue());
        }
        return total;
    }

    // TODO rename Visitor Pattern style
    // Find contents that match the criteria
    public void accept(ItemVisitor visitor) {
        // Search the Items directly declared in this class.
        for (Item Item : getContents()) {
            visitor.visit(Item);
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
            Item Item = contents.remove(0);
            Item.beNot();
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
     * Move the Item object's position within the ItemContainer.
     * 
     * @param item the item to be moved.
     * @param expectedPosition the new position.
     * 
     * The container has control of the movement of Item objects
     * within it.
     */
    public void moveItem(Item item, Point3d expectedPosition) {
        item.setPosition(expectedPosition);        
    }

    /**
     * Relocate the Item to a new ItemContainer.
     * 
     * @param item
     *            item to relocate.
     * @param container
     *            new container.
     */
    public void relocateItem(Item item, ItemContainer container) {
        ItemContainer losingContainer = item.getContainer();
        if (losingContainer != null) {
            losingContainer.remove(item);
        }
        container.add(item);
    }    
    
    /** {@inheritDoc} */
    @Override
    public void setAllFrom(Item item) {
        super.setAllFrom(item);
        // TODO set local properties
        // TODO Add unit tests
        // TODO Do we copy contents?  Thinking no.
    }
}
