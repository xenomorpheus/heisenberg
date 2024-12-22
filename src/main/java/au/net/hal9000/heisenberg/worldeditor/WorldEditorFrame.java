package au.net.hal9000.heisenberg.worldeditor;

import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.api.ItemContainer;
import au.net.hal9000.heisenberg.item.property.ItemVisitor;
import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.JsonItems;
import au.net.hal9000.heisenberg.util.PersistEntities;
import au.net.hal9000.heisenberg.worldeditor.demo.DemoEnvironment;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import org.apache.log4j.Logger;

/** The main application window. Shows a tree of the items in this world. */
public class WorldEditorFrame extends JFrame {

  private static final String MENU_NEW = "New";

  private static final String MENU_SAVE = "Save";

  private static final String MENU_IMPORT = "Import...";

  private static final String MENU_EXPORT = "Export...";

  private static final String MENU_LOAD_DEMO = "Load Demo";

  private static final String MENU_DEBUG_TREE = "Debug Tree";

  private static final String MENU_QUIT = "Quit";

  /** the Location object at the root of this world. */
  private Location location = null;

  /** The panel showing the tree of Item objects. */
  private ItemTreePanel itemTreePanel = null;

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

    // Main Frame
    setSize(400, 600);

    // This will centre the JFrame in the middle of the screen
    setLocationRelativeTo(null);

    setBounds(100, 100, 894, 634);

    // Main container
    location = DemoEnvironment.getDemoWorld();

    setLayout(new BorderLayout());
    itemTreePanel = new ItemTreePanel(config, location);
    itemTreePanel.setVisible(true);
    add(itemTreePanel, BorderLayout.NORTH);

    // Menus
    final ActionListener menuActionListener =
        new ActionListener() {
          static final File pathname = new File("/tmp/heisenberg_world_editor.json");

          public void actionPerformed(ActionEvent event) {
            String eventName = event.getActionCommand();
            if (MENU_NEW.equals(eventName)) {
              location = new Location();
              setLocation(location);
            }
            if (MENU_SAVE.equals(eventName)) {
              PersistEntities.save(location);
            }
            if (MENU_IMPORT.equals(eventName)) {
              // TODO choose where in structure to add new items.
              for (var item : JsonItems.importFromFile(pathname)) {
                location.add(item);
              }
            }
            if (MENU_EXPORT.equals(eventName)) {
              // TODO choose where in structure to export from.
              List<Item> items = new ArrayList<>();
              items.add(location);
              JsonItems.export(pathname, items);
            }
            if (MENU_LOAD_DEMO.equals(eventName)) {
              setLocation(DemoEnvironment.getDemoWorld());
            }
            if (MENU_DEBUG_TREE.equals(eventName)) {
              debugTreePrint();
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
    JMenuItem fileSave = new JMenuItem(MENU_SAVE);
    fileSave.addActionListener(actionListener);
    fileMenu.add(fileSave);
    JMenuItem fileImport = new JMenuItem(MENU_IMPORT);
    fileImport.addActionListener(actionListener);
    fileMenu.add(fileImport);
    JMenuItem fileExport = new JMenuItem(MENU_EXPORT);
    fileExport.addActionListener(actionListener);
    fileMenu.add(fileExport);
    JMenuItem fileDemo = new JMenuItem(MENU_LOAD_DEMO);
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
