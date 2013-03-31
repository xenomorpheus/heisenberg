package au.net.hal9000.heisenberg.worldeditor;

import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import au.net.hal9000.heisenberg.crafting.Recipe;
import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.Configuration;

public class RecipesTable extends JTable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    RecipesTable(final PcRace pc, Configuration config) {
        super();
        this.setModel(new MyTableModel(pc, config));
    }

    private class MyTableModel extends AbstractTableModel {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        String[] columnNames = { "Id", "Description" };

        ArrayList<String> recipeIds;
        private TreeMap<String, Recipe> recipes;

        public MyTableModel(PcRace pc, Configuration config) {
            recipeIds = new ArrayList<String>(pc.getRecipes());
            recipes = config.getRecipes();
        }

        public String getColumnName(int col) {
            return columnNames[col].toString();
        }

        public int getRowCount() {
            return recipeIds.size();
        }

        public int getColumnCount() {
            return 2;
        }

        public Object getValueAt(int row, int col) {
            String recipeId = recipeIds.get(row);
            if (col == 0) {
                return recipeId;
            } else {
                return recipes.get(recipeId).getDescription();
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
