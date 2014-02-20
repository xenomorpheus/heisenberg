package au.net.hal9000.heisenberg.worldeditor;

import static org.junit.Assert.assertNotNull;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.junit.Test;
import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.DummyData;

public class DescriptionPaneTest {
    /** frame width. */
    static final int FRAME_WIDTH = 800;
    /** frame height. */
    static final int FRAME_HEIGHT = 600;


    @Test
    public void doTest() throws ConfigurationError {

        PcRace pc = DummyData.elf();
        DescriptionPane window = new DescriptionPane();
        assertNotNull("not Null", window);
        window.setItem(pc);
    }

    /**
     * Launch the application.
     * @param command line args.
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {

                    PcRace pc = DummyData.elf();
                    JFrame guiFrame = new JFrame();

                    // make sure the program exits when the frame closes
                    guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    guiFrame.setTitle("Description");
                    guiFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

                    // This will center the JFrame in the middle of the screen
                    guiFrame.setLocationRelativeTo(null);

                    DescriptionPane window = new DescriptionPane();
                    window.setItem(pc);
                    
                    // add to JFrame
                    guiFrame.add(window);
                    guiFrame.setVisible(true);

                } catch (ConfigurationError e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
