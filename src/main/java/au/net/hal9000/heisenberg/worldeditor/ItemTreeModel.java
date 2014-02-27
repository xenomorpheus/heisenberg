package au.net.hal9000.heisenberg.worldeditor;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.jdesktop.swingx.tree.TreeModelSupport;

import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.ItemContainer;

/**
 * This class provides a model that allows JTree to traverse the contents of an
 * Item. Note to have contents the Item needs to be a subclass of ItemContainer.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 **/
class ItemTreeModel implements TreeModel {
    /** We specify the root directory when we create the model. */
    private Item root;

    /** Support for PropertyChange messaging. */
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /**
     * instantiate the notification support
     */
    private TreeModelSupport support;

    /**
     * Constructor for ItemTreeModel.
     * 
     * @param root
     *            Item
     */
    public ItemTreeModel(Item root) {
        super();
        this.root = root;
        support = new TreeModelSupport(this);
    }

    /**
     * Return the root node.
     * 
     * @return Object
     * @see javax.swing.tree.TreeModel#getRoot()
     */
    @Override
    public Object getRoot() {
        // TODO System.out.println("getRoot " + root);
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
            ItemContainer container = (ItemContainer) node;
            isLeaf = container.getContentsCount() == 0;
        }
        // TODO System.out.println("isLeaf " + node + "= " + isLeaf);
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
        System.out.println("getChildCount " + node);
        return ((ItemContainer) node).getChildCount();
    }

    /**
     * Method getChild. Fetch any numbered child of a node for the JTree. Our
     * model returns Item objects for all nodes in the m_tree. The JTree
     * displays these by calling the Item.toString() method.
     * 
     * @param parent
     *            Object
     * @param index
     *            int
     * @return Object
     * @see javax.swing.tree.TreeModel#getChild(Object, int)
     */
    @Override
    public Object getChild(Object parent, int index) {
        Item child = ((ItemContainer) parent).getChildAt(index);
        System.out.println("getChild parent='" + parent + "', index=" + index
                + ", child=" + child);
        return child;
    }

    // Figure out a child's position in its parent node.
    /**
     * Method getIndexOfChild.
     * 
     * @param parent
     *            Object
     * @param child
     *            Object
     * @return int
     * @see javax.swing.tree.TreeModel#getIndexOfChild(Object, Object)
     */
    @Override
    public int getIndexOfChild(Object parent, Object child) {
        int index = ((ItemContainer) parent).getIndexOfChild((Item) child);
        System.out.println("getIndexOfChild " + parent + " " + child + "="
                + index);
        return index;
    }

    /**
     * This method is only invoked by the JTree for editable trees.
     * 
     * @param path
     *            TreePath
     * @param newValue
     *            Object
     * @see javax.swing.tree.TreeModel#valueForPathChanged(TreePath, Object)
     */
    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
        Item oldValue = (Item) path.getLastPathComponent();
        System.out.println("valueForPathChanged path=" + path + ", newValue='"
                + newValue + "', oldValue='" + oldValue + "'");
        this.pcs.firePropertyChange("value", oldValue, newValue);
    }

    /**
     * Method addTreeModelListener.
     * 
     * @param l
     *            TreeModelListener
     * @see javax.swing.tree.TreeModel#addTreeModelListener(TreeModelListener)
     */
    @Override
    public void addTreeModelListener(TreeModelListener l) {
        System.out.println("addTreeModelListener");
        support.addTreeModelListener(l);
    }

    /**
     * Method removeTreeModelListener.
     * 
     * @param l
     *            TreeModelListener
     * @see javax.swing.tree.TreeModel#removeTreeModelListener(TreeModelListener)
     */
    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        System.out.println("removeTreeModelListener");
        support.removeTreeModelListener(l);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        System.out.println("addPropertyChangeListener");
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        System.out.println("removePropertyChangeListener");
        this.pcs.removePropertyChangeListener(listener);
    }

}
