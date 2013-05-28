package au.net.hal9000.heisenberg.worldeditor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

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
     * 
     */
    private static final long serialVersionUID = 1L;
    Configuration config = null;
    Location location = null;

    // Create a TreeModel object to represent our m_tree of files
    ItemTreeModel model = null;
    JTree m_tree = new JTree();
    JComboBox itemClassesList = null;

    // TODO - constructor without setting config.
    public ItemTreePanel(Configuration config, Location location) {

        setLocation(location);
        setConfig(config);
        setLayout( new BorderLayout() );

        // Cell Editor
        // String elements[] = { "A", "B", "C", "D" };
        // JComboBox comboBox = new JComboBox(elements);
        // comboBox.setEditable(true);
        // TreeCellEditor comboEditor = new DefaultCellEditor(comboBox);

        // TreeCellEditor itemEditor = new ItemEditor();

        // DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) m_tree
        // .getCellRenderer();
        // TreeCellEditor cell_editor = new DefaultTreeCellEditor(m_tree,
        // renderer, itemEditor);
        // m_tree.setCellEditor(cell_editor);

        // The JTree can get big, so allow it to scroll.
        JScrollPane scrollpane = new JScrollPane();
        scrollpane.setViewportView(m_tree);
        //scrollpane.setSize(400, 700);

        // The "Add" Button Panel
        JPanel addButtonPanel = new JPanel();

        // A JComboBox of Items we can add
        Vector<String> itemClasses = config.getItemClasses();
        itemClassesList = new JComboBox(itemClasses.toArray());
        addButtonPanel.add(itemClassesList);

        // The "Add" Button
        JButton addButton = new JButton("Add");
        // http://www.chka.de/swing/tree/DefaultTreeModel.html

        ActionListener buttonActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                // TODO get from event. How?
                String eventName = event.getActionCommand();
                if ("Add".equals(eventName)) {
                    Item selNode = (Item) m_tree.getLastSelectedPathComponent();
                    if ((selNode != null) && !selNode.isLeaf()) {
                        ItemContainer selContainer = (ItemContainer) selNode;
                        // Add the desired item.
                        // A list of Items that could be added.

                        // TODO get from event
                        String itemClass = itemClassesList.getSelectedItem()
                                .toString();
                        Item newNode = Factory.createItem(itemClass);
                        model.insertNodeInto(newNode, selContainer,
                                selContainer.getChildCount());
                        Item[] nodes = model.getPathToRoot(newNode);
                        TreePath path = new TreePath(nodes);
                        System.out.println("DEBUG: path is " + path);
                        m_tree.scrollPathToVisible(path);
                        m_tree.setSelectionPath(path);
                        m_tree.startEditingAtPath(path);
                    }
                }
            }
        };
        addButton.addActionListener(buttonActionListener);
        addButtonPanel.add(addButton);
        
   
        // setLayoutManager(new BorderLayout());
        add(scrollpane, BorderLayout.NORTH);
        add(addButtonPanel, BorderLayout.CENTER);

/*        // TODO I can't remember what I was going to use these buttons for.
        // Buttons
        JPanel controlPanel = new JPanel();
        controlPanel.add(new JButton("Future"));
        controlPanel.add(new JButton("Expansion"));
        controlPanel.setVisible(true);
        add(controlPanel, BorderLayout.SOUTH);    */
    }

    public void setConfig(Configuration config) {
        this.config = config;
    }

    public void setLocation(Location location) {
        this.location = location;
        // Create a TreeModel object to represent our m_tree of files
        model = new ItemTreeModel(location);
        // Create a JTree and tell it to display our model
        m_tree.setModel(model);
        m_tree.setEditable(true);
        m_tree.setSelectionRow(0);

    }


}
