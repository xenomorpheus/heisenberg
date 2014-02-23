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

/**
 */
public class SkillsTable extends JTable {

    /**
     * 
     */
    private static final String[] COLUMN_NAMES = {"Id", "Description" };
    /**
     * Field serialVersionUID.
     * (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for SkillsTable.
     */
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

    /**
     */
    private class MyTableModel extends AbstractTableModel {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        /**
         * Field config.
         */
        private Configuration config = Configuration.lastConfig();


        /**
         * Field orderedSkills.
         */
        private ArrayList<Skill> orderedSkills;
        /**
         * Field skillDetails.
         */
        private TreeMap<String, SkillDetail> skillDetails;

        /**
         * Constructor for MyTableModel.
         * @param pc PcRace
         */
        public MyTableModel(final PcRace pc) {
            Set<Skill> pcSkills = pc.getSkills();
            if (null == pcSkills) {
                orderedSkills = new ArrayList<Skill>();
            } else {
                orderedSkills = new ArrayList<Skill>(pcSkills);
            }
            skillDetails = config.getSkillDetails();
        }

        /**
         * Method getColumnName.
         * @param col int
         * @return String
         * @see javax.swing.table.TableModel#getColumnName(int)
         */
        @Override
        public String getColumnName(int col) {
            return COLUMN_NAMES[col].toString();
        }

        /**
         * Method getRowCount.
         * @return int
         * @see javax.swing.table.TableModel#getRowCount()
         */
        public int getRowCount() {
            return orderedSkills.size();
        }

        /**
         * Method getColumnCount.
         * @return int
         * @see javax.swing.table.TableModel#getColumnCount()
         */
        public int getColumnCount() {
            return 2;
        }

        /**
         * Method getValueAt.
         * @param row int
         * @param col int
         * @return Object
         * @see javax.swing.table.TableModel#getValueAt(int, int)
         */
        public Object getValueAt(int row, int col) {
            Skill skillCell = orderedSkills.get(row);
            String skillId = skillCell.toString();
            String result = null;
            if (0 == col) {
                result = skillId;
            } else {
                SkillDetail skillDetail = skillDetails.get(skillId);
                if (null != skillDetail) {
                    result = skillDetail.getDescription();
                }
            }
            return result;
        }

        /**
         * Method isCellEditable.
         * @param row int
         * @param col int
         * @return boolean
         * @see javax.swing.table.TableModel#isCellEditable(int, int)
         */
        @Override
        public boolean isCellEditable(int row, int col) {
            return false;
        }

        /**
         * Method setValueAt.
         * @param value Object
         * @param row int
         * @param col int
         * @see javax.swing.table.TableModel#setValueAt(Object, int, int)
         */
        @Override
        public void setValueAt(Object value, int row, int col) {
            // rowData[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }
}
