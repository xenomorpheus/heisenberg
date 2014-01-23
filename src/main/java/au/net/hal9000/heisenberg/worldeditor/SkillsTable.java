package au.net.hal9000.heisenberg.worldeditor;

import java.util.ArrayList;
import java.util.Set;
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
    private static final String[] COLUMN_NAMES = {"Id", "Description" };
    private static final long serialVersionUID = 1L;

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
        this.setModel(new MyTableModel(pc));
    }

    private class MyTableModel extends AbstractTableModel {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private Configuration config = Configuration.lastConfig();


        private ArrayList<Skill> orderedSkills;
        private TreeMap<String, SkillDetail> skillDetails;

        public MyTableModel(final PcRace pc) {
            Set<Skill> pcSkills = pc.getSkills();
            if (pcSkills == null) {
                orderedSkills = new ArrayList<Skill>();
            } else {
                orderedSkills = new ArrayList<Skill>(pcSkills);
            }
            skillDetails = config.getSkillDetails();
        }

        @Override
        public String getColumnName(int col) {
            return COLUMN_NAMES[col].toString();
        }

        public int getRowCount() {
            return orderedSkills.size();
        }

        public int getColumnCount() {
            return 2;
        }

        public Object getValueAt(int row, int col) {
            Skill skillCell = orderedSkills.get(row);
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

        @Override
        public boolean isCellEditable(int row, int col) {
            return false;
        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            // rowData[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }
}
