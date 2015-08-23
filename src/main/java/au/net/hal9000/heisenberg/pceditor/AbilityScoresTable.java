package au.net.hal9000.heisenberg.pceditor;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import au.net.hal9000.heisenberg.item.entity.PcRace;
import au.net.hal9000.heisenberg.util.AbilityScore;

/**
 * @author bruins
 * @version $Revision: 1.0 $
 */
public class AbilityScoresTable extends JTable implements FocusListener {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private MyTableModel myTableModel;

    /**
     * Constructor for AbilityScoresTable.
     */
    public AbilityScoresTable() {
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
    public void setPcRace(final PcRace pc) {
        myTableModel = new MyTableModel(pc);
        setModel(myTableModel);
    }

    @Override
    public void focusGained(FocusEvent e) {
        // PC's level may have changed so we need to refresh the values in the table
        myTableModel.fireTableStructureChanged();
    }

    @Override
    public void focusLost(FocusEvent e) {
        // Nothing
    }

    /**
     * @author bruins
     */
    private class MyTableModel extends AbstractTableModel {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        /** column names. */
        private String[] columnNames = { "Id", "Description" };

        /**
         * Field pcAbilityScores.
         */
        private ArrayList<AbilityScore> pcAbilityScores;

        /**
         * Constructor for MyTableModel.
         * 
         * @param pc
         *            PcRace
         */
        private MyTableModel(PcRace pc) {
            Map<String, AbilityScore> abilityScores = pc.getAbilityScores();
            if (null != abilityScores) {
                pcAbilityScores = new ArrayList<AbilityScore>(
                        abilityScores.values());
            }
        }

        /**
         * Get the column name.
         * 
         * @param col
         *            column
         * @return the column name
         * @see javax.swing.table.TableModel#getColumnName(int)
         */
        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        /**
         * get row count.
         * 
         * 
         * @return int * @see javax.swing.table.TableModel#getRowCount()
         */
        @Override
        public int getRowCount() {
            int count = 0;
            if (null != pcAbilityScores) {
                count = pcAbilityScores.size();
            }
            return count;
        }

        /**
         * Method getColumnCount.
         * 
         * 
         * @return int * @see javax.swing.table.TableModel#getColumnCount()
         */
        @Override
        public int getColumnCount() {
            return 2;
        }

        /**
         * Method getValueAt.
         * 
         * @param row
         *            int
         * @param col
         *            int
         * 
         * 
         * @return Object * @see javax.swing.table.TableModel#getValueAt(int,
         *         int)
         */
        @Override
        public Object getValueAt(int row, int col) {
            AbilityScore abilityScore = pcAbilityScores.get(row);
            if (0 == col) {
                return abilityScore.getName();
            } else {
                return abilityScore.valueOptionalMod();
            }
        }

        /**
         * Method isCellEditable.
         * 
         * @param row
         *            int
         * @param col
         *            int
         * 
         * 
         * @return boolean * @see
         *         javax.swing.table.TableModel#isCellEditable(int, int)
         */
        @Override
        public boolean isCellEditable(int row, int col) {
            return false;
        }

        /**
         * Method setValueAt.
         * 
         * @param value
         *            Object
         * @param row
         *            int
         * @param col
         *            int
         * 
         * @see javax.swing.table.TableModel#setValueAt(Object, int, int)
         */
        @Override
        public void setValueAt(Object value, int row, int col) {
            // rowData[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }

}
