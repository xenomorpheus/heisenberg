package au.net.hal9000.heisenberg.pceditor;

import au.net.hal9000.heisenberg.util.AbilityScore;
import au.net.hal9000.heisenberg.util.CharacterSheet;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import lombok.NonNull;

/** Ability scores table. */
public class AbilityScoresTable extends JTable implements FocusListener {

  /** serial id. */
  private static final long serialVersionUID = 1L;

  private MyTableModel myTableModel;

  /** Constructor for AbilityScoresTable. */
  public AbilityScoresTable(@NonNull CharacterSheet characterSheet) {
    super();
    myTableModel = new MyTableModel(characterSheet);
    setModel(myTableModel);
  }

  @Override
  public void focusGained(FocusEvent e) {
    System.out.println("focusGained "+this.getClass().getSimpleName());
    // PC's level may have changed so we need to refresh the values in the table
    myTableModel.fireTableStructureChanged();
  }

  @Override
  public void focusLost(FocusEvent e) {
    System.out.println("focusLost");
    // Nothing
  }

  /** My table model. */
  private class MyTableModel extends AbstractTableModel {
    /** serial id. */
    private static final long serialVersionUID = 1L;

    /** column names. */
    private static final String[] columnNames = {"Ability", "Description"};

    private CharacterSheet characterSheet = null;

    /**
     * Constructor for MyTableModel.
     *
     * @param characterSheet CharacterSheet
     */
    private MyTableModel(@NonNull CharacterSheet characterSheet) {
      this.characterSheet = characterSheet;
    }

    public void fireTableStructureChanged() {
      System.out.println("fireTableStructureChanged "+this.getClass().getSimpleName()); 
      super.fireTableStructureChanged();
    } 

    /**
     * Get the column name.
     *
     * @param col column
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
     * @return int * @see javax.swing.table.TableModel#getRowCount()
     */
    @Override
    public int getRowCount() {
      return characterSheet.getAbilityScores().size();
    }

    /**
     * Method getColumnCount.
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
     * @param row int
     * @param col int
     * @return Object * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    @Override
    public Object getValueAt(int row, int col) {
      AbilityScore abilityScore = characterSheet.getAbilityScores().stream().skip(row).findFirst().orElse(null);
      if (abilityScore == null) {
        System.err.println("Error: AbilityScore is null at row " + row);
        return null;
      }
      if (0 == col) {
        return abilityScore.getName();
      }
      return abilityScore.valueOptionalMod();
    }

    /**
     * Method isCellEditable.
     *
     * @param row int
     * @param col int
     * @return boolean * @see javax.swing.table.TableModel#isCellEditable(int, int)
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
