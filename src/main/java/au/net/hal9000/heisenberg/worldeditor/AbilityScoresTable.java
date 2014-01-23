package au.net.hal9000.heisenberg.worldeditor;

import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.AbilityScore;

public class AbilityScoresTable extends JTable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    AbilityScoresTable() {
        super();
    }

    /**
     * Set the PcClass object to show values for.
     * 
     * @param pc
     *            the PcClass object to show values for.
     * 
     *            Note we pass the PcClass rather than the values needed to do
     *            the display. We do this because the values to display may be
     *            changed by other tabs, and passing by pc allows a refresh of
     *            values.
     */
    public void setItem(final PcRace pc) {
        this.setModel(new MyTableModel(pc));
    }

    private class MyTableModel extends AbstractTableModel {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        /** column names */
        private String[] columnNames = {"Id", "Description" };

        private ArrayList<AbilityScore> pcAbilityScores;

        public MyTableModel(PcRace pc) {
            TreeMap<String, AbilityScore> abilityScores = pc.getAbilityScores();
            if (abilityScores != null) {
                pcAbilityScores = new ArrayList<AbilityScore>(
                        abilityScores.values());
            }
        }

        public String getColumnName(int col) {
            return columnNames[col].toString();
        }

        public int getRowCount() {
            int count = 0;
            if (pcAbilityScores != null) {
                count = pcAbilityScores.size();
            }
            return count;
        }

        public int getColumnCount() {
            return 2;
        }

        public Object getValueAt(int row, int col) {
            AbilityScore abilityScore = pcAbilityScores.get(row);
            if (col == 0) {
                return abilityScore.getName();
            } else {
                return abilityScore.valueOptionalMod();
            }
        }

        public boolean isCellEditable(int row, int col) {
            return false;
        }

        public void setValueAt(Object value, int row, int col) {
            // rowData[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }

}
