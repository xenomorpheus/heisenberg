package au.net.hal9000.heisenberg.worldeditor.demo;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import au.net.hal9000.heisenberg.item.PcRace;
import au.net.hal9000.heisenberg.util.ConfigurationError;
import au.net.hal9000.heisenberg.util.DummyData;
import au.net.hal9000.heisenberg.worldeditor.PcRaceEditor;

public class PcRaceEditorMain {

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
