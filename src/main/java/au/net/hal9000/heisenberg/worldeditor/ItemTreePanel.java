package au.net.hal9000.heisenberg.worldeditor;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreePath;

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
public class ItemTreePanel extends JPanel {
    /** serial version id. */
    private static final long serialVersionUID = 1L;

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
    public ItemTreePanel(Configuration config, Location location) {

        setRoot(location);
        setLayout(new BorderLayout());

        tree.setCellRenderer(new ItemTreeCellRenderer());

        // The JTree can get big, so allow it to scroll.
        JScrollPane scrollpane = new JScrollPane();
        scrollpane.setViewportView(tree);

        // The "Add" Button Panel
        JPanel addButtonPanel = new JPanel();

        // A JComboBox of Item types we can add
        itemClassesList = new JComboBox<String>(config.getItemClassIds());
        addButtonPanel.add(itemClassesList);

        // The "Add" Button
        JButton addButton = new JButton("Add");
        // http://www.chka.de/swing/tree/DefaultTreeModel.html

        ActionListener buttonActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                // TODO get from event. How?
                String eventName = event.getActionCommand();
                if ("Add".equals(eventName)) {
                    Object selNode = tree.getLastSelectedPathComponent();
                    if (selNode instanceof ItemContainer) {
                        ItemContainer selContainer = (ItemContainer) selNode;
                        // Add the desired item.
                        // A list of Items that could be added.

                        // TODO get from event
                        String itemClass = itemClassesList.getSelectedItem()
                                .toString();
                        Item newNode = Factory.createItem(itemClass);
                        try {
                            selContainer.add(selContainer.getChildCount(),newNode);
                        } catch (TooHeavyException | TooLargeException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        System.out.println("DEBUG: newNode is " + newNode);

                        TreePath path =  getPathToRoot(newNode);
                        System.out.println("DEBUG: path is " + path);
                        tree.scrollPathToVisible(path);
                        tree.setSelectionPath(path);
                        tree.startEditingAtPath(path);
                    } else {
                        toolkit.beep();
                        System.out.println("Not a container");
                    }
                }
            }
        };
        addButton.addActionListener(buttonActionListener);
        addButtonPanel.add(addButton);

        add(scrollpane, BorderLayout.NORTH);
        add(addButtonPanel, BorderLayout.CENTER);
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

    private TreePath getPathToRoot(Item node) {
        ArrayList<Item> itemArrayList = new ArrayList<Item>();
        Item debugTarget = node;
        while ((null != node)) {
            // insert at start of list.
            itemArrayList.add(0, node);
            node = node.getContainer();
        }
        System.out.println("getPathToRoot node='" + debugTarget + "', path="
                + itemArrayList);
        return new TreePath(itemArrayList.toArray(new Item[itemArrayList.size()]));
    }
    
}
