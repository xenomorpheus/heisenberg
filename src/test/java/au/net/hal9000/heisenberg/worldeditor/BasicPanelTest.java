package au.net.hal9000.heisenberg.worldeditor;

//Use the GridBagConstraints to determine how the component
import static org.junit.Assert.assertNotNull;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.DummyData;

/**
 */
public class BasicPanelTest {
    /** frame width. */
    static final int FRAME_WIDTH = 800;
    /** frame height. */
    static final int FRAME_HEIGHT = 300;

    /**
     * Method doTest.
     * @throws ConfigurationError
     */
    @Test
    public void doTest() throws ConfigurationError {

        PcRace pc = DummyData.elf();
        BasicPanel basicPanel = new BasicPanel();
        assertNotNull("not Null", basicPanel);
        basicPanel.setItem(pc);
    }

    /**
     * for testing the config editor.
     * 
     * @param args
     *            command line args.
     */
    public static void main(String[] args) {

        // Use the event dispatch thread for Swing components
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    PcRace pc = DummyData.elf();
                    JFrame guiFrame = new JFrame();

                    // make sure the program exits when the frame closes
                    guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    guiFrame.setTitle("Basic Panel");
                    guiFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

                    // This will center the JFrame in the middle of the screen
                    guiFrame.setLocationRelativeTo(null);

                    BasicPanel basicPanel = new BasicPanel();
                    basicPanel.setItem(pc);

                    // add to JFrame
                    guiFrame.add(basicPanel);
                    guiFrame.setVisible(true);
                } catch (ConfigurationError e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
