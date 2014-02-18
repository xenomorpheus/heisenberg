package au.net.hal9000.heisenberg.worldeditor;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import java.util.ArrayList;
import org.jdesktop.swingx.tree.TreeModelSupport;

import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.ItemContainer;

/**
 * This class provides a model that allows JTree to traverse the contents of an
 * Item. Note to have contents the Item needs to be a subclass of ItemContainer.
 **/
class ItemTreeModel implements TreeModel {
    /** We specify the root directory when we create the model.*/
    private Item root;

    // prepare fix issue 1: instantiate the notification support
    private TreeModelSupport support;

    public ItemTreeModel(Item root) {
        this.root = root;
        support = new TreeModelSupport(this);
    }

    // The model knows how to return the root object of the m_tree
    @Override
    public Object getRoot() {
        return root;
    }

    // Tell JTree whether an object in the m_tree is a leaf or not
    @Override
    public boolean isLeaf(Object node) {
        return ((Item) node).isLeaf();
    }

    // fix issue 1: accept listener
    @Override
    public void addTreeModelListener(TreeModelListener l) {
        support.addTreeModelListener(l);
    }

    /**
     * Add a node into our model.
     * 
     * @param newNode
     *            the new node.
     * @param selNode
     *            the location for the new node.
     * @param childCount
     *            the index number in the selNode where the newNode is.
     */

    // fix issue 2: notify the listeners on inserts
    @SuppressWarnings("deprecation")
    public void insertNodeInto(final Item newNode, final ItemContainer selNode,
            final int childCount) {
        selNode.add(childCount, (Item) newNode);
        newNode.setContainer(selNode);
        support.fireChildAdded(new TreePath(getPathToRoot((Item) selNode)),
                childCount, newNode);
    }

    /**
     * Get path up to the root node.
     * 
     * @param node
     *            starting node
     * @return an array of nodes starting at the root
     */

    public Item[] getPathToRoot(Item node) {
        ArrayList<Item> itemArrayList = new ArrayList<Item>();
        while ((node != null)) {
            itemArrayList.add(0, node);
            node = node.getContainer();
        }
        return itemArrayList.toArray(new Item[itemArrayList.size()]);
    }

    // Tell JTree how many children a node has
    @Override
    public int getChildCount(Object node) {
        return ((ItemContainer) node).getChildCount();
    }

    // Fetch any numbered child of a node for the JTree.
    // Our model returns Item objects for all nodes in the m_tree. The
    // JTree displays these by calling the Item.toString() method.
    @Override
    public Object getChild(Object parent, int index) {
        return ((ItemContainer) parent).getChildAt(index);
    }

    // Figure out a child's position in its parent node.
    @Override
    public int getIndexOfChild(Object parent, Object child) {
        return ((ItemContainer) parent).getIndexOfChild((Item) child);
    }

    // This method is only invoked by the JTree for editable trees.
    @Override
    public void valueForPathChanged(TreePath path, Object newvalue) {
        Item item = (Item) path.getLastPathComponent();
        item.setName((String) newvalue);
        // fireTreeNodesChanged(new TreeModelEvent(this, path));
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        System.out.println("removeTreeModelListener");
    }

    // Just an outline
    class MyTreeModelListener implements TreeModelListener {
        public void treeNodesChanged(TreeModelEvent e) {
            DefaultMutableTreeNode node;
            node = (DefaultMutableTreeNode) (e.getTreePath()
                    .getLastPathComponent());

            /*
             * If the event lists children, then the changed node is the child
             * of the node we've already gotten. Otherwise, the changed node and
             * the specified node are the same.
             */

            int index = e.getChildIndices()[0];
            node = (DefaultMutableTreeNode) (node.getChildAt(index));

            System.out.println("The user has finished editing the node.");
            System.out.println("New value: " + node.getUserObject());
        }

        @Override
        public void treeNodesInserted(TreeModelEvent e) {
            System.out.println("Node Inserted.");
        }

        /**
         * Add a node into our model.
         * @param newNode the new node.
         * @param selNode the location for the new node.
         * @param childCount the index number in the selNode where the newNode is.
         */
        @SuppressWarnings("deprecation")
        public void insertNodeInto(final Item newNode, final Item selNode,
                final int childCount) {
            if (selNode instanceof ItemContainer) {
                ((ItemContainer) selNode).add(childCount, (Item) newNode);
            } else {
                System.out.println("bad parent container:" + selNode);
            }
        }

        @Override
        public void treeNodesRemoved(TreeModelEvent e) {
            System.out.println("treeNodesRemoved - Node Removed.");
        }

        @Override
        public void treeStructureChanged(TreeModelEvent e) {
            System.out.println("treeStructureChanged - Node Changed.");
        }
    }
}
