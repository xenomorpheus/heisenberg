package au.net.hal9000.heisenberg.worldeditor;

import javax.swing.SwingUtilities;

import au.net.hal9000.heisenberg.util.ConfigurationError;

public class RecipesTableMain {

    /**
     * 
     * @param args
     *            command line args, but not used.
     */
    public static void main(String[] args) {

        // Use the event dispatch thread for Swing components
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    RecipesTableTest recipesTableTest = new RecipesTableTest();
                    recipesTableTest.doTest();
                } catch (ConfigurationError e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
