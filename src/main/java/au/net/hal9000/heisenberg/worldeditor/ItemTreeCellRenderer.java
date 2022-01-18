package au.net.hal9000.heisenberg.worldeditor;

import au.net.hal9000.heisenberg.item.api.Item;
import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

/** Item Tree Cell Renderer. */
class ItemTreeCellRenderer extends DefaultTreeCellRenderer {

  /** Set the Icon to be shown next to each Icon when in a JTree in the UI. */
  private static final long serialVersionUID = 1L;

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

    if (value instanceof Item) {
      Item item = (Item) value;
      setOpenIcon(item.getItemIcon().getIconOpen());
      setClosedIcon(item.getItemIcon().getIconClosed());
      setLeafIcon(item.getItemIcon().getIconLeaf());
    } else {
      setOpenIcon(getDefaultOpenIcon());
      setClosedIcon(getDefaultClosedIcon());
    }
    super.getTreeCellRendererComponent(tree, value, sel, exp, leaf, row, hasFocus);
    return this;
  }
}
