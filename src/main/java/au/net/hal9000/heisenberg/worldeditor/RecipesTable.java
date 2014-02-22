package au.net.hal9000.heisenberg.worldeditor;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.apache.log4j.Logger;

import au.net.hal9000.heisenberg.crafting.Recipe;
import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.Configuration;

/**
 */
public class RecipesTable extends JTable {

    /** serial version id. */
    private static final long serialVersionUID = 1L;
    /** column names. */
    private static final String[] COLUMN_NAMES = {"Id", "Description" };

    /** Constructor. */
    RecipesTable() {
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
         * Field logger.
         */
        private final Logger logger = Logger.getLogger(RecipesTable.class
                .getName());

        /**
         * Field recipeIds.
         */
        private ArrayList<String> recipeIds;
        /**
         * Field recipes.
         */
        private TreeMap<String, Recipe> recipes;

        /**
         * Constructor for MyTableModel.
         * @param pc PcRace
         */
        public MyTableModel(PcRace pc) {
            recipes = config.getRecipes();
            Set<String> pcRecipeIds = null;
            if (pc == null) {
                logger.error("PC is NULL");
            } else {
                pcRecipeIds = pc.getRecipes();
            }
            if (pcRecipeIds != null) {
                recipeIds = new ArrayList<String>(pcRecipeIds);
            }
        }

        /**
         * Method getColumnName.
         * @param col int
         * @return String
         * @see javax.swing.table.TableModel#getColumnName(int)
         */
        public String getColumnName(int col) {
            return COLUMN_NAMES[col].toString();
        }

        /**
         * Method getRowCount.
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
            String recipeId = recipeIds.get(row);
            String result = null;
            if (col == 0) {
                result = recipeId;
            } else {
                Recipe recipe = recipes.get(recipeId);
                if (recipe != null) {
                    result = recipe.getDescription();
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
        public void setValueAt(Object value, int row, int col) {
            // rowData[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }

}
