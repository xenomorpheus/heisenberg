package au.net.hal9000.heisenberg.pceditor;

import au.net.hal9000.heisenberg.units.Skill;
import au.net.hal9000.heisenberg.units.SkillDetail;
import au.net.hal9000.heisenberg.util.CharacterSheet;
import au.net.hal9000.heisenberg.util.Configuration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/** Skills table. */
public class SkillsTable extends JTable {

  /** column names. */
  private static final String[] COLUMN_NAMES = {"Skill", "Description"};

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;

  /** Constructor for SkillsTable. */
  public SkillsTable(CharacterSheet characterSheet) {
    super();
    if (characterSheet == null) {
      throw new IllegalArgumentException("characterSheet is NULL");
    }
    setModel(new MyTableModel(characterSheet));
  }


  /** My table model. */
  private class MyTableModel extends AbstractTableModel {
    /** serial id. */
    private static final long serialVersionUID = 1L;

    /** Field config. */
    private Configuration config = Configuration.lastConfig();

    /** Field orderedSkills. */
    private List<Skill> orderedSkills;

    /** Field skillDetails. */
    private Map<Skill, SkillDetail> skillDetails;

    /**
     * Constructor for MyTableModel.
     *
     * @param cs CharacterSheet
     */
    private MyTableModel(final CharacterSheet cs) {
      Set<Skill> pcSkills = cs.getSkills();
      if (pcSkills == null) {
        orderedSkills = new ArrayList<Skill>();
      } else {
        orderedSkills = new ArrayList<Skill>(pcSkills);
      }
      skillDetails = config.getSkillDetails();
    }

    /**
     * Method getColumnName.
     *
     * @param col int
     * @return String
     * @see javax.swing.table.TableModel#getColumnName(int)
     */
    @Override
    public String getColumnName(int col) {
      return COLUMN_NAMES[col];
    }

    /**
     * Method getRowCount.
     *
     * @return int
     * @see javax.swing.table.TableModel#getRowCount()
     */
    public int getRowCount() {
      return orderedSkills.size();
    }

    /**
     * Method getColumnCount.
     *
     * @return int
     * @see javax.swing.table.TableModel#getColumnCount()
     */
    public int getColumnCount() {
      return 2;
    }

    /**
     * Method getValueAt.
     *
     * @param row int
     * @param col int
     * @return Object
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    public Object getValueAt(int row, int col) {
      Skill skillCell = orderedSkills.get(row);
      Skill skill = new Skill(skillCell.toString());
      String result = null;
      if (0 == col) {
        result = skill.getId();
      } else {
        SkillDetail skillDetail = skillDetails.get(skill);
        if (skillDetail != null) {
          result = skillDetail.getDescription();
        }
      }
      return result;
    }

    /**
     * Method isCellEditable.
     *
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
     *
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
