package au.net.hal9000.heisenberg.worldeditor;

import static org.junit.Assert.assertNotNull;

import java.awt.EventQueue;
import org.junit.Test;
import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.DummyData;

public class PcRaceEditorTest {

    @Test
    public void doTest() throws ConfigurationError {
        PcRace pc = DummyData.elf();
        PcRaceEditor window = new PcRaceEditor();
        assertNotNull("not Null", window);
        window.setPcRace(pc);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PcRace pc = DummyData.elf();
                    PcRaceEditor window = new PcRaceEditor();
                    window.setPcRace(pc);
                    window.setVisible(true);
                } catch (ConfigurationError e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
