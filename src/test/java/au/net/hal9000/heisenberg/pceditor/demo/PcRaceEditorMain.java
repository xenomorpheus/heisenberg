package au.net.hal9000.heisenberg.pceditor.demo;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.pceditor.PcRaceEditor;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.TestEnvironment;

public class PcRaceEditorMain { // NO_UCD (unused code)

    /**
     * Launch the application.
     * @param args line args.
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    PcRace pc = TestEnvironment.getPcRace();
                    PcRaceEditor editor = new PcRaceEditor();
                    editor.setPcRace(pc);
                    JFrame window = new JFrame();
                    window.add(editor);
                    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    window.pack();
                    window.setLocationRelativeTo(null); // Centre
                    window.setVisible(true);
                } catch (ConfigurationError e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
