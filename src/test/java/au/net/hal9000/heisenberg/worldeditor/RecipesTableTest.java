package au.net.hal9000.heisenberg.worldeditor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.junit.Test;
import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.DummyData;

public class RecipesTableTest {

    @Test
    public void testRecipesTable() throws ConfigurationError {
        PcRace pc = DummyData.elf();
        RecipesTable basicPanel = new RecipesTable();
        basicPanel.setItem(pc);
        assertNotNull("BasicPanel not null", basicPanel);
    }

    @Test
    public void testGetRowCount() {

        RecipesTable recipesTable = new RecipesTable();
        assertEquals(0, recipesTable.getRowCount());
    }

    public void doTest() throws ConfigurationError {

        PcRace pc = DummyData.elf();
        JFrame guiFrame = new JFrame();

        // make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Skills Table");
        guiFrame.setSize(800, 300);

        // This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);

        RecipesTable basicPanel = new RecipesTable();
        basicPanel.setItem(pc);

        // add to JFrame
        guiFrame.add(basicPanel);
        guiFrame.setVisible(true);

    }

    public static void main(String[] args) {

        // Use the event dispatch thread for Swing components
        EventQueue.invokeLater(new Runnable() {

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
