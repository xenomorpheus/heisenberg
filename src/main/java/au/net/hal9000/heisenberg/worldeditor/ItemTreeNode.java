package au.net.hal9000.heisenberg.worldeditor;

import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.api.ItemList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import org.apache.log4j.Logger;

/** An adapter/wrapper around a Item object to make it look like a ItemTreeNode. */
public class ItemTreeNode implements MutableTreeNode {
  /** Field LOGGER. */
  private static final Logger LOGGER = Logger.getLogger(ItemTreeNode.class.getName());

  private Item item;

  public ItemTreeNode(Item item) {
    super();
    this.item = item;
  }

  @Override
  public TreeNode getChildAt(int childIndex) {
    TreeNode child = null;
    if (item instanceof ItemList) {
      ItemList container = (ItemList) item;
      Item childItem = container.get(childIndex);
      if (childItem == null) {
        LOGGER.error("getChildAt failed to get childIndex='" + childIndex + "' of " + item);
      } else {
        child = new ItemTreeNode(childItem);
      }
    } else {
      LOGGER.warn("getChildAt called on non-container " + item);
    }
    return child;
  }

  @Override
  public int getChildCount() {
    int count = 0;
    if (item instanceof ItemList) {
      ItemList container = (ItemList) item;
      count = container.size();
    } else {
      LOGGER.warn("getChildCount called on non-container " + item);
    }
    return count;
  }

  @Override
  public TreeNode getParent() {
    TreeNode parent = null;
    Item parentItem = item.getContainer();
    if (parentItem != null) {
      // TODO should this be creating a new node or getting the existing node?
      parent = new ItemTreeNode(parentItem);
    }
    return parent;
  }

  @Override
  public int getIndex(TreeNode node) {
    LOGGER.trace("getIndex - item " + item);
    LOGGER.trace("getIndex - node " + node + " of " + node.getClass());
    int index = -1;
    if (node instanceof ItemTreeNode) {
      Item nodeItem = ((ItemTreeNode) node).getItem();
      if (item instanceof ItemList) {
        ItemList ItemList = (ItemList) item;
        index = ItemList.indexOf(nodeItem);
      } else {
        LOGGER.error(
            "getIndex failed as item not of class ItemList. node "
                + node
                + " of "
                + node.getClass());
      }
    } else {
      LOGGER.error(
          "getIndex failed as node is not of type ItemTreeNode. item "
              + item
              + ", node "
              + node
              + " of "
              + node.getClass());
    }
    LOGGER.trace("getIndex - index " + index);
    return index;
  }

  @Override
  public boolean getAllowsChildren() {
    boolean allowsChildren = false;
    if (item instanceof ItemList) {
      allowsChildren = true;
    } else {
      LOGGER.trace("getAllowsChildren called on: " + item);
    }
    return allowsChildren;
  }

  @Override
  public boolean isLeaf() {
    return !(item instanceof ItemList);
  }

  @Override
  public Enumeration<TreeNode> children() {
    Enumeration<TreeNode> enumeration = null;
    if (item instanceof ItemList) {
      ItemList items = (ItemList) item;
      ArrayList<TreeNode> treeNodes = new ArrayList<>();
      for (int index = 0; index < items.size(); index++) {
        treeNodes.add(new ItemTreeNode(items.get(index)));
      }
      enumeration = Collections.enumeration(treeNodes);
    }
    return enumeration;
  }

  @Override
  public String toString() {
    return item.toString();
  }

  @Override
  public void insert(MutableTreeNode child, int index) {
    if (item instanceof ItemList) {
      ItemList container = (ItemList) item;
      if (child instanceof ItemTreeNode) {
        ItemTreeNode childItemTreeNode = (ItemTreeNode) child;
        Item childItem = childItemTreeNode.getItem();
        container.add(index, childItem);
      } else {
        LOGGER.error(
            "insert failed as wrong type for child " + child + " type " + child.getClass());
      }
    } else {
      LOGGER.error(
          "insert failed as wrong type for container item " + item + " type " + item.getClass());
    }
  }

  public Item getItem() {
    return item;
  }

  @Override
  public void remove(int index) {
    if (item instanceof ItemList) {
      ItemList container = (ItemList) item;
      container.remove(index);
    } else {
      LOGGER.error(
          "remove failed as wrong type for container item " + item + " type " + item.getClass());
    }
  }

  @Override
  public void remove(MutableTreeNode child) {
    if (item instanceof ItemList) {
      ItemList container = (ItemList) item;
      if (child instanceof ItemTreeNode) {
        ItemTreeNode childTreeNode = (ItemTreeNode) child;
        Item childItem = childTreeNode.getItem();
        container.remove(childItem);
      } else {
        LOGGER.error(
            "remove failed as wrong type for child " + child + " type " + child.getClass());
      }
    } else {
      LOGGER.error(
          "remove failed as wrong type for container item " + item + " type " + item.getClass());
    }
  }

  @Override
  public void setUserObject(Object object) {
    LOGGER.info("TODO setUserObject item '" + item + "' was passed object " + object.getClass());
    // if (object instanceof Item) {
    //   item = (Item) object;
    // }
  }

  @Override
  public void removeFromParent() {
    ItemList container = item.getContainer();
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
      if (parentItem instanceof ItemList) {
        ItemList container = (ItemList) parentItem;
        container.add(item);
      } else {
        LOGGER.error(
            "setParent failed as newParent's Item is not an ItemList as type is "
                + parentItem.getClass());
      }
    } else {
      LOGGER.error(
          "setParent failed as newParent not an ItemTreeNode as is type " + newParent.getClass());
    }
  }
}
