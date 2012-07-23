package au.net.hal9000.player.item;

import java.util.Stack;

import au.net.hal9000.player.units.*;

public interface IItem {

	// Feature

	public boolean isMagical();

	public boolean isExtraDimensional();

	public boolean isHumanoidFood();

	public boolean isHumanoidMount();

	public boolean isRingWearer();

	public boolean isLiving();

	public boolean isClothing();

	public boolean isSharp();

	// Getters and Setters

	// name
	public String getName();

	public void setName(String pName);

	// description
	public String getDescription();

	public void setDescription(String pDescription);

	// weight related
	/** weight before addition of other items such as those carried */
	public Weight getWeightBase();

	public void setWeightBase(Weight baseWeight);

	public void setWeightBase(float baseWeight);

	/**
	 * For simple items the weight is the weightBase. Will be overridden by
	 * collections.
	 * 
	 */
	public Weight getWeight();

	// weightMax - max weight that can be carried
	public Weight getWeightMax();

	public void setWeightMax(Weight loadMax);

	public void setWeightMax(float loadMax);

	// volume related
	/** volume before addition of other items such as those carried */
	public Volume getVolumeBase();

	public void setVolumeBase(Volume volumeWeight);

	public void setVolumeBase(float volumeWeight);

	/**
	 * For simple items the weight is the weightBase. Will be overridden by
	 * collections
	 */
	public Volume getVolume();

	// valueBase
	public Volume getVolumeMax();

	/** value before addition of other items such as those carried */
	public Currency getValueBase();

	/**
	 * For simple items the value is the valueBase. Will be overridden by
	 * collections
	 * 
	 */
	public Currency getValue();

	public void setValueBase(Currency pValueBase);

	// location
	public IItem getLocation();

	public void setLocation(IItem location);

	// hitPoints
	public void setHitPoints(float hitPoints);

	public float getHitPoints();

	public Item getOwner();

	public void setOwner(IItem owner);

	// misc methods

	// public boolean equals(IItem other);

	// Attempt to unlink the item from everything so that
	// it can be garbage collected.
	// Won't work if anything is referencing this item.
	public void beNot();

	public String toString();

	public String detailedDescription();

	// Find items that match the criteria
	public void accept(ItemVisitor visitor);

	// TODO public void accept(Damage damage);

	/**
	 * This is used for tree traversal.
	 * 
	 * @return True unless this IItem has child Items.<br>
	 */
	public boolean isLeaf();

	/**
	 * This is used for tree traversal.
	 * 
	 * @return the number of directly connected child Items.<br>
	 */
	public int getChildCount();

	/**
	 * Fetch any numbered child of a node for the JTree. Our model returns IItem
	 * objects for all nodes in the tree. The JTree displays these by calling
	 * the IItem.toString() method.
	 * 
	 * @param index
	 * @return
	 */
	public Object getChild(int index);

	/**
	 * Figure out a child's position in its parent node.
	 * 
	 * @param child
	 * @return
	 */
	public int getIndexOfChild(IItem child);

	/**
	 * Figure out a child's position in its parent node.
	 * 
	 * @return
	 */
	public Stack<IItem> getChildren();

	void setVolumeMax(float volumeMax);

	void setVolumeMax(Volume volumeMax);
}
