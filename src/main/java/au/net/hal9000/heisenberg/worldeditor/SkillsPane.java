package au.net.hal9000.heisenberg.worldeditor;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.units.Skill;

public class SkillsPane extends JScrollPane {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    SkillsPane(final PcRace pc) {

        TableModel dm = new AbstractTableModel() {
            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            String[] columnNames = { "Id", "Description" };

            ArrayList<Skill> skills = new ArrayList<Skill>(pc.getSkills());

            public String getColumnName(int col) {
                return columnNames[col].toString();
            }

            public int getRowCount() {
                return skills.size();
            }

            public int getColumnCount() {
                return 2;
            }

            public Object getValueAt(int row, int col) {
                Skill skill = skills.get(row);
                if (col == 0) {
                    return skill.toString();
                } else {
                    return "foo";
                }
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

        this(table);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

}
