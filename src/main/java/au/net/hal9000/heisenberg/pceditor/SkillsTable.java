package au.net.hal9000.heisenberg.pceditor;

import au.net.hal9000.heisenberg.units.SkillId;
import au.net.hal9000.heisenberg.units.SkillDetail;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

/** Skills table. */
@Log4j2
public class SkillsTable extends JTable {

  /** Field serialVersionUID. (value is 1) */
  private static final long serialVersionUID = 1L;

  /** column names. */
  private static final String[] COLUMN_NAMES = { "Skill", "Description" };

  /** Field model. */
  private final MyTableModel model;

  /** Constructor for SkillsTable. */
  public SkillsTable(@NonNull Set<SkillId> skills, @NonNull Map<SkillId, SkillDetail> skillDetails) {
    super();
    model = new MyTableModel(skills, skillDetails);
    setModel(model);
  }

  public void updateSkills() {
    model.updateSkills();
  }

  /** My table model. */
  private class MyTableModel extends AbstractTableModel {
    /** serial id. */
    private static final long serialVersionUID = 1L;

    /** Field skills. */
    private final Set<SkillId> skills;

    /** Field skillDetails. */
    private final Map<SkillId, SkillDetail> skillDetails;

    /** Field skills. */
    private List<SkillId> skillsSorted;

    /**
     * Constructor for MyTableModel.
     *
     * @param skills Set of SkillIds
     */
    private MyTableModel(@NonNull Set<SkillId> skills, @NonNull Map<SkillId, SkillDetail> skillDetails) {
      this.skills = skills;
      this.skillDetails = skillDetails;
      sortSkills();
    }

    public void updateSkills() {
      sortSkills();
      fireTableDataChanged();
    }

    void sortSkills() {
      skillsSorted = new ArrayList<>(skills);
      skillsSorted.sort((s1, s2) -> s1.getId().compareTo(s2.getId()));
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
     * Method getRowCount
     *
     * @return int
     * @see javax.swing.table.TableModel#getRowCount()
     */
    public int getRowCount() {
      log.info("Getting row count: " + skills.size());
      return skills.size();
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
      var skillCell = skillsSorted.get(row);
      var skill = new SkillId(skillCell.toString());
      if (0 == col) {
        return skill.getId();
      }
      var skillDetail = skillDetails.get(skill);
      if (skillDetail == null) {
        return null;
      }
      return skillDetail.getDescription();
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
     * @param row   int
     * @param col   int
     * @see javax.swing.table.TableModel#setValueAt(Object, int, int)
     */
    @Override
    public void setValueAt(Object value, int row, int col) {
      // rowData[row][col] = value;
      fireTableCellUpdated(row, col);
    }
  }

}
