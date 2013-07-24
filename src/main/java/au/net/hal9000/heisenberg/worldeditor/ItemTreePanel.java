package au.net.hal9000.heisenberg.worldeditor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import au.net.hal9000.heisenberg.util.Configuration;

public class ItemTreePanel extends JPanel {
    /**
     * A window with hierarchical representation of the game objects.
     */
    private static final long serialVersionUID = 1L;
    Configuration config = null;
    Location location = null;

    // Create a TreeModel object to represent our tree of Item objects
    // at the specified location.
    ItemTreeModel treeModel = null;
    JTree tree = new JTree();
    JComboBox itemClassesList = null;

    // TODO - constructor without setting config.
    public ItemTreePanel(Configuration config, Location location) {

        setLocation(location);
        setConfig(config);
        setLayout(new BorderLayout());

        tree.setCellRenderer(new ItemTreeCellRenderer());

        // The JTree can get big, so allow it to scroll.
        JScrollPane scrollpane = new JScrollPane();
        scrollpane.setViewportView(tree);

        // The "Add" Button Panel
        JPanel addButtonPanel = new JPanel();

        // A JComboBox of Item types we can add
        itemClassesList = new JComboBox(config.getItemClassIds());
        addButtonPanel.add(itemClassesList);

        // The "Add" Button
        JButton addButton = new JButton("Add");
        // http://www.chka.de/swing/tree/DefaultTreeModel.html

        ActionListener buttonActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                // TODO get from event. How?
                String eventName = event.getActionCommand();
                if ("Add".equals(eventName)) {
                    Item selNode = (Item) tree.getLastSelectedPathComponent();
                    if ((selNode != null) && !selNode.isLeaf()) {
                        ItemContainer selContainer = (ItemContainer) selNode;
                        // Add the desired item.
                        // A list of Items that could be added.

                        // TODO get from event
                        String itemClass = itemClassesList.getSelectedItem()
                                .toString();
                        Item newNode = Factory.createItem(itemClass);
                        treeModel.insertNodeInto(newNode, selContainer,
                                selContainer.getChildCount());
                        Item[] nodes = treeModel.getPathToRoot(newNode);
                        TreePath path = new TreePath(nodes);
                        System.out.println("DEBUG: path is " + path);
                        tree.scrollPathToVisible(path);
                        tree.setSelectionPath(path);
                        tree.startEditingAtPath(path);
                    }
                }
            }
        };
        addButton.addActionListener(buttonActionListener);
        addButtonPanel.add(addButton);

        add(scrollpane, BorderLayout.NORTH);
        add(addButtonPanel, BorderLayout.CENTER);
    }

    public void setConfig(Configuration config) {
        this.config = config;
    }

    public void setLocation(Location location) {
        this.location = location;
        // Create a TreeModel object to represent our m_tree of files
        treeModel = new ItemTreeModel(location);
        // Create a JTree and tell it to display our model
        tree.setModel(treeModel);
        tree.setEditable(true);
        tree.setSelectionRow(0);
    }

}
