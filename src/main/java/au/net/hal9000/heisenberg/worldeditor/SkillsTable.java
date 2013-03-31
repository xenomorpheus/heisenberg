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

    SkillsTable(final PcRace pc, Configuration config) {
        super();
        this.setModel(new MyTableModel(pc, config));
    }

    private class MyTableModel extends AbstractTableModel {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        String[] columnNames = { "Id", "Description" };

        ArrayList<Skill> skills;
        TreeMap<String, SkillDetail> skillDetails;

        public MyTableModel(PcRace pc, Configuration config) {
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
            if (col == 0) {
                return skillId;
            } else {
                return skillDetails.get(skillId).getDescription();
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
