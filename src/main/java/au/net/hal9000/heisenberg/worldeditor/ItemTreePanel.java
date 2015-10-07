package au.net.hal9000.heisenberg.worldeditor;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.apache.log4j.Logger;

import au.net.hal9000.heisenberg.item.Factory;
import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.ItemContainer;
import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.exception.TooHeavyException;
import au.net.hal9000.heisenberg.item.exception.TooLargeException;
import au.net.hal9000.heisenberg.util.Configuration;

/**
 * A window with hierarchical representation of the game objects.
 * 
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class ItemTreePanel extends JPanel implements TreeModelListener,
        PropertyChangeListener {
    /** serial version id. */
    private static final long serialVersionUID = 1L;
    /**
     * Field LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ItemContainer.class
            .getName());
    // Create a TreeModel object to represent our tree of Item objects
    // at the specified location.
    /**
     * Field treeModel.
     */
    private ItemTreeModel treeModel = null;
    /**
     * Field tree.
     */
    private JTree tree = new JTree();
    /** Choose the character class. */
    private JComboBox<String> itemClassesList = null;

    /** toolkit. */
    private Toolkit toolkit = Toolkit.getDefaultToolkit();

    /**
     * Constructor.
     * 
     * @param config
     *            configuration to use for building the selection boxes.
     * @param location
     *            the location to display.
     */
    public ItemTreePanel(Configuration config, final Location location) {

        // The JTree can get big, so allow it to scroll.
        JScrollPane scrollpane = new JScrollPane();

        // The "Add" Button Panel
        JPanel addButtonPanel = new JPanel();

        setRoot(location);
        setLayout(new BorderLayout());

        tree.setCellRenderer(new ItemTreeCellRenderer());

        scrollpane.setViewportView(tree);

        // A JComboBox of Item types we can add
        Set<String> classIds = config.getItemClassIds();
        String[] classIdStrings = classIds.toArray(new String[classIds.size()]);
        itemClassesList = new JComboBox<String>(classIdStrings);
        addButtonPanel.add(itemClassesList);

        // The "Add" Button
        final JButton addButton = new JButton("Add");
        // http://www.chka.de/swing/tree/DefaultTreeModel.html

        ActionListener buttonActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                Object source = event.getSource();
                if (source == addButton) {
                    Object selNode = tree.getLastSelectedPathComponent();
                    if (selNode instanceof ItemContainer) {
                        ItemContainer selContainer = (ItemContainer) selNode;
                        // Create an Item of the requested type.
                        String itemClass = itemClassesList.getSelectedItem()
                                .toString();
                        Item newNode = Factory.createItem(itemClass);
                        // Add the new Item to the selected container.
                        try {

                            // TODO Bugfix the UI isn't being updated when new
                            // Item objects added to container.
                            // Is anything listening to the changes in the
                            // container?
                            // Perhaps the model?

                            selContainer.add(selContainer.getChildCount(),
                                    newNode);
                        } catch (TooHeavyException | TooLargeException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        LOGGER.debug("newNode is " + newNode);

                        TreePath path = getPathToRoot(newNode);
                        LOGGER.debug("path is " + path);
                        tree.scrollPathToVisible(path);
                        tree.setSelectionPath(path);
                        tree.startEditingAtPath(path);
                    } else {
                        toolkit.beep();
                        LOGGER.debug(selNode+" is not a container");
                    }
                }
            }
        };
        addButton.addActionListener(buttonActionListener);
        addButtonPanel.add(addButton);

        add(scrollpane, BorderLayout.NORTH);
        add(addButtonPanel, BorderLayout.SOUTH);
    }

    /**
     * Set the root object in this tree.
     * 
     * @param root
     *            the root object of the tree.
     */
    public void setRoot(Location root) {
        // Create a TreeModel object to represent our m_tree of files
        treeModel = new ItemTreeModel(root);

        // This class will listen to changes in the TreeModel.
        treeModel.addTreeModelListener(this);

        // Create a JTree and tell it to display our model
        tree.setModel(treeModel);
        tree.setEditable(true);
        tree.setSelectionRow(0);
    }

    /**
     * Get path from root to the target node.
     * 
     * @param node
     *            target node
     * 
     * @return a TreePath of nodes from root to the target node.
     */
    static TreePath getPathToRoot(Item node) {
        List<Item> itemList = new ArrayList<Item>();
        while ((null != node)) {
            itemList.add(node);
            node = node.getContainer();
        }
        return new TreePath(itemList.toArray(new Item[itemList.size()]));
    }

    /** {@inheritDoc} */
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

        LOGGER.debug("The user has finished editing the node.");
        LOGGER.debug("New value: " + node.getUserObject());
    }

    /** {@inheritDoc} */
    @Override
    public void treeNodesInserted(TreeModelEvent e) {
        // TODO finish
        LOGGER.debug("Node Inserted.");
    }

    /** {@inheritDoc} */
    @Override
    public void treeNodesRemoved(TreeModelEvent e) {
        // TODO finish
        LOGGER.debug("treeNodesRemoved - Node Removed.");
    }

    /** {@inheritDoc} */
    @Override
    public void treeStructureChanged(TreeModelEvent e) {
        // TODO finish
        LOGGER.debug("treeStructureChanged - Node Changed.");
    }

    /** {@inheritDoc} */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO Auto-generated method stub
        LOGGER.debug("propertyChange - Node Changed.");
    }
}
