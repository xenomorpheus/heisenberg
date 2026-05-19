package au.net.hal9000.heisenberg.pceditor;

import au.net.hal9000.heisenberg.crafting.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

/** Recipe table. */
@Log4j2
public class RecipesTable extends JTable {

  /** serial version id. */
  private static final long serialVersionUID = 1L;

  /** column names. */
  private static final String[] COLUMN_NAMES = { "Recipe", "Description" };

  /** Field model. */
  private final MyTableModel model;

  /** Constructor. */
  public RecipesTable(@NonNull Set<String> recipeIds, @NonNull Map<String, Recipe> recipeDetails) {
    super();
    model = new MyTableModel(recipeIds, recipeDetails);
    setModel(model);
  }

  public void update() {
    model.update();
  }

  /** My table model. */
  private class MyTableModel extends AbstractTableModel {
    /** serial id. */
    private static final long serialVersionUID = 1L;

    /** Field recipeIds. */
    private final Set<String> recipeIds;

    /** Field recipeDetails. */
    private final Map<String, Recipe> recipeDetails;

    /** Field recipes. */
    private List<String> recipesSorted;

    /**
     * Constructor for MyTableModel.
     *
     * @param cs CharacterSheet
     */
    private MyTableModel(@NonNull Set<String> recipeIds, @NonNull Map<String, Recipe> recipeDetails) {
      this.recipeIds = recipeIds;
      this.recipeDetails = recipeDetails;
      sortRecipes();
    }

    public void update() {
      sortRecipes();
      fireTableDataChanged();
    }

    void sortRecipes() {
      recipesSorted = new ArrayList<>(recipeIds);
      recipesSorted.sort(String::compareTo);
    }

    /**
     * Method getColumnName.
     *
     * @param col int
     * @return String
     * @see javax.swing.table.TableModel#getColumnName(int)
     */
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
      int count = 0;
      if (recipeIds != null) {
        count = recipeIds.size();
      }
      return count;
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
      String recipeId = recipesSorted.get(row);
      if (0 == col) {
        return recipeId;
      }
      var recipe = recipeDetails.get(recipeId);
      if (recipe == null) {
        return null;
      }
      return recipe.getDescription();
    }

    /**
     * Method isCellEditable.
     *
     * @param row int
     * @param col int
     * @return boolean
     * @see javax.swing.table.TableModel#isCellEditable(int, int)
     */
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
    public void setValueAt(Object value, int row, int col) {
      // rowData[row][col] = value;
      fireTableCellUpdated(row, col);
    }
  }
}
