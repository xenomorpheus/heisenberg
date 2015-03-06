package au.net.hal9000.heisenberg.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.swing.JFrame;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.DummyData;
import au.net.hal9000.heisenberg.worldeditor.RecipesTable;

/**
 * Unit tests for RecipesTable.
 * 
 * @author bruins
 * 
 * @version $Revision: 1.0 $
 */
public class RecipesTableTest {
    /** frame width. */
    static final int FRAME_WIDTH = 800;
    /** frame height. */
    static final int FRAME_HEIGHT = 300;

    /**
     * test basic operations.
     * 
     * @throws ConfigurationError
     */
    @Test
    public void testBasicOperations() throws ConfigurationError {
        PcRace pc = DummyData.elf();
        RecipesTable basicPanel = new RecipesTable();
        basicPanel.setPcRace(pc);
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

        RecipesTable basicPanel = new RecipesTable();
        basicPanel.setPcRace(pc);

        // add to JFrame
        guiFrame.add(basicPanel);
        guiFrame.pack();
        // This will centre the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);
        guiFrame.setVisible(true);

    }

}
