package au.net.hal9000.heisenberg.worldeditor;

import javax.swing.AbstractCellEditor;
import javax.swing.tree.TreeCellEditor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTree;

import au.net.hal9000.heisenberg.item.Item;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemEditor extends AbstractCellEditor implements
        TreeCellEditor, ActionListener {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    Item currentItem;
    JButton button;
    PcEditor pcEditor;
    JDialog dialog;
    protected static final String EDIT = "edit";

    public ItemEditor() {
        // Set up the editor (from the table's point of view),
        // which is a button.
        // This button brings up the color chooser dialog,
        // which is the editor from the user's point of view.
        button = new JButton();
        button.setActionCommand(EDIT);
        button.addActionListener(this);
        button.setBorderPainted(false);

        // Set up the dialog that the button brings up.
        pcEditor = new PcEditor();
        dialog = PcEditor.createDialog(button, "Pick a Color", true, // modal
                pcEditor, this, // OK button handler
                null); // no CANCEL button handler
    }

    /**
     * Handles events from the editor button and from the dialog's OK button.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (EDIT.equals(e.getActionCommand())) {
            // The user has clicked the cell, so
            // bring up the dialog.
            // button.setBackground(currentItem);
            pcEditor.setItem(currentItem);
            dialog.setVisible(true);

            // Make the renderer reappear.
            fireEditingStopped();

        } else { // User pressed dialog's "OK" button.
            currentItem = pcEditor.getItem();
        }
    }

    /**
     * Implement the one CellEditor method that AbstractCellEditor doesn't.
     */
    @Override
    public Object getCellEditorValue() {
        return currentItem;
    }

    /**
     *  Implement the one method defined by TableCellEditor.
     */
    @Override
    public Component getTreeCellEditorComponent(JTree tree, Object value,
            boolean isSelected, boolean expanded, boolean leaf, int row) {
        currentItem = (Item) value;
        return button;
    }
}
