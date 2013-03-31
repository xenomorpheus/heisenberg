package au.net.hal9000.heisenberg.worldeditor;

import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.units.PowerWord;
import au.net.hal9000.heisenberg.units.PowerWordDetail;
import au.net.hal9000.heisenberg.util.Configuration;

public class PowerWordsTable extends JTable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    PowerWordsTable(final PcRace pc, Configuration config) {
        super();
        this.setModel(new MyTableModel(pc, config));
    }

    private class MyTableModel extends AbstractTableModel {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        String[] columnNames = { "Id", "Description" };

        ArrayList<PowerWord> powerWords;
        TreeMap<String, PowerWordDetail> powerWordDetails;

        public MyTableModel(PcRace pc, Configuration config) {
            powerWords = new ArrayList<PowerWord>(pc.getPowerWords());
            powerWordDetails = config.getPowerWordDetails();
        }

        public String getColumnName(int col) {
            return columnNames[col].toString();
        }

        public int getRowCount() {
            return powerWords.size();
        }

        public int getColumnCount() {
            return 2;
        }

        public Object getValueAt(int row, int col) {
            PowerWord powerWordCell = powerWords.get(row);
            String powerWordId = powerWordCell.toString();
            if (col == 0) {
                return powerWordId;
            } else {
                return powerWordDetails.get(powerWordId).getDescription();
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