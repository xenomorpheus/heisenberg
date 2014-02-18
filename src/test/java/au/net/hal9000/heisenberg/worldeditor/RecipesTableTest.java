package au.net.hal9000.heisenberg.worldeditor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.junit.Test;
import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.DummyData;

/**
 * Unit tests for RecipesTable.
 * 
 * @author bruins
 * 
 */
public class RecipesTableTest {
    /** frame width. */
    static final int FRAME_WIDTH = 800;
    /** frame height. */
    static final int FRAME_HEIGHT = 300;

    /**
     * 
     * @throws ConfigurationError
     *             if bad config.
     */
    @Test
    public void testRecipesTable() throws ConfigurationError {
        PcRace pc = DummyData.elf();
        RecipesTable basicPanel = new RecipesTable();
        basicPanel.setItem(pc);
        assertNotNull("BasicPanel not null", basicPanel);
    }

    /**
     * Test getRowCount.
     */
    @Test
    public void testGetRowCount() {

        RecipesTable recipesTable = new RecipesTable();
        assertEquals(0, recipesTable.getRowCount());
    }

    /**
     * 
     * @throws ConfigurationError
     *             if bad config.
     */
    public void doTest() throws ConfigurationError {

        PcRace pc = DummyData.elf();
        JFrame guiFrame = new JFrame();

        // make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Skills Table");
        guiFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        // This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);

        RecipesTable basicPanel = new RecipesTable();
        basicPanel.setItem(pc);

        // add to JFrame
        guiFrame.add(basicPanel);
        guiFrame.setVisible(true);

    }

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
