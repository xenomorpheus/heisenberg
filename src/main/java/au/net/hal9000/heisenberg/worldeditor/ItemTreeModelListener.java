package au.net.hal9000.heisenberg.worldeditor;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;

import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.ItemContainer;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;

/**
 * THIS CODE IS NOT USED.  WHERE DO I LINK IT IN???????
 * 
 * @author bruins
 *
 */
public class ItemTreeModelListener implements TreeModelListener {
    /**
     * Method treeNodesChanged.
     * 
     * @param e
     *            TreeModelEvent
     * @see javax.swing.event.TreeModelListener#treeNodesChanged(TreeModelEvent)
     */
    public void treeNodesChanged(TreeModelEvent e) {
        DefaultMutableTreeNode node;
        node = (DefaultMutableTreeNode) (e.getTreePath().getLastPathComponent());

        /*
         * If the event lists children, then the changed node is the child of
         * the node we've already gotten. Otherwise, the changed node and the
         * specified node are the same.
         */

        int index = e.getChildIndices()[0];
        node = (DefaultMutableTreeNode) (node.getChildAt(index));

        System.out.println("The user has finished editing the node.");
        System.out.println("New value: " + node.getUserObject());
    }

    /**
     * Method treeNodesInserted.
     * 
     * @param e
     *            TreeModelEvent
     * @see javax.swing.event.TreeModelListener#treeNodesInserted(TreeModelEvent)
     */
    @Override
    public void treeNodesInserted(TreeModelEvent e) {
        System.out.println("Node Inserted.");
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
    public void insertNodeInto(Item newNode, Item selNode, int childCount)
            throws TooHeavyException, TooLargeException {
        if (selNode instanceof ItemContainer) {
            ((ItemContainer) selNode).add(childCount, (Item) newNode);
        } else {
            System.out.println("bad parent container:" + selNode);
        }
    }

    /**
     * Method treeNodesRemoved.
     * 
     * @param e
     *            TreeModelEvent
     * @see javax.swing.event.TreeModelListener#treeNodesRemoved(TreeModelEvent)
     */
    @Override
    public void treeNodesRemoved(TreeModelEvent e) {
        System.out.println("treeNodesRemoved - Node Removed.");
    }

    /**
     * Method treeStructureChanged.
     * 
     * @param e
     *            TreeModelEvent
     * @see javax.swing.event.TreeModelListener#treeStructureChanged(TreeModelEvent)
     */
    @Override
    public void treeStructureChanged(TreeModelEvent e) {
        System.out.println("treeStructureChanged - Node Changed.");
    }
}
