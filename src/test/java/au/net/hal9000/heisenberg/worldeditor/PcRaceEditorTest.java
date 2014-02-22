package au.net.hal9000.heisenberg.worldeditor;

import static org.junit.Assert.assertNotNull;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.junit.Test;
import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.DummyData;

/**
 */
public class PcRaceEditorTest {

    /**
     * Method doTest.
     * @throws ConfigurationError
     */
    @Test
    public void doTest() throws ConfigurationError {
        PcRace pc = DummyData.elf();
        PcRaceEditor window = new PcRaceEditor();
        assertNotNull("not Null", window);
        window.setPcRace(pc);
    }

    /**
     * Launch the application.
     * @param args line args.
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    PcRace pc = DummyData.elf();
                    PcRaceEditor editor = new PcRaceEditor();
                    editor.setPcRace(pc);
                    JFrame window = new JFrame();
                    window.add(editor);
                    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    window.pack();
                    window.setVisible(true);
                } catch (ConfigurationError e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
