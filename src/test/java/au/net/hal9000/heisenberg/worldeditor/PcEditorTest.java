package au.net.hal9000.heisenberg.worldeditor;

import java.awt.EventQueue;
import org.junit.Test;
import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.ConfigurationError;

public class PcEditorTest {

    @Test
    public void doTest() throws ConfigurationError {
        PcRace pc = DummyData.elf();
        PcEditor window = new PcEditor();
        window.setPc(pc);
        window.getFrame().setVisible(true);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PcEditorTest pcEditorRun = new PcEditorTest();
                    pcEditorRun.doTest();
                } catch (ConfigurationError e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
