package au.net.hal9000.heisenberg.worldeditor;

import au.net.hal9000.heisenberg.util.Configuration;
import au.net.hal9000.heisenberg.util.ItemClassIcon;
import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import org.apache.log4j.Logger;

/** Set the Icon to be shown next to each Icon when in a JTree in the UI. */
class ItemTreeCellRenderer extends DefaultTreeCellRenderer {

  /** Logger. */
  private static final Logger LOGGER = Logger.getLogger(ItemTreeCellRenderer.class.getName());

  private ItemClassIcon itemClassIcon = null;

  public ItemTreeCellRenderer(Configuration config) {
    super();
    itemClassIcon = new ItemClassIcon(config);
  }

  /**
   * Method getTreeCellRendererComponent.
   *
   * @param tree JTree
   * @param value Object
   * @param sel boolean
   * @param exp boolean
   * @param leaf boolean
   * @param row int
   * @param hasFocus boolean
   * @return Component
   * @see javax.swing.tree.TreeCellRenderer#getTreeCellRendererComponent(JTree, Object, boolean,
   *     boolean, boolean, int, boolean)
   */
  @Override
  public Component getTreeCellRendererComponent(
      JTree tree, Object value, boolean sel, boolean exp, boolean leaf, int row, boolean hasFocus) {
    // TODO should we be passed a MutableTreeNode ?
    // DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
    // String s = node.getUserObject().toString();
    if (value instanceof ItemTreeNode) {
      var simpleClassName = ((ItemTreeNode) value).getItem().getSimpleClassName();
      // The icon getters may return null which gives no icon which is better than the default
      // filesystem icons.
      setOpenIcon(itemClassIcon.getClassIconOpenDefault(simpleClassName));
      setClosedIcon(itemClassIcon.getClassIconClosedDefault(simpleClassName));
      setLeafIcon(itemClassIcon.getClassIconLeafDefault(simpleClassName));
    } else {
      if (value == null) {
        LOGGER.warn("Cell value: IS NULL");
      } else {
        LOGGER.warn("Cell value getSimpleName: " + value.getClass().getSimpleName());
      }
      setOpenIcon(getDefaultOpenIcon());
      setClosedIcon(getDefaultClosedIcon());
      setLeafIcon(getDefaultLeafIcon());
    }
    super.getTreeCellRendererComponent(tree, value, sel, exp, leaf, row, hasFocus);
    return this;
  }
}
