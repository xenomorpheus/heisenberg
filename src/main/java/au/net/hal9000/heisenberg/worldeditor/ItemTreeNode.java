package au.net.hal9000.heisenberg.worldeditor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import org.apache.log4j.Logger;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.api.ItemContainer;

/**
 * An adapter/wrapper around a Item object to make it look like a ItemTreeNode.
 *
 * This class contains major design flaw(s).
 *
 * TODO To detected items containing other items, it should ItemList not ItemContainer.
 * Or alternatively all items that contain items should be ItemContainer subclasses.
 *
 * @author bruins
 *
 */
public class ItemTreeNode implements MutableTreeNode {
	/**
	 * Field LOGGER.
	 */
	private static final Logger LOGGER = Logger.getLogger(ItemTreeNode.class.getName());

	private Item item;

	public ItemTreeNode(Item item) {
		super();
		this.item = item;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		TreeNode child = null;
		if (item instanceof ItemContainer) {
			ItemContainer container = (ItemContainer) item;
			Item childItem = container.get(childIndex);
			if (childItem == null) {
				LOGGER.error("getChildAt failed to get childIndex='" + childIndex + "' of " + item);
			} else {
				child = new ItemTreeNode(childItem);
			}
		}
		return child;
	}

	@Override
	public int getChildCount() {
		int count = 0;
		if (item instanceof ItemContainer) {
			ItemContainer container = (ItemContainer) item;
			count = container.size();
		}
		return count;
	}

	@Override
	public TreeNode getParent() {
		TreeNode parent = null;
		ItemContainer parentItem = item.getContainer();
		if (parentItem != null) {
			parent = new ItemTreeNode(parentItem);
		}
		return parent;
	}

	@Override
	public int getIndex(TreeNode node) {
		LOGGER.info("getIndex - item " + item);
		LOGGER.info("getIndex - node " + node + " of " + node.getClass());
		int index = -1;
		if (node instanceof ItemTreeNode) {
			ItemTreeNode itn = (ItemTreeNode) node;
			Item nodeItem = itn.getItem();

			if (item instanceof ItemContainer) {
				ItemContainer itemContainer = (ItemContainer) item;
				index = itemContainer.indexOf(nodeItem);
			} else {
				LOGGER.error("getIndex failed as item not of class ItemContainer. node " + node + " of " + node.getClass());
			}
		} else {
			LOGGER.error("getIndex failed as node is not of type ItemTreeNode. item " + item + ", node " + node + " of "
					+ node.getClass());
		}
		LOGGER.info("getIndex - index " + index);
		return index;
	}

	@Override
	public boolean getAllowsChildren() {
		boolean allowsChildren = false;
		if (item instanceof ItemContainer) {
			allowsChildren = true;
		}
		return allowsChildren;
	}

	@Override
	public boolean isLeaf() {
		boolean isLeaf = true;
		if (item instanceof ItemContainer) {
			isLeaf = false;
		}
		return isLeaf;
	}

	@Override
	public Enumeration<TreeNode> children() {
		Enumeration<TreeNode> enumeration = null;
		if (item instanceof ItemContainer) {
			ItemContainer container = (ItemContainer) item;
			ArrayList<TreeNode> treeNodeList = new ArrayList<>();
			for (Item item : container.getContents()) {
				treeNodeList.add(new ItemTreeNode(item));
			}
			enumeration = Collections.enumeration(treeNodeList);
		}
		return enumeration;
	}

	@Override
	public String toString() {
		return item.toString();
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		if (item instanceof ItemContainer) {
			ItemContainer container = (ItemContainer) item;
			if (child instanceof ItemTreeNode) {
				ItemTreeNode childItemTreeNode = (ItemTreeNode) child;
				Item childItem = childItemTreeNode.getItem();
				container.add(index, childItem);
			} else {
				LOGGER.error("insert failed as wrong type for child " + child + " type " + child.getClass());
			}
		} else {
			LOGGER.error("insert failed as wrong type for container item " + item + " type " + item.getClass());
		}
	}

	public Item getItem() {
		return item;
	}

	@Override
	public void remove(int index) {
		if (item instanceof ItemContainer) {
			ItemContainer container = (ItemContainer) item;
			container.remove(index);
		} else {
			LOGGER.error("remove failed as wrong type for container item " + item + " type " + item.getClass());
		}
	}

	@Override
	public void remove(MutableTreeNode child) {
		if (item instanceof ItemContainer) {
			ItemContainer container = (ItemContainer) item;
			if (child instanceof ItemTreeNode) {
				ItemTreeNode childTreeNode = (ItemTreeNode) child;
				Item childItem = childTreeNode.getItem();
				container.remove(childItem);
			} else {
				LOGGER.error("remove failed as wrong type for child " + child + " type " + child.getClass());
			}
		} else {
			LOGGER.error("remove failed as wrong type for container item " + item + " type " + item.getClass());
		}
	}

	@Override
	public void setUserObject(Object object) {
		if (object instanceof String) {
			item.setName((String) object);
		} else {
			LOGGER.error("setUserObject failed as wrong type for item " + item + " type " + object.getClass());
		}
	}

	@Override
	public void removeFromParent() {
		ItemContainer container = item.getContainer();
		if (container == null) {
			LOGGER.error("removeFromParent container is null");
		} else {
			container.remove(item);
		}
	}

	@Override
	public void setParent(MutableTreeNode newParent) {
		if (newParent instanceof ItemTreeNode) {
			ItemTreeNode parentItemTreeNode = (ItemTreeNode) newParent;
			Item parentItem = parentItemTreeNode.getItem();
			if (parentItem instanceof ItemContainer) {
				ItemContainer container = (ItemContainer) parentItem;
				container.add(item);
			} else {
				LOGGER.error("setParent failed as newParent's Item is not an ItemContainer as type is "
						+ parentItem.getClass());
			}
		} else {
			LOGGER.error("setParent failed as newParent not an ItemTreeNode as is type " + newParent.getClass());
		}
	}
}
