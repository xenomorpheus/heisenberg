package au.net.hal9000.heisenberg.item;

import java.util.EmptyStackException;
import java.util.Vector;

import au.net.hal9000.heisenberg.units.Currency;

public interface IItemContainer extends IItem {

    
    /**
     * Get the contents
     * @return the contents
     */
    Vector<IItem> getContents();
    /**
     * Set the contents
     * @param contents
     */
    public void setContents(Vector<IItem> contents);

    
	/**
	 * @return The max weight that can be carried.
	 */
	float getWeightMax();

	/**
	 * Set the max weight that may be carried.
	 * 
	 * @param loadMax
	 *            The max weight that can be carried.
	 */
	void setWeightMax(float loadMax);

	/**
	 * get the maximum volume that this item can hold.
	 * 
	 * @return the maximum volume that this item can hold.
	 */
	float getVolumeMax();

	/**
	 * Set the maximum volume that this item can hold.
	 * 
	 * @param volumeMax
	 *            the maximum volume that this item can hold.
	 */
	void setVolumeMax(float volumeMax);

	// Misc
	/**
	 * 
	 * @return get all the first level contents
	 */
	public Vector<IItem> getChildren();

	/**
	 * 
	 * @return Total weight, including contents.
	 */
	public float getWeight();

	/**
	 * 
	 * @return Total volume, including contents.
	 */
	public float getVolume();

	/**
	 * 
	 * @return Total value, including contents.
	 */
	public Currency getValue();

	/**
	 * Add the IItem to the contents.
	 * @deprecated use transfer. add() is only for testing.
	 * @param index
	 * @param item
	 */
	public void add(int index, IItem item);

	/**
	 * Add the IItem to the contents.
	 * @param item
	 */
	public void add(IItem item);

	/**
	 * Add multiple items to the contents.
	 * @param items
	 */
	public void add(Vector<IItem> items);

	/**
	 * Take the top item out of the contents.
	 * @return the top item in the contents
	 * @throws EmptyStackException
	 */
	public IItem pop() throws EmptyStackException;

	/**
	 * Peek at the top item of contents without removing it.
	 * 
	 * @return The top item.
	 * @throws EmptyStackException
	 */
	public IItem peek() throws EmptyStackException;

	/**
	 * Empty the bag into this location
	 * 
	 * @param newLocation
	 */
	public void empty(ItemContainer newLocation);

	/**
	 * Get the number of items in the container.
	 * 
	 * @return the number of items directly inside the container. Items with
	 *         other items don't add to the count as they are *NOT* directly
	 *         contained.
	 */
	public int getContentsCount();

	// TODO rename Visitor Pattern style
	// Find contents that match the criteria
	public void accept(ItemVisitor visitor);

	/**
	 * Destroy this object.
	 */
	public void beNot();

	/**
	 * Used for tree display.
	 * 
	 * We aren't a leaf as we can hold items.
	 * 
	 * @return false
	 */
	public boolean isLeaf();

	/**
	 * Used for tree display.
	 * 
	 * Return the number of first-level items this in this container.
	 * 
	 * @return the number of children.
	 */
	public int getChildCount();

	/**
	 * Used for tree display.
	 * 
	 * @param index get child with with this index in list of items.
	 * @return the item requested.
	 */
	public IItem getChild(final int index);

	/**
	 * Used for tree display etc. <br>
	 * 0-based index, -1 for missing.
	 * 
	 * @param child
	 * @return the index of the child item.
	 */
	public int getIndexOfChild(final IItem child);

	/**
	 * Does the IItem exist in the ItemContainer ?
	 * @param item IItem looking for
	 * @return true iff found
	 */
	boolean contains(IItem item);
	
	/**
	 * Remove the IItem from the container.
	 * @param item the time to remove.
	 */
    void remove(IItem item);
    
    /**
     * Relocate the IItem to the new 
     * @param item item to relocate.
     * @param container new container.
     */
    void relocateItem(IItem item, IItemContainer container);

}
