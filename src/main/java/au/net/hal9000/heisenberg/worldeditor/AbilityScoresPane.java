package au.net.hal9000.heisenberg.worldeditor;

import java.awt.Dimension;
import java.util.TreeMap;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.AbilityScore;
import au.net.hal9000.heisenberg.util.PcClass;

public class AbilityScoresPane extends JScrollPane {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    AbilityScoresPane(final PcRace pc) {
        super();

        TableModel dm = new AbstractTableModel() {
            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            String[] columnNames = { "Id", "Description" };

            private TreeMap<String, AbilityScore> abilityScores = pc.getAbilityScores();

            public String getColumnName(int col) {
                return columnNames[col].toString();
            }

            public int getRowCount() {
                if (abilityScores == null){
                    return 0;
                }
                return abilityScores.values().size();            }

            public int getColumnCount() {
                return 2;
            }

            public Object getValueAt(int row, int col) {
                AbilityScore abilityScore = abilityScoresget(row);
                if (col == 0) {
                    return abilityScore.getName();
                }
                return abilityScore.getValue();
            }

            public boolean isCellEditable(int row, int col) {
                return false;
            }

            public void setValueAt(Object value, int row, int col) {
                // rowData[row][col] = value;
                fireTableCellUpdated(row, col);
            }
        };

        final JTable table = new JTable(dm);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }
    
}



