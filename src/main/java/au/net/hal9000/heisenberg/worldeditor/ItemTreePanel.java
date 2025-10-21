package au.net.hal9000.heisenberg.worldeditor;

import au.net.hal9000.heisenberg.item.Factory;
import au.net.hal9000.heisenberg.item.Location;
import au.net.hal9000.heisenberg.item.api.Item;
import au.net.hal9000.heisenberg.item.api.ItemList;
import au.net.hal9000.heisenberg.item.being.Human;
import au.net.hal9000.heisenberg.pceditor.CharacterSheetEditor;
import au.net.hal9000.heisenberg.util.CharacterSheet;
import au.net.hal9000.heisenberg.util.Configuration;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import org.apache.log4j.Logger;
import lombok.Getter;
import lombok.Setter;

/** A window with hierarchical representation of the game objects. */
public class ItemTreePanel extends JPanel implements TreeModelListener, PropertyChangeListener {

  /** Class serial version id. */
  private static final long serialVersionUID = 1L;

  /** LOGGER. */
  private static final Logger LOGGER = Logger.getLogger(ItemTreePanel.class.getName());

  /** The TreeModel will translate our custom structure into Swing paths etc.. */
  private TreeModel treeModel = null;

  /** A tree structure of Item objects. */
  private JTree tree = new JTree();

  /** A list of Item types to choose when we want to add Item to a container. */
  private JComboBox<String> itemClassesList = null;

  /** Swing toolkit. */
  private Toolkit toolkit = Toolkit.getDefaultToolkit();

  @Getter
  @Setter
  Item selectedItem = null;

  /**
   * Constructor.
   *
   * @param config   configuration to use for building the selection boxes.
   * @param location the location to display.
   */
  public ItemTreePanel(Configuration config, Location location) {
    super();

    // The JTree can get big, so allow it to scroll.
    JScrollPane scrollPane = new JScrollPane();

    setLayout(new BorderLayout());

    tree.setCellRenderer(new ItemTreeCellRenderer(config));

    // Mouse listener for clicks
    tree.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        TreePath path = tree.getPathForLocation(e.getX(), e.getY());
        if (path == null) {
          return;
        }
        Object nodeObj = path.getLastPathComponent();
        if (nodeObj instanceof ItemTreeNode) {
          var node = (ItemTreeNode) nodeObj;
          var item = node.getItem();
          System.out.println("Click on item: "+item.getClass().getSimpleName());
          if (e.getClickCount() == 1) {
            LOGGER.info("Selected item: "+item.getClass().getSimpleName());
            setSelectedItem(item);
          }
          else if (e.getClickCount() == 2) {
            if (item instanceof Human) {
              launchCharacterSheetEditor(((Human) item).getCharacterSheet());
            }
          }
        }
      }
    });

    scrollPane.setViewportView(tree);

    // A JComboBox of Item types we can add
    Set<String> classIds = config.getItemClassIds();
    String[] classIdStrings = classIds.toArray(new String[classIds.size()]);
    itemClassesList = new JComboBox<String>(classIdStrings);

    // The "Add" Button Panel
    JPanel addButtonPanel = new JPanel();
    addButtonPanel.add(itemClassesList);

    // The "Add" Button
    final JButton addButton = new JButton("Add");
    // http://www.chka.de/swing/tree/DefaultTreeModel.html

    addButton.addActionListener(new ButtonActionListener());
    addButtonPanel.add(addButton);

    add(scrollPane, BorderLayout.NORTH);
    add(addButtonPanel, BorderLayout.SOUTH);
    setRoot(location);
  }

  private void launchCharacterSheetEditor(CharacterSheet cs) {
    var editor = new CharacterSheetEditor(cs);
    var frame = new JFrame();
    frame.add(editor);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.pack();
    frame.setLocationRelativeTo(null); // Centre
    frame.setVisible(true);
  }

  /**
   * Set the root object in this tree.
   *
   * @param root the root object of the tree.
   */
  public void setRoot(Location root) {
    // Create a TreeModel object to represent our m_tree of files
    TreeNode rootTreeNode = new ItemTreeNode(root);
    treeModel = new DefaultTreeModel(rootTreeNode);

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
   * @param node target node
   * @return a TreePath of nodes from root to the target node.
   */
  static TreePath getPathToNode(TreeNode node) {
    TreeNode currentNode = node;
    List<TreeNode> itemList = new ArrayList<>();
    while (currentNode != null) {
      itemList.add(0, currentNode);
      currentNode = currentNode.getParent();
    }
    return new TreePath(itemList.toArray(new TreeNode[itemList.size()]));
  }

  @Override
  public void treeNodesChanged(TreeModelEvent e) {
    Object o = e.getTreePath().getLastPathComponent();
    LOGGER.error("treeNodesChanged - code not finished");

    /*
     * If the event lists children, then the changed node is the child of the node
     * we've already gotten. Otherwise, the changed node and the specified node are
     * the same.
     */

    // int index = e.getChildIndices()[0];
    // DefaultMutableTreeNode node = (DefaultMutableTreeNode)
    // (node.getChildAt(index));

    LOGGER.info("treeNodesChanged: The user has finished editing the node " + o + ", " + o.getClass());
    // LOGGER.debug("New value: " + node.getUserObject());
  }

  @Override
  public void treeNodesInserted(TreeModelEvent e) {
    // TODO finish treeNodesInserted
    LOGGER.info("TODO treeNodesInserted: " + e.toString());
  }

  @Override
  public void treeNodesRemoved(TreeModelEvent e) {
    // TODO finish treeNodesRemoved
    LOGGER.info("TODO treeNodesRemoved: " + e.toString());
  }

  @Override
  public void treeStructureChanged(TreeModelEvent e) {
    // TODO finish treeStructureChanged
    LOGGER.info("TODO treeStructureChanged: " + e.toString());
  }

  @Override
  public void propertyChange(PropertyChangeEvent e) {
    // TODO Auto-generated method stub propertyChange
    LOGGER.info("TODO propertyChange: " + e.toString());
  }

  private class ButtonActionListener implements ActionListener {
    ButtonActionListener() {
      super();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
      Object selectedObject = tree.getLastSelectedPathComponent();
      if (selectedObject == null) {
        toolkit.beep();
        LOGGER.warn("No component was selected");
        return;
      }
      if (!(selectedObject instanceof MutableTreeNode)) {
        toolkit.beep();
        LOGGER.warn(selectedObject + " is not a MutableTreeNode");
        return;
      }

      MutableTreeNode parentTreeNode = (MutableTreeNode) selectedObject;
      if (!parentTreeNode.getAllowsChildren()) {
        LOGGER.warn(parentTreeNode + " does not allow addition of items");
        toolkit.beep();
        return;
      }

      if (!(parentTreeNode instanceof ItemTreeNode)) {
        LOGGER.warn(parentTreeNode + " treeNode, is not an instanceof ItemTreeNode");
        toolkit.beep();
        return;
      }

      // Create an Item of the requested type.
      String itemClass = itemClassesList.getSelectedItem().toString();
      Item childItem = Factory.createItem(itemClass);
      MutableTreeNode childTreeNode = new ItemTreeNode(childItem);
      appendMutableTreeNodeToParent(parentTreeNode, childTreeNode);
    }

    void appendMutableTreeNodeToParent(MutableTreeNode parentMutableTreeNode, MutableTreeNode childTreeNode) {

      // Append to selected node
      ItemTreeNode parentTreeNode = (ItemTreeNode) parentMutableTreeNode;
      ItemList container = (ItemList) parentTreeNode.getItem();
      int insertIndex = container.size();
      parentTreeNode.insert(childTreeNode, insertIndex);

      TreePath path = getPathToNode(parentMutableTreeNode);
      LOGGER.warn(
          "actionPerformed. path: " + path + ", insertIndex: " + insertIndex + ", childTreeNode: " + childTreeNode);
      // https://stackoverflow.com/questions/21150160/jtree-adding-nodes-and-updating
      int[] childIndices = new int[] { insertIndex };
      ((DefaultTreeModel) tree.getModel()).nodesWereInserted(parentTreeNode, childIndices);
    }
  }
}
