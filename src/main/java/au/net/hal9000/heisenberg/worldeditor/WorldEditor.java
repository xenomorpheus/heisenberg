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
import javax.swing.tree.TreePath;

import au.net.hal9000.heisenberg.item.Arrow;
import au.net.hal9000.heisenberg.item.Backpack;
import au.net.hal9000.heisenberg.item.Bag;
import au.net.hal9000.heisenberg.item.BagOfHolding;
import au.net.hal9000.heisenberg.item.Box;
import au.net.hal9000.heisenberg.item.Candle;
import au.net.hal9000.heisenberg.item.Cloak;
import au.net.hal9000.heisenberg.item.Cookie;
import au.net.hal9000.heisenberg.item.Crossbow;
import au.net.hal9000.heisenberg.item.CrossbowBolt;
import au.net.hal9000.heisenberg.item.Factory;
import au.net.hal9000.heisenberg.item.Horse;
import au.net.hal9000.heisenberg.item.Human;
import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.ItemContainer;
import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.MagicRing;
import au.net.hal9000.heisenberg.item.Quiver;
import au.net.hal9000.heisenberg.item.Ring;
import au.net.hal9000.heisenberg.item.Scabbard;
import au.net.hal9000.heisenberg.item.Shield;
import au.net.hal9000.heisenberg.item.Sword;
import au.net.hal9000.heisenberg.item.Torch;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.ConfigurationError;

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
                    location = null; // TODO free old world
                    location = getDemoWorld();
                    model = new ItemTreeModel(location);
                    // Create a JTree and tell it to display our model
                    m_tree.setModel(model);
                    m_tree.setEditable(true);
                    m_tree.setSelectionRow(0);
                }
                if ("Open".equals(eventName)) {
                    location = null; // TODO free old world
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

    public static Location getDemoWorld() {
        // Ad-hoc test world
        Location world = new Location("World");
        world.setWeightMax(100000);
        world.setVolumeMax(100000);

        // Scabbard
        Scabbard scabbard = new Scabbard();
        scabbard.add(new Sword());

        Scabbard scabbard2 = new Scabbard("Scabbard2");
        scabbard2.add(new Sword());

        Bag boh = new BagOfHolding(1);
        boh.add(new Bag("Bag1"));
        boh.add(new Box("Box1"));
        boh.add(new Candle());
        boh.add(new Cloak());
        boh.add(new Cookie("Cookie1"));
        boh.add(new Crossbow());
        boh.add(new CrossbowBolt());
        boh.add(new MagicRing());
        boh.add(new Ring());
        boh.add(scabbard);
        boh.add(new Torch());

        // a backpack of stuff
        Bag backpack = new Backpack("Backpack1");
        backpack.setWeightMax(100000);
        backpack.setVolumeMax(100000);
        backpack.add(boh);

        Quiver quiver = new Quiver();
        quiver.add(new Arrow());
        quiver.add(new Arrow());
        quiver.add(new Arrow());

        Bag bag2 = new Bag("Bag2");
        bag2.add(new Cookie("Cookie6"));
        bag2.add(new Cookie("Cookie7"));
        bag2.add(new Cookie("Cookie8"));
        backpack.add(bag2);

        // a human with a bag of cookies
        Human human = new Human("Human1");
        human.setWeightMax(100000);
        human.setVolumeMax(100000);

        // TODO backpack, quiver, shield not showing.
        human.add(new Shield());
        human.add(scabbard2);
        human.add(quiver);
        human.add(backpack);
        world.add(human);

        world.add(new Sword());
        world.add(new Horse());

        // bag3
        Bag bag3 = new Bag("Bag3");
        bag3.add(new Cookie("Cookie9"));
        bag3.add(new Cookie("Cookie10"));
        bag3.add(new Cookie("Cookie11"));
        world.add(bag3);

        return world;
    }

    public static void main(String[] args) {
        try {
            // Load some config
            Configuration config = new Configuration(
                    "src/test/resources/config.xml");
            new WorldEditor(config);
        } catch (ConfigurationError e) {
            e.printStackTrace();
        }
    }
}
