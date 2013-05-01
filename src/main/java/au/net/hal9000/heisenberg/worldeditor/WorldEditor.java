package au.net.hal9000.heisenberg.worldeditor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreePath;

import au.net.hal9000.heisenberg.item.Factory;
import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.ItemContainer;
import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.DummyData;

public class WorldEditor {
    Configuration config = null;
    Location location = null;
    EntityManager entityManager = null;

    // Create a TreeModel object to represent our m_tree of files
    ItemTreeModel model = null;
    JTree m_tree = null;
    JComboBox itemClassesList = null;

    public WorldEditor(final Configuration pConfig) {
        config = pConfig;

        final String PERSISTENCE_UNIT_NAME = "items";
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        entityManager = factory.createEntityManager();

        // The world
        location = new Location("World");
        location.setWeightMax(100000);
        location.setVolumeMax(100000);

        // Create a TreeModel object to represent our m_tree of files
        model = new ItemTreeModel(location);
        m_tree = new JTree();

        // Create a JTree and tell it to display our model
        m_tree.setModel(model);
        m_tree.setEditable(true);
        m_tree.setSelectionRow(0);

        // Cell Editor
        // String elements[] = { "A", "B", "C", "D" };
        // JComboBox comboBox = new JComboBox(elements);
        // comboBox.setEditable(true);
        // TreeCellEditor comboEditor = new DefaultCellEditor(comboBox);

        TreeCellEditor itemEditor = new ItemEditor();

        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) m_tree
                .getCellRenderer();
        TreeCellEditor cell_editor = new DefaultTreeCellEditor(m_tree,
                renderer, itemEditor);
        m_tree.setCellEditor(cell_editor);

        // The JTree can get big, so allow it to scroll.
        JScrollPane scrollpane = new JScrollPane(m_tree);

        // Display it all in a window and make the window appear
        JFrame frame = new JFrame("Item Creator");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                exitProgram();
            }
        });

        frame.getContentPane().add(scrollpane, BorderLayout.CENTER);

        // Add Panel
        JPanel addPanel = new JPanel();

        // A JComboBox of Items we can add
        Vector<String> itemClasses = config.getItemClasses();
        itemClassesList = new JComboBox(itemClasses.toArray());
        addPanel.add(itemClassesList);

        // The "Add" Button
        JButton addButton = new JButton("Add");
        // http://www.chka.de/swing/tree/DefaultTreeModel.html
        ActionListener actionListener = new ActionListener() {
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
                if ("Demo".equals(eventName)) {
                    location.beNot();
                    location = null;
                    location = DummyData.getDemoWorld();
                    model = new ItemTreeModel(location);
                    // Create a JTree and tell it to display our model
                    m_tree.setModel(model);
                    m_tree.setEditable(true);
                    m_tree.setSelectionRow(0);
                }
                if ("Open".equals(eventName)) {
                    location.beNot();
                    location = null;
                    // TODO load.
                    // TODO Create a project object to contain details and a
                    // pointer to top location.
                }
                if ("Save".equals(eventName)) {
                    if (entityManager != null) {
                        entityManager.getTransaction().begin();
                        entityManager.persist(location);
                        entityManager.getTransaction().commit();
                    }
                }
                if ("Quit".equals(eventName)) {
                    exitProgram();
                }

            }
        };
        addButton.addActionListener(actionListener);

        addPanel.add(addButton);
        frame.getContentPane().add(addPanel, BorderLayout.SOUTH);

        // Menu
        JMenuBar jmb = getMenus(actionListener);
        frame.setJMenuBar(jmb);

        frame.setSize(400, 600);
        // This will center the JFrame in the middle of the screen
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    public void exitProgram() {
        if (entityManager != null) {
            entityManager.close();
            entityManager = null;
        }
        System.out.println("End");
        System.exit(0);
    }

    public static JMenuBar getMenus(ActionListener actionListener) {
        JMenuBar menubar = new JMenuBar();
        // File Menu
        JMenu appemenu = new JMenu("WorldEditor");
        JMenuItem appItem1 = new JMenuItem("Quit");
        appItem1.addActionListener(actionListener);
        appemenu.add(appItem1);
        menubar.add(appemenu);
        // File Menu
        JMenu filemenu = new JMenu("File");
        JMenuItem fileItem1 = new JMenuItem("New");
        fileItem1.addActionListener(actionListener);
        filemenu.add(fileItem1);
        JMenuItem fileItem2 = new JMenuItem("Open");
        fileItem2.addActionListener(actionListener);
        filemenu.add(fileItem2);
        JMenuItem fileItem3 = new JMenuItem("Close");
        fileItem3.addActionListener(actionListener);
        filemenu.add(fileItem3);
        JMenuItem fileItem4 = new JMenuItem("Save");
        fileItem4.addActionListener(actionListener);
        filemenu.add(fileItem4);
        JMenuItem fileItem5 = new JMenuItem("Demo");
        fileItem5.addActionListener(actionListener);
        filemenu.add(fileItem5);
        menubar.add(filemenu);
        // Edit Menu
        JMenu editmenu = new JMenu("Edit");
        JMenuItem editItem1 = new JMenuItem("Cut");
        editItem1.addActionListener(actionListener);
        editmenu.add(editItem1);
        JMenuItem editItem2 = new JMenuItem("Copy");
        editItem2.addActionListener(actionListener);
        editmenu.add(editItem2);
        JMenuItem editItem3 = new JMenuItem("Paste");
        editItem3.addActionListener(actionListener);
        editmenu.add(editItem3);
        JMenuItem editItem4 = new JMenuItem("Insert");
        editItem4.addActionListener(actionListener);
        editmenu.add(editItem4);
        menubar.add(editmenu);
        return menubar;
    }


}
