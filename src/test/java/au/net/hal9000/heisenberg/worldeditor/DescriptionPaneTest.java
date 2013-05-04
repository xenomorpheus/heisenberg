package au.net.hal9000.heisenberg.worldeditor;

import static org.junit.Assert.assertNotNull;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.junit.Test;
import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.DummyData;

public class DescriptionPaneTest {

    @Test
    public void doTest() throws ConfigurationError {

        PcRace pc = DummyData.elf();
        DescriptionPane window = new DescriptionPane();
        assertNotNull("not Null", window);
        window.setItem(pc);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    PcRace pc = DummyData.elf();
                    JFrame guiFrame = new JFrame();

                    // make sure the program exits when the frame closes
                    guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    guiFrame.setTitle("Description");
                    guiFrame.setSize(800, 600);

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
