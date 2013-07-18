package au.net.hal9000.heisenberg.worldeditor;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import au.net.hal9000.heisenberg.item.Item;

public class ItemTreeCellRenderer extends DefaultTreeCellRenderer {

    /**
     * Set the Icon to be shown next to each Icon when in a JTree in the UI.
     */
    private static final long serialVersionUID = 1L;

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
            boolean sel, boolean exp, boolean leaf, int row, boolean hasFocus) {
        // TODO should we be passed a MutableTreeNode ?
        // DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        // String s = node.getUserObject().toString();

        if (value instanceof Item) {
            Item item = (Item) value;
            setOpenIcon(item.getIconOpen());
            setClosedIcon(item.getIconClosed());
            setLeafIcon(item.getIconLeaf());
        }
        else {
            setOpenIcon(getDefaultOpenIcon());
            setClosedIcon(getDefaultClosedIcon());
        }
        super.getTreeCellRendererComponent(tree, value, sel, exp, leaf, row,
                hasFocus);
        return this;
    }
}
