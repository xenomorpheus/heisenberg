package au.net.hal9000.heisenberg.item;

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.Vector;

import au.net.hal9000.heisenberg.units.Currency;


/**
 *
 * TODO consider replave Item with IItem.
 */

public interface IItemContainer {

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
	public Stack<IItem> getChildren();

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
	 * Add the Item to the contents.
	 * @deprecated use transfer. add() is only for testing.
	 * @param item
	 */
	public void add(Item item);

	/**
	 * Add multiple items to the contents.
	 * @deprecated use transfer. add() is only for testing.
	 * @param items
	 */
	public void add(Vector<Item> items);

	/**
	 * Take the top item out of the contents.
	 * @deprecated use transfer. add() is only for testing.
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

	public void beNot();

	// TODO should other be Item ?
	public boolean equals(ItemContainer other);

	public ItemContainer clone(Item toClone);

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
	public Item getChild(final int index);

	/**
	 * Used for tree display.
	 * 
	 * @param child
	 * @return the index of the child item.
	 */
	public int getIndexOfChild(final IItem child);

	/**
	 * Transfer an IItem from one container to another
	 * @param item the IItem object to move.
	 * @param container the destination container.
	 */
	void transfer(Item item, ItemContainer container);

	/**
	 * Does the Item exist in the ItemContainer ?
	 * @param item Item looking for
	 * @return true iff found
	 */
	boolean contains(Item item);

}
