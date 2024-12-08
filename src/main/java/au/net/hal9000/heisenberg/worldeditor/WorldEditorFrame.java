package au.net.hal9000.heisenberg.worldeditor;

import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.api.ItemContainer;
import au.net.hal9000.heisenberg.item.property.ItemVisitor;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.ItemIcon;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import org.apache.log4j.Logger;

/** The main application window. Shows a tree of the items in this world. */
public class WorldEditorFrame extends JFrame {

  private static final String MENU_DEMO = "Demo";

  private static final String MENU_SAVE = "Save";

  private static final String MENU_CLOSE = "Close";

  private static final String MENU_OPEN = "Open";

  private static final String MENU_NEW = "New";

  private static final String MENU_QUIT = "Quit";

  private static final String MENU_DEBUG_TREE = "Debug Tree";

  /** serial version id. */
  private static final long serialVersionUID = 1L;

  /** Persistence Entity Manager. */
  private EntityManagerFactory factory =
      Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

  private EntityManager entityManager = null;

  /** the Location object at the root of this world. */
  private Location location = null;

  /** The panel showing the tree of Item objects. */
  private ItemTreePanel itemTreePanel = null;

  /** Persistence unit name for Entity Manager. */
  private static final String PERSISTENCE_UNIT_NAME = "items";

  private static final Logger LOGGER = Logger.getLogger(WorldEditorFrame.class.getName());

  /**
   * Constructor.
   *
   * @throws ConfigurationError
   */
  public WorldEditorFrame() throws ConfigurationError {
    super();
    init();
  }

  private void init() {

    /** Config. */
    final Configuration config = Configuration.lastConfig();
    /** Persistence. */
    entityManager = factory.createEntityManager();

    // Icons
    ItemIcon.setIcon(config);

    // Main Frame
    setSize(400, 600);

    // This will centre the JFrame in the middle of the screen
    setLocationRelativeTo(null);

    setBounds(100, 100, 894, 634);

    // Main container
    location = DemoEnvironment.getDemoWorld();
    location.setWeightMax(1000000);
    location.setVolumeMax(1000000);

    setLayout(new BorderLayout());
    itemTreePanel = new ItemTreePanel(config, location);
    itemTreePanel.setVisible(true);
    add(itemTreePanel, BorderLayout.NORTH);

    // Menus
    final ActionListener menuActionListener =
        new ActionListener() {
          public void actionPerformed(ActionEvent event) {
            String eventName = event.getActionCommand();
            if (MENU_NEW.equals(eventName)) {
              location = new Location();
              location.setWeightMax(1000000);
              location.setVolumeMax(1000000);
              setLocation(location);
            }
            if (MENU_DEMO.equals(eventName)) {
              setLocation(DemoEnvironment.getDemoWorld());
            }
            if (MENU_DEBUG_TREE.equals(eventName)) {
              debugTreePrint();
            }
            if (MENU_OPEN.equals(eventName)) {
              LOGGER.warn("Open not implemented");
              // TODO load.
              // TODO Create a project object to contain details and a
              // pointer to top location.
            }
            if (MENU_SAVE.equals(eventName)) {
              if (null != entityManager) {
                entityManager.getTransaction().begin();
                entityManager.persist(location);
                entityManager.getTransaction().commit();
              }
            }
            if (MENU_QUIT.equals(eventName)) {
              exitProgram();
            }
          }

          private void debugTreePrint() {
            ItemVisitor visitor =
                new ItemVisitor() {

                  @Override
                  public void visit(Item item) {
                    StringBuilder sb = new StringBuilder(item.toString());
                    ItemContainer container = item.getContainer();
                    if (container != null) {
                      sb.append(" located in " + container);
                    }
                    System.out.println(sb.toString());
                  }

                  @Override
                  public void visit(List<Item> items) {
                    for (Item item : items) {
                      visit(item);
                    }
                  }
                };
            location.accept(visitor);
          }
        };
    JMenuBar jmb = getMenus(menuActionListener);
    setJMenuBar(jmb);

    // Exit by closing window
    addWindowListener(
        new WindowAdapter() {
          public void windowClosing(WindowEvent event) {
            exitProgram();
          }
        });
  }

  /**
   * set the location.
   *
   * @param newLocation new location.
   */
  public void setLocation(Location newLocation) {
    if (null != location) {
      location.beNot();
      location = null;
    }
    location = newLocation;
    itemTreePanel.setRoot(newLocation);
  }

  /** quit the program. */
  public void exitProgram() {
    if (null != entityManager) {
      entityManager.close();
      entityManager = null;
    }
    LOGGER.info("Exiting");
    System.exit(0);
  }

  /**
   * Construct UI menu bar.
   *
   * @param actionListener the listener for the menu events.
   * @return a menu bar.
   */
  public static JMenuBar getMenus(ActionListener actionListener) {
    JMenuBar menuBar = new JMenuBar();
    // Application Menu
    JMenu appMenu = new JMenu("WorldEditor");
    JMenuItem appQuit = new JMenuItem(MENU_QUIT);
    appQuit.addActionListener(actionListener);
    appMenu.add(appQuit);
    menuBar.add(appMenu);
    // File Menu
    JMenu fileMenu = new JMenu("File");
    JMenuItem fileNew = new JMenuItem(MENU_NEW);
    fileNew.addActionListener(actionListener);
    fileMenu.add(fileNew);
    JMenuItem fileOpen = new JMenuItem(MENU_OPEN);
    fileOpen.addActionListener(actionListener);
    fileMenu.add(fileOpen);
    JMenuItem fileClose = new JMenuItem(MENU_CLOSE);
    fileClose.addActionListener(actionListener);
    fileMenu.add(fileClose);
    JMenuItem fileSave = new JMenuItem(MENU_SAVE);
    fileSave.addActionListener(actionListener);
    fileMenu.add(fileSave);
    JMenuItem fileDemo = new JMenuItem(MENU_DEMO);
    fileDemo.addActionListener(actionListener);
    fileMenu.add(fileDemo);
    JMenuItem fileDebugTree = new JMenuItem(MENU_DEBUG_TREE);
    fileDebugTree.addActionListener(actionListener);
    fileMenu.add(fileDebugTree);
    menuBar.add(fileMenu);
    // Edit Menu
    JMenu editMenu = new JMenu("Edit");
    JMenuItem editCut = new JMenuItem("Cut");
    editCut.addActionListener(actionListener);
    editMenu.add(editCut);
    JMenuItem editCopy = new JMenuItem("Copy");
    editCopy.addActionListener(actionListener);
    editMenu.add(editCopy);
    JMenuItem editPaste = new JMenuItem("Paste");
    editPaste.addActionListener(actionListener);
    editMenu.add(editPaste);
    JMenuItem editInsert = new JMenuItem("Insert");
    editInsert.addActionListener(actionListener);
    editMenu.add(editInsert);
    menuBar.add(editMenu);
    return menuBar;
  }
}
