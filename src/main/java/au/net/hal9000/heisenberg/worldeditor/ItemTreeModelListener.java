package au.net.hal9000.heisenberg.worldeditor;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;

/**
 * THIS CODE IS NOT USED.  WHERE DO I LINK IT IN???????
 * 
 * @author bruins
 *
 */
public class ItemTreeModelListener implements TreeModelListener {
    
    /** tree model.*/
    private TreeModel treeModel;

    public ItemTreeModelListener(TreeModel treeModel){
        super();
        this.treeModel = treeModel;
        treeModel.addTreeModelListener(this);
    }
    /**
     * Method treeNodesChanged.
     * 
     * @param e
     *            TreeModelEvent
     * @see javax.swing.event.TreeModelListener#treeNodesChanged(TreeModelEvent)
     */
    @Override
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
