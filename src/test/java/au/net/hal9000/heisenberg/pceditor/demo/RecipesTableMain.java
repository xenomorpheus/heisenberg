package au.net.hal9000.heisenberg.pceditor.demo;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.pceditor.RecipesTable;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.TestEnvironment;

public class RecipesTableMain { // NO_UCD (unused code)

    /** frame width. */
    static final int FRAME_WIDTH = 2400;
    /** frame height. */
    static final int FRAME_HEIGHT = 300;

    /**
     * app to test the world editor.
     * 
     * @param args
     *            not used
     */
    public static void main(String[] args) {

        // Use the event dispatch thread for Swing components
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    PcRace pc = TestEnvironment.getPcRace();
                    JFrame guiFrame = new JFrame();

                    // make sure the program exits when the frame closes
                    guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    guiFrame.setTitle("Recipes Table");
                    guiFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

                    RecipesTable recipesTable = new RecipesTable();
                    recipesTable.setPcRace(pc);

                    // add to JFrame
                    guiFrame.add(recipesTable);
                    guiFrame.pack();
                    // This will centre the JFrame in the middle of the screen
                    guiFrame.setLocationRelativeTo(null);
                    guiFrame.setVisible(true);
                } catch (ConfigurationError e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
