package au.net.hal9000.heisenberg.item.api;

import java.util.List;

import au.net.hal9000.heisenberg.item.exception.InvalidTypeException;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;
import au.net.hal9000.heisenberg.item.property.ItemVisitor;
import au.net.hal9000.heisenberg.units.Position;

public interface ItemContainer extends Item {

	// Getters and Setters
	/**
	 * Get the contents.
	 * 
	 * 
	 * @return the contents
	 */
	List<Item> getContents();

	/**
	 * Set the contents.
	 * 
	 * @param contents
	 *            the new contents of this container.
	 */
	void setContents(List<Item> contents);

	/**
	 * 
	 * @return The max weight that can be carried.
	 */
	float getWeightMax();

	/**
	 * Set the max weight that may be carried.
	 * 
	 * @param weightMax
	 *            The max weight that can be carried.
	 */
	void setWeightMax(float weightMax);

	/**
	 * get the maximum volume that this item can hold.
	 * 
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

	/**
	 * Does the Item exist in the ItemContainer ?
	 * 
	 * @param item
	 *            Item looking for
	 * 
	 * @return true iff found
	 */
	boolean contains(Item item);

	/**
	 * Get the number of items in the container.
	 * 
	 * 
	 * @return the number of items directly inside the container. Items with
	 *         other items don't add to the count as they are *NOT* directly
	 *         contained.
	 */

	int size();

	/**
	 * Used for tree display.
	 * 
	 * @param index
	 *            get child with with this index in list of items.
	 * 
	 * @return the item requested.
	 */
	Item get(int index);

	/**
	 * Add the Item to the contents.
	 * 
	 * @param item
	 * @throws TooLargeException
	 * @throws TooHeavyException
	 * @throws InvalidTypeException
	 * 
	 */
	void add(Item item) throws TooHeavyException, TooLargeException, InvalidTypeException;

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
	void add(int index, Item item) throws TooHeavyException, TooLargeException;

	/**
	 * Empty the bag into this location.
	 * 
	 * @param newLocation
	 * 
	 * @throws InvalidTypeException
	 * @throws TooLargeException
	 * @throws TooHeavyException
	 */
	void empty(ItemContainer newLocation) throws InvalidTypeException, TooHeavyException, TooLargeException;

	// TODO rename Visitor Pattern style
	// Find contents that match the criteria
	/**
	 * Method accept.
	 * 
	 * @param visitor
	 *            ItemVisitor
	 */
	void accept(ItemVisitor visitor);

	/**
	 * Remove the Item from the container.
	 * 
	 * @param item
	 *            the time to remove.
	 */
	void remove(Item item);

    /**
     * Change the position of the item within the ItemContainer.
     * 
     * Note: The request can fail or partially complete.<br>
     * E.g Can't pass through walls.
     * 
     * @param item the item to move withing the container.
     * @param requestedPosition
     *            the requested final position within the container.
     *            The position is NOT a delta but rather absolute WRT
     *            the position of the container.
     * 
     */
	void moveItemAbsolute(Item item, Position requestedPosition);

	int indexOf(Item child);

    /**
     * Add multiple items to the contents.
     * 
     * @param items
     *            the items to add.
     */
	void addAll(List<Item> items) throws InvalidTypeException, TooHeavyException, TooLargeException;

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
	void moveItemDelta(Item item, Position delta);

}