package au.net.hal9000.heisenberg.item;

import java.util.Stack;

import au.net.hal9000.heisenberg.units.Currency;

/**
 * @author bruins
 */
public interface IItem {

	/**
	 * @param toClone
	 *            toClone the item to clone.
	 * @return the clone of the cloned item.
	 */
	public Item clone(Item toClone);

	// Getters and Setters

	/**
	 * get the name of the item.
	 * 
	 * @return the name
	 */
	String getName();

	/**
	 * Set the name
	 * 
	 * @param pName
	 *            the name to set
	 */
	void setName(String pName);

	/**
	 * Get the description
	 * 
	 * @return the description
	 */
	String getDescription();

	/**
	 * Set the description
	 * 
	 * @param pDescription
	 *            the description
	 */
	void setDescription(String pDescription);

	// weight related
	/**
	 * @return weight before addition of other items such as those carried
	 */
	float getWeightBase();

	/**
	 * @param baseWeight
	 *            weight before addition of other items such as those carried.
	 */
	void setWeightBase(float baseWeight);

	/**
	 * The total weight. For simple items the weight is the weightBase. Will be
	 * overridden by collections.
	 * 
	 * @return the total weight
	 */
	float getWeight();

	// volume related
	/**
	 * volume before addition of other items such as those carried.
	 * 
	 * @return the volume that this item occupies without any contained items.
	 */
	float getVolumeBase();

	void setVolumeBase(float volumeWeight);

	/**
	 * For simple items the weight is the weightBase. Will be overridden by
	 * collections
	 * 
	 * @return the amount of 3D space that this item occupies.
	 */
	float getVolume();

	// valueBase
	/**
	 * value before addition of other items such as those carried.
	 * 
	 * @return The value before any contained items.
	 */
	Currency getValueBase();

	/**
	 * For simple items the value is the valueBase. Will be overridden by
	 * collections
	 * @return the total value including contained items.
	 */
	Currency getValue();

	void setValueBase(Currency pValueBase);

	// location
	IItem getLocation();

	void setLocation(IItem location);

	// hitPoints
	void setHitPoints(float hitPoints);

	float getHitPoints();

	Item getOwner();

	void setOwner(IItem owner);

	// misc methods

	// boolean equals(IItem other);

	/**
	 * Attempt to unlink the item from everything so that it can be garbage
	 * collected. Won't work if anything is referencing this item.
	 */
	void beNot();

	String toString();

	String detailedDescription();

	// Find items that match the criteria
	void accept(ItemVisitor visitor);

	/**
	 * This is used for tree traversal.
	 * 
	 * @return True unless this IItem has child Items.<br>
	 */
	boolean isLeaf();

	/**
	 * This is used for tree traversal.
	 * 
	 * @return the number of directly connected child Items.<br>
	 */
	int getChildCount();

	/**
	 * Fetch any numbered child of a node for the JTree. Our model returns IItem
	 * objects for all nodes in the tree. The JTree displays these by calling
	 * the IItem.toString() method.
	 * 
	 * @param index
	 *            the index of the object
	 * @return the object at that index
	 */
	Object getChild(int index);

	/**
	 * Figure out a child's position in its parent node.
	 * 
	 * @param child
	 *            the child we want the index of
	 * @return index the of the child
	 */
	int getIndexOfChild(IItem child);

	/**
	 * Get the children of a node.
	 * 
	 * @return the child {@link IItem} objects of a node
	 */
	Stack<IItem> getChildren();

}