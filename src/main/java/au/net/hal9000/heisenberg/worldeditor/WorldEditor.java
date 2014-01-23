package au.net.hal9000.heisenberg.worldeditor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.DummyData;
import au.net.hal9000.heisenberg.util.ItemIcon;

/**
 * The main application window. Shows a tree of the items in this world.
 */
public class WorldEditor extends JFrame {

    /** serial version id. */
    private static final long serialVersionUID = 1L;

    /** Persistence Entity Manager. */
    private EntityManager entityManager = null;

    /** the Location object at the root of this world. */
    private Location location = null;

    /** The panel showing the tree of Item objects. */
    private ItemTreePanel itemTreePanel = null;

    /** Persistence unit name for Entity Manager. */
    private static final String PERSISTENCE_UNIT_NAME = "items";

    /**
     * Constructor.
     * 
     * @throws ConfigurationError
     */
    public WorldEditor() throws ConfigurationError {

        // Persistence
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        entityManager = factory.createEntityManager();

        // Config
        Configuration config = DummyData.config();

        // Icons
        ItemIcon.setIcon(config);

        // Main Frame
        setSize(400, 600);

        // This will center the JFrame in the middle of the screen
        setLocationRelativeTo(null);

        setBounds(100, 100, 894, 634);

        // Main container
        setLayout(new BorderLayout());
        itemTreePanel = new ItemTreePanel(config, location);
        itemTreePanel.setVisible(true);
        add(itemTreePanel, BorderLayout.NORTH);

        // Menus
        ActionListener menuActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                // TODO get from event. How?
                String eventName = event.getActionCommand();

                if ("New".equals(eventName)) {
                    setLocation(new Location());
                }
                if ("Demo".equals(eventName)) {
                    setLocation(DummyData.getDemoWorld());
                }
                if ("Open".equals(eventName)) {
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
        JMenuBar jmb = getMenus(menuActionListener);
        setJMenuBar(jmb);

        // Exit by closing window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                exitProgram();
            }
        });

    }

    /** getter for Entity Manager. @return Entity Manager. */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /** setter for Entity Manager. @param entityManager Entity Manager. */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * set the location.
     * 
     * @param location
     *            new location.
     */
    public void setLocation(Location location) {
        if (location != null) {
            location.beNot();
            location = null;
        }
        this.location = location;
        itemTreePanel.setLocation(location);
    }

    /** quit the program. */
    public void exitProgram() {
        if (entityManager != null) {
            entityManager.close();
            entityManager = null;
        }
        System.out.println("End");
        System.exit(0);
    }

    /**
     * Construct UI menu bar.
     * @param actionListener the listener for the menu events.
     * @return a menu bar.
     */
    public static JMenuBar getMenus(ActionListener actionListener) {
        JMenuBar menubar = new JMenuBar();
        // Application Menu
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
