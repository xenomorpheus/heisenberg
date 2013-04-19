package au.net.hal9000.heisenberg.worldeditor;

import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.AbilityScore;
import au.net.hal9000.heisenberg.util.Configuration;

public class AbilityScoresTable extends JTable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    AbilityScoresTable(final PcRace pc, Configuration config) {
        super();
        this.setModel(new MyTableModel(pc, config));
    }

    private class MyTableModel extends AbstractTableModel {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        String[] columnNames = { "Id", "Description" };

        private ArrayList<AbilityScore> pcAbilityScores;

        public MyTableModel(PcRace pc, Configuration config) {
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
            return pcAbilityScores.size();
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
