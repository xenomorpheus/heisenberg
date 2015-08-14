package au.net.hal9000.heisenberg.worldeditor;

import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.apache.log4j.Logger;

import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.ItemContainer;

/**
 * This class is an adapter that provides a model that for JTree to use to
 * traverse the contents of an Item tree. Note to have contents the Item needs
 * to be a subclass of ItemContainer.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 **/
class ItemTreeModel extends AbstractTreeModel implements TreeModel {
    /**
     * Field LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ItemContainer.class
            .getName());
    /** We specify the root node when we create the model. */
    private Item root;

    /** Support for PropertyChange messaging. */
    // TODO private final PropertyChangeSupport pcs = new
    // PropertyChangeSupport(this);

    /**
     * instantiate the notification support
     */
    // TODO private TreeModelSupport treeModelSupport;

    /**
     * Constructor for ItemTreeModel.
     * 
     * @param root
     *            Item
     */
    ItemTreeModel(Item root) {
        super();
        this.root = root;
    }

    /**
     * Return the root node.
     * 
     * @return Object
     * @see javax.swing.tree.TreeModel#getRoot()
     */
    @Override
    public Object getRoot() {
        LOGGER.debug("getRoot " + root);
        return root;
    }

    /**
     * Tell JTree whether an object in the m_tree is a leaf or not.
     * 
     * @param node
     *            Object
     * @return boolean
     * @see javax.swing.tree.TreeModel#isLeaf(Object)
     */
    @Override
    public boolean isLeaf(Object node) {
        boolean isLeaf = true;

        if (node instanceof ItemContainer) {
            isLeaf = false;
        }
        LOGGER.debug("isLeaf " + node + "= " + isLeaf);
        return isLeaf;
    }

    /**
     * Tell JTree how many children a node has.
     * 
     * @param node
     *            node.
     * @return int number of children.
     * @see javax.swing.tree.TreeModel#getChildCount(Object)
     */
    @Override
    public int getChildCount(Object node) {
        int count = ((ItemContainer) node).getChildCount();
        LOGGER.debug("getChildCount " + node + " is " + count);
        return count;
    }

    /** {@inheritDoc} */
    @Override
    public Object getChild(Object parent, int index) {
        Item child = ((ItemContainer) parent).getChildAt(index);
        LOGGER.debug("getChild parent='" + parent + "', index=" + index
                + ", child=" + child);
        return child;
    }

    /** {@inheritDoc} */
    @Override
    public int getIndexOfChild(Object parent, Object child) {
        int index = ((ItemContainer) parent).getIndexOfChild((Item) child);
        LOGGER.debug("getIndexOfChild " + parent + " " + child + "=" + index);
        return index;
    }

    /** {@inheritDoc} */
    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
        // TODO The last element of the path must be updated with newValue (or just the properties?).
        LOGGER.error("valueForPathChanged - not implemented");

    }

}
