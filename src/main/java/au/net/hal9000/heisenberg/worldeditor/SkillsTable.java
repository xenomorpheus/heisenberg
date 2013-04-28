package au.net.hal9000.heisenberg.worldeditor;

import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.units.Skill;
import au.net.hal9000.heisenberg.units.SkillDetail;
import au.net.hal9000.heisenberg.util.Configuration;

public class SkillsTable extends JTable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    PcRace pc;

    SkillsTable() {
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
        this.pc = pc;
        this.setModel(new MyTableModel(pc));
    }

    private class MyTableModel extends AbstractTableModel {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private Configuration config = Configuration.lastConfig();

        String[] columnNames = { "Id", "Description" };

        ArrayList<Skill> skills;
        TreeMap<String, SkillDetail> skillDetails;

        public MyTableModel(final PcRace pc) {
            skills = new ArrayList<Skill>(pc.getSkills());
            skillDetails = config.getSkillDetails();
        }

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
            Skill skillCell = skills.get(row);
            String skillId = skillCell.toString();
            String result = null;
            if (col == 0) {
                result = skillId;
            } else {
                SkillDetail skillDetail = skillDetails.get(skillId);
                if (skillDetail != null) {
                    result = skillDetail.getDescription();
                }
            }
            return result;
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
