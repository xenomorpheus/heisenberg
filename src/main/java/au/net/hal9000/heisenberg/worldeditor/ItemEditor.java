package au.net.hal9000.heisenberg.worldeditor;

import javax.swing.AbstractCellEditor;
import javax.swing.tree.TreeCellEditor;
import javax.swing.JDialog;
import javax.swing.JTree;

import org.apache.log4j.Logger;

import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.item.PcRace;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemEditor extends AbstractCellEditor implements TreeCellEditor,
        ActionListener {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ItemEditor.class
            .getName());

    Item currentItem;
    PcRaceEditor pcRaceEditor;
    JDialog dialog;
    protected static final String EDIT = "edit";

    public ItemEditor() {
        pcRaceEditor = new PcRaceEditor();
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
            if (currentItem instanceof PcRace) {
                pcRaceEditor.setPcRace((PcRace) currentItem);
                dialog.setVisible(true);

                // Make the renderer reappear.
                fireEditingStopped();
            } else {
                logger.error("unsupported Item type "
                        + Item.class.getSimpleName());
            }

        } else { // User pressed dialog's "OK" button.
            currentItem = pcRaceEditor.getPcRace();
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
     * Implement the one method defined by TableCellEditor.
     */
    @Override
    public Component getTreeCellEditorComponent(JTree tree, Object value,
            boolean isSelected, boolean expanded, boolean leaf, int row) {
        currentItem = (Item) value;
        Component component = null;

        if (currentItem instanceof PcRace) {
            pcRaceEditor.setPcRace((PcRace) currentItem);
            component = pcRaceEditor;
        }

        return component;
    }

}
